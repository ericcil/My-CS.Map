package com.vcredit.service.process;

import com.alibaba.fastjson.JSON;
import com.vcredit.process.CreditStep;
import com.vcredit.process.boot.SymmetricCreditStepCombin;
import com.vcredit.process.boot.SymmetricCreditPipline;
import com.vcredit.process.dto.ProcessContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @Author chenyubin
 * @Date 2018/7/2
 */
public class DefaultCreditPipline<T> implements SymmetricCreditPipline<T> {

    private Logger logger = LoggerFactory.getLogger(DefaultCreditPipline.class);

    private final  transient CopyOnWriteArrayList<SymmetricCreditStepCombin<T>> combins = new CopyOnWriteArrayList();

    private final transient ReentrantLock lock = new ReentrantLock();

    public boolean init(List<SymmetricCreditStepCombin<T>> combinList){
        clean();
        addAllCombin(combinList);
        return true;
    }

    @Override
    public List<CreditStep<T>> getCreditStep() {
        List<CreditStep<T>> resultList = new ArrayList<>(combins.size());
        if(combins.size() == 0 ){
            return resultList;
        }
        List objs = Arrays.asList(combins.toArray());

        for(Object obj:objs){
            SymmetricCreditStepCombin combin = (SymmetricCreditStepCombin)obj;
            resultList.add(combin.getCreditStep());
        }
        return resultList;
    }

    @Override
    public boolean addAllCombin(List<SymmetricCreditStepCombin<T>> combinList) {
        Assert.notEmpty(combinList,"入参授信步骤至少要包含一个元素");
        combins.addAll(combinList);
        return true;
    }

    @Override
    public boolean addCombin(int index, SymmetricCreditStepCombin<T> combin) {
        Assert.notNull(combin,"步骤不能为空");
        if(index < 0 ) throw new ArrayIndexOutOfBoundsException();
        combins.add(index,combin);
        return false;
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
    public void startProcessLine(ProcessContext<T> context) {
        context.setReceiveTime(Calendar.getInstance().getTime());
        fireProcess(0,context);
        context.setSubmitTime(Calendar.getInstance().getTime());
        logger.info("流程完全结束==={}", JSON.toJSONString(context));
    }

    @Override
    public void fireProcess(int index, ProcessContext<T> context) {
        Long begin = System.currentTimeMillis();
        List<SymmetricCreditStepCombin<T>> combinsCopy = (List<SymmetricCreditStepCombin<T>>)combins.clone();
        System.out.println("clon耗时====="+ (System.currentTimeMillis() - begin) );
        Assert.notEmpty(combins,"授信流程必须含有至少一个步骤");
        CreditStep<T> step = null;
        SymmetricCreditStepCombin<T> combin = null;
        for(int i = index,copySize = combinsCopy.size(); i< copySize ; i++ ){
            combin = combinsCopy.get(i);
            step = combin.getCreditStep();
            context.setCurrentIndex(i + 1);
            step.executeStep(context);
            //中间可以执行其他操作....
            //callbackList.get(i).afterExecute(context);
            step.fireCallback(context,combin.getCallback());
            if(context.isProcessInterrupt()){
                context.setSubmitTime(Calendar.getInstance().getTime());
                logger.info("结束流程index = {}，{}",i,step.getStepName());
                return;
            }
        }
    }
}
