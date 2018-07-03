package com.vcredit.service.process.step;

import com.vcredit.process.CreditStep;
import com.vcredit.process.dto.ChannelParam1;
import com.vcredit.process.dto.ProcessContext;
import com.vcredit.process.dto.StepResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试类
 * @Author chenyubin
 * @Date 2018/7/3
 */
public class Step2 implements CreditStep<ChannelParam1> {

    Logger logger = LoggerFactory.getLogger(Step2.class);
    private final String key = Step2.class.getName();

    @Override
    public void executeStep(ProcessContext<ChannelParam1> context) {
        logger.info("{}执行=====",key);
        StepResult s = new StepResult();
        s.setMsg(key+"执行完成");
        context.setCurrentStepResult(s);
        context.setProcessInterrupt(false);
        ChannelParam1 param = context.getProcessParam();
        param.setCount(param.getCount() + 1);
        try {
            Thread.sleep(900); //模拟耗时操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void fireCallback(ProcessContext<ChannelParam1> context, AfterStepCallback callback) {

    }*/

    @Override
    public String getStepName() {
        return key;
    }
}
