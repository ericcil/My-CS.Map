package com.vcredit.service.process;

import com.vcredit.process.dto.ChannelParam1;
import com.vcredit.process.dto.ProcessContext;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultCreditPiplineSingleManagerTest {

    private static DefaultCreditPipline<ChannelParam1> pipline;


    private static CyclicBarrier closeCb = new CyclicBarrier(3);

    public static void main(String[] args) throws InterruptedException {

        pipline = TestPiplineUtil.piplineInit();

        ExecutorService es = Executors.newFixedThreadPool(2);



        es.submit(new TestThread());
        es.submit(new ManagerThread());

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
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("执行移除");
            pipline.remove(3);

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
            channelParam1.setName("授信通道1");
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


