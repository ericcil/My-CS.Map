package com.vcredit.service.process;

import com.alibaba.fastjson.JSON;
import com.vcredit.process.boot.SymmetricCreditStepCombin;
import com.vcredit.process.dto.ChannelParam1;
import com.vcredit.process.dto.ProcessContext;
import com.vcredit.service.process.step.Step5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultCreditPiplineManagerTest {

    private static DefaultCreditPipline<ChannelParam1> pipline;


    private static CyclicBarrier closeCb = new CyclicBarrier(12);

    public static void main(String[] args) throws InterruptedException {

        pipline = TestPiplineUtil.piplineInit();

        ExecutorService es = Executors.newFixedThreadPool(11);

        for(int i=0;i<5;i++) {
            Thread.sleep(200);
            es.submit(new TestThread());
        }
        es.submit(new ManagerThread());
        for(int i=0;i<5;i++) {
            Thread.sleep(200);
            es.submit(new TestThread());
        }
        //
        try {
            closeCb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        es.shutdown();
        //Thread.sleep(3);

    }


    static class ManagerThread extends  Thread{
        @Override
        public void run() {
            System.out.println("执行移除");
            pipline.remove(1);
            pipline.addCombin(2,new SymmetricCreditStepCombin<>(new Step5(), context -> {
                    System.out.println("step5回调结果==="+ JSON.toJSONString(context));
                })
            );

            try {
                closeCb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


    static class TestThread extends Thread{

        @Override
        public void run() {

            String threadName = Thread.currentThread().getName();
            ProcessContext<ChannelParam1> context = new ProcessContext<>();
            ChannelParam1 channelParam1 = new ChannelParam1();
            channelParam1.setName("授信通道"+threadName);
            channelParam1.setDescript(threadName);
            context.setProcessParam(channelParam1);
            pipline.startProcessLine(context);

            try {
                closeCb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}


