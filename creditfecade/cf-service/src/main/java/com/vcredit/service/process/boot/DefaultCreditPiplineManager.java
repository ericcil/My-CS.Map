package com.vcredit.service.process.boot;

import com.vcredit.service.process.CentralizeStep;
import com.vcredit.service.process.CreditPipline;
import com.vcredit.service.process.Step;
import com.vcredit.service.process.dto.DefaultChannelParam;
import com.vcredit.service.process.dto.ProcessContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author chenyubin
 * @Date 2018/7/3
 */
@Component
public class DefaultCreditPiplineManager {

    //用于存储已经注册初始化的pipline
    private static final transient Map<String,CreditPipline<DefaultChannelParam> >piplineMap = new ConcurrentHashMap<>(20);
    private static final transient Map<String,CentralizeStep> stepPool = new ConcurrentHashMap<>(20);

    private ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        Map<String,CentralizeStep> cStepMap = this.applicationContext.getBeansOfType(CentralizeStep.class);
        cStepMap.forEach( (key,value) ->{
            stepPool.put(value.getStepName(),value);
        });
        //TODO 管道初始化待补充
    }


    /**
     * 获取所有管道的key
     * @return
     */
    public Set<String> getAllPiplineKeys(){
        return piplineMap.keySet();
    }
    /**
     * 获取所有步骤的key
     * @return
     */
    public Set<String> getAllStepKeys(){
        return stepPool.keySet();
    }


    /**
     * 获取管道下所有步骤名
     * @param piplineKey
     * @return
     */
    public List<String> getStepsByPipline(String piplineKey){
        List<Step<DefaultChannelParam>> steps = piplineMap.get(piplineKey).getAllStep();
        List<String> result = new ArrayList<>();
        for(Step<DefaultChannelParam> step:steps){
            result.add(step.getStepName());
        }
        return result;
    }
    /**
     * 获取指定pipline的引用
     * TODO 该方式可能存在安全性问题
     * @Param key
     * @return
     */
    public CreditPipline selectePipline(String key){
        return piplineMap.get(key);
    }


    /**
     * 向管道内添加已经注册的步骤
     * @param plKey
     * @param index
     * @param stepKey
     */
    public void addStep(String plKey,int index,String stepKey){
        CreditPipline<DefaultChannelParam> pipline = selectePipline(plKey);
        if(pipline == null){
            pipline = new DefaultCopyOnWritePipline<>();
            pipline.addStep(0,stepPool.get(stepKey));
            piplineMap.put(plKey,pipline);

        }else {
            pipline.addStep(index, stepPool.get(stepKey));
        }
    }

    /**
     * 移除特定管道的特定步骤
     * @param key
     * @param index
     */
    public void removeStep(String key,int index){
        selectePipline(key).remove(index);
    }

    public void removeAllStep(String key){
        selectePipline(key).clean();
    }

    public String getKey(Object processParam){
        return processParam.getClass().getName();
    }


    /**
     * 执行授信流程
     * @param plKey
     * @param processParam
     * @return
     */
    public ProcessContext<DefaultChannelParam> exectueProcess(String plKey,DefaultChannelParam processParam){
        //构建context
        ProcessContext<DefaultChannelParam> context = new ProcessContext<>();
        context.setProcessParam(processParam);
        context.setSubmitTime(Calendar.getInstance().getTime());
        //执行pipline
        CreditPipline<DefaultChannelParam> pipline = selectePipline(plKey);
        pipline.startProcessLine(context);
        return context;
    }
}
