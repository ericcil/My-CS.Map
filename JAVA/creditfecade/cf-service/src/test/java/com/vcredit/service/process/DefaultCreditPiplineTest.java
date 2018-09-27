package com.vcredit.service.process;

import com.vcredit.service.process.boot.DefaultCopyOnWritePipline;
import com.vcredit.service.process.dto.DefaultChannelParam;
import com.vcredit.service.process.dto.ProcessContext;

import java.util.concurrent.CyclicBarrier;

/**
 * 单线程功能测试
 */
public class DefaultCreditPiplineTest {

    private static DefaultCopyOnWritePipline<DefaultChannelParam> pipline;
    private static CyclicBarrier cb = new CyclicBarrier(11);

    public static void main(String[] args) {

        pipline = TestPiplineUtil.piplineInit();



        ProcessContext<DefaultChannelParam> context = new ProcessContext<>();
        DefaultChannelParam defaultChannelParam = new DefaultChannelParam();
        defaultChannelParam.setName("授信通道1");
        defaultChannelParam.setDescript("测试使用");
        context.setProcessParam(defaultChannelParam);
        pipline.startProcessLine(context);
        //pipline.fireProcess(2,context);

    }


}


