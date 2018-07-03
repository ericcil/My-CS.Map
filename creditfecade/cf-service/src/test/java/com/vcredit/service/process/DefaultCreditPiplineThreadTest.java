package com.vcredit.service.process;

import com.vcredit.process.dto.ChannelParam1;
import com.vcredit.process.dto.ProcessContext;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程访问测试
 */
public class DefaultCreditPiplineThreadTest {

    private static DefaultCreditPipline<ChannelParam1> pipline;
    private static CyclicBarrier cb = new CyclicBarrier(11);

    private static CyclicBarrier closeCb = new CyclicBarrier(11);

    public static void main(String[] args) throws InterruptedException {

        pipline = TestPiplineUtil.piplineInit();

        ExecutorService es = Executors.newFixedThreadPool(11);

        for(int i=0;i<10;i++) {
            es.submit(new TestThread());
        }

        try {
            System.out.println(Thread.currentThread().getName() + "栅栏获取");
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
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



    static class TestThread extends Thread{

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "栅栏获取");
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }

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


