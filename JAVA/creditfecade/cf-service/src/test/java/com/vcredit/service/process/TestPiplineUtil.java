package com.vcredit.service.process;

import com.alibaba.fastjson.JSON;
import com.vcredit.service.process.boot.DefaultCopyOnWritePipline;
import com.vcredit.service.process.dto.DefaultChannelParam;
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

    public static DefaultCopyOnWritePipline<DefaultChannelParam> piplineInit(){
        DefaultCopyOnWritePipline<DefaultChannelParam> pipline = new DefaultCopyOnWritePipline<>();

        List<Step<DefaultChannelParam>> list = new ArrayList<>();

        Step1 c1 = new Step1( context -> {
            System.out.println("step1回调结果==="+ JSON.toJSONString(context) ); });
        Step2 c2 = new Step2( context -> {
            System.out.println("step1回调结果==="+ JSON.toJSONString(context) ); });
        Step3 c3 = new Step3( context -> {
            System.out.println("step1回调结果==="+ JSON.toJSONString(context) ); });
        Step4 c4 = new Step4( context -> {
            System.out.println("step1回调结果==="+ JSON.toJSONString(context) ); });

        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        pipline.init(list);

        return pipline;
    }
}
