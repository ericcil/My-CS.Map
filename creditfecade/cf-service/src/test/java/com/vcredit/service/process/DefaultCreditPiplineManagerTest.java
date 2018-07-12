package com.vcredit.service.process;

import com.alibaba.fastjson.JSON;
import com.vcredit.service.process.boot.DefaultCopyOnWritePipline;
import com.vcredit.service.process.dto.DefaultChannelParam;
import com.vcredit.service.process.dto.ProcessContext;
import com.vcredit.service.process.step.Step5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程移除，添加步骤测试
 */
public class DefaultCreditPiplineManagerTest {

    private static DefaultCopyOnWritePipline<DefaultChannelParam> pipline;


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
            //pipline.remove(1);
            pipline.addStep(2,new Step5(context -> {
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
            ProcessContext<DefaultChannelParam> context = new ProcessContext<>();
            DefaultChannelParam defaultChannelParam = new DefaultChannelParam();
            defaultChannelParam.setName("授信通道"+threadName);
            defaultChannelParam.setDescript(threadName);
            context.setProcessParam(defaultChannelParam);
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


