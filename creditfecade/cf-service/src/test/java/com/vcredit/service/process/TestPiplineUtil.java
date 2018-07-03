package com.vcredit.service.process;

import com.alibaba.fastjson.JSON;
import com.vcredit.process.boot.SymmetricCreditStepCombin;
import com.vcredit.process.dto.ChannelParam1;
import com.vcredit.service.process.step.Step1;
import com.vcredit.service.process.step.Step2;
import com.vcredit.service.process.step.Step3;
import com.vcredit.service.process.step.Step4;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author chenyubin
 * @Date 2018/7/3
 */
public class TestPiplineUtil {

    public static DefaultCreditPipline<ChannelParam1> piplineInit(){
        DefaultCreditPipline<ChannelParam1> pipline = new DefaultCreditPipline<>();
        SymmetricCreditStepCombin<ChannelParam1> c1 = new SymmetricCreditStepCombin<>(new Step1(), context -> {
            System.out.println("step1回调结果==="+ JSON.toJSONString(context));
        });
        SymmetricCreditStepCombin<ChannelParam1> c2 = new SymmetricCreditStepCombin<>(new Step2(), context -> {
            System.out.println("step2回调结果==="+ JSON.toJSONString(context));
        });
        SymmetricCreditStepCombin<ChannelParam1> c3 = new SymmetricCreditStepCombin<>(new Step3(), context -> {
            System.out.println("step3回调结果==="+ JSON.toJSONString(context));
        });
        SymmetricCreditStepCombin<ChannelParam1> c4 = new SymmetricCreditStepCombin<>(new Step4(), context -> {
            System.out.println("step4回调结果==="+ JSON.toJSONString(context));
        });

        List<SymmetricCreditStepCombin<ChannelParam1>> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        pipline.init(list);


        /*Step1 step1 = new Step1();
        Step2 step2 = new Step2();
        Step3 step3 = new Step3();
        Step4 step4 = new Step4();

        List<CreditStep> stepList = new ArrayList<>();
        stepList.add(step1);
        stepList.add(step2);
        stepList.add(step3);
        stepList.add(step4);

        List<AfterStepCallback> callbacks = new ArrayList<>();
        callbacks.add( context -> {
            //context.setProcessInterrupt(true);
            System.out.println("step1回调结果==="+ JSON.toJSONString(context));
        });
        callbacks.add( context -> {
            System.out.println("step2回调结果==="+ JSON.toJSONString(context));
        });
        callbacks.add( context -> {
            System.out.println("step3回调结果==="+ JSON.toJSONString(context));
        });
        callbacks.add( context -> {
            System.out.println("step4回调结果==="+ JSON.toJSONString(context));
        });

        pipline.init(stepList,callbacks);
        return pipline;*/
        return pipline;
    }
}
