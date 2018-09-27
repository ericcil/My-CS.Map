package com.vcredit.service.process.boot;

import com.alibaba.fastjson.JSON;
import com.vcredit.service.process.CreditPipline;
import com.vcredit.service.process.Step;
import com.vcredit.service.process.dto.ProcessContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 默认对称管道实现
 * @Author chenyubin
 * @Date 2018/7/2
 */
public final class DefaultCopyOnWritePipline<P> implements CreditPipline<P> {

    private Logger logger = LoggerFactory.getLogger(DefaultCopyOnWritePipline.class);

    private final  transient CopyOnWriteArrayList<Step<P>> combins = new CopyOnWriteArrayList();

    public boolean init(List<Step<P>> combinList){
        clean();
        addAllStep(combinList);
        return true;
    }

    @Override
    public boolean addAllStep(List<Step<P>> combinList) {
        Assert.notEmpty(combinList,"入参授信步骤至少要包含一个元素");
        combins.addAll(combinList);
        return true;
    }

    @Override
    public boolean addStep(int index, Step<P> combin) {
        Assert.notNull(combin,"步骤不能为空");
        if(index < 0 ) throw new ArrayIndexOutOfBoundsException();
        combins.add(index,combin);
        return false;
    }

    @Override
    public List<Step<P>> getAllStep() {
        return (List<Step<P>>)combins.clone();
    }


    @Override
    public void clean() {
        combins.clear();
    }

    @Override
    public boolean remove(int index) {
        combins.remove(index);
        return true;
    }

    @Override
    public void startProcessLine(ProcessContext<P> context) {
        context.setReceiveTime(Calendar.getInstance().getTime());
        fireProcess(0,context);
        context.setSubmitTime(Calendar.getInstance().getTime());
        logger.debug("流程完全结束==={}", JSON.toJSONString(context));
    }

    @Override
    public void fireProcess(int index, ProcessContext<P> context) {
        List<Step<P>> combinsCopy = (List<Step<P>>)combins.clone();
        Assert.notEmpty(combins,"授信流程必须含有至少一个步骤");
        Step<P> step = null;
        for(int i = index,copySize = combinsCopy.size(); i< copySize ; i++ ){
            step = combinsCopy.get(i);
            context.setCurrentIndex(i + 1);
            step.executeStep(context);
            //中间可以执行其他操作....
            step.fireCallback(context);
            if(context.isProcessInterrupt()){
                context.setSubmitTime(Calendar.getInstance().getTime());
                logger.info("结束流程index = {}，{}",i,step.getStepName());
                return;
            }
        }
    }
}
