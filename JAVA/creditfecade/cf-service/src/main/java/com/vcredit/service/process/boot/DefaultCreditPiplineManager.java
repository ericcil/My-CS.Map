package com.vcredit.service.process.boot;

import com.vcredit.domain.CreditProcessDomain;
import com.vcredit.persistence.po.CreditConfig;
import com.vcredit.service.process.CentralizeStep;
import com.vcredit.service.process.CreditPipline;
import com.vcredit.service.process.Step;
import com.vcredit.service.process.dto.DefaultChannelParam;
import com.vcredit.service.process.dto.ProcessContext;
import enums.UseStatusEnum;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DefaultCreditPiplineManager {

    //用于存储已经注册初始化的pipline
    private static final transient Map<String,CreditPipline<DefaultChannelParam> >piplineMap = new ConcurrentHashMap<>(20);
    private static final transient Map<String,CentralizeStep> stepPool = new ConcurrentHashMap<>(20);


    @Autowired
    private CreditProcessDomain processDomain;

    @Autowired
    public void initiator(ApplicationContext applicationContext) {
        Map<String,CentralizeStep> cStepMap = applicationContext.getBeansOfType(CentralizeStep.class);
        cStepMap.forEach( (key,value) ->{
            stepPool.put(value.getStepName(),value);
        });
        piplineDBInitiator(processDomain);
    }

    /**
     * 从数据库初始化管道
     * @param processDomain
     */
    private void piplineDBInitiator(CreditProcessDomain processDomain){
        List<String> names = processDomain.queryExistsProjectProcessName();
        List<CreditConfig> creditConfigs;
        for(String name :names) {
            creditConfigs = processDomain.getFullConfigInfoByProject(name);
            reindexPiplineByDB(creditConfigs);
        }

    }


    /**
     * 根据给定配置刷新
     * @param creditConfigs
     * @return
     */
    public boolean reindexPiplineByDB(List<CreditConfig> creditConfigs){
        CreditPipline<DefaultChannelParam> newPipline = new DefaultCopyOnWritePipline<>();
        CreditConfig config;
        String plkey = null;
        for(int i=0,k=0,size=creditConfigs.size() ;i<size ;i++){
            config = creditConfigs.get(i);
            plkey = config.getProjectName();
            log.debug("====={}",config.toString());
            if(!stepExistsCheck(config.getStepName()) || !UseStatusEnum.INUSE.getCode().equals(config.getUseStatus()) ){
                log.debug("未添加==={}",config.toString());
                continue; //不存在步骤池中,非有效，无法添加
            }
            newPipline.addStep(k,stepPool.get(config.getStepName()));
            k++;
        }
        piplineMap.put(plkey,newPipline);
        return true;
    }

    private boolean stepExistsCheck(String nameWill){
        return stepPool.containsKey(nameWill);
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
    private CreditPipline selectePipline(String key){
        return piplineMap.get(key);
    }


    /**
     * 向管道内添加已经注册的步骤
     * @param plKey
     * @param index
     * @param stepKey
     */
    public boolean addStep(String plKey,int index,String stepKey){
        if(!stepExistsCheck(stepKey)) return false; //不存在步骤池中，无法添加
        CreditPipline<DefaultChannelParam> pipline = selectePipline(plKey);
        if(pipline == null){
            pipline = new DefaultCopyOnWritePipline<>();
            pipline.addStep(0,stepPool.get(stepKey));
            piplineMap.put(plKey,pipline);

        }else {
            pipline.addStep(index, stepPool.get(stepKey));
        }
        return true;
    }

    /**
     * 移除特定管道的特定步骤
     * @param key
     * @param index
     */
    public void removeStep(String key,int index){
        selectePipline(key).remove(index);
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
