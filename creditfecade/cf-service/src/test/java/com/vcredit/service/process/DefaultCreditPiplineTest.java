package com.vcredit.service.process;

import com.vcredit.process.dto.ChannelParam1;
import com.vcredit.process.dto.ProcessContext;

import java.util.concurrent.CyclicBarrier;

/**
 * 单线程功能测试
 */
public class DefaultCreditPiplineTest {

    private static DefaultCreditPipline<ChannelParam1> pipline;
    private static CyclicBarrier cb = new CyclicBarrier(11);

    public static void main(String[] args) {

        pipline = TestPiplineUtil.piplineInit();



        ProcessContext<ChannelParam1> context = new ProcessContext<>();
        ChannelParam1 channelParam1 = new ChannelParam1();
        channelParam1.setName("授信通道1");
        channelParam1.setDescript("测试使用");
        context.setProcessParam(channelParam1);
        pipline.startProcessLine(context);
        //pipline.fireProcess(2,context);

    }


}


