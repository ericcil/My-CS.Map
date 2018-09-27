package com.vcredit.service.process;

import com.vcredit.service.process.boot.DefaultCopyOnWritePipline;
import com.vcredit.service.process.dto.DefaultChannelParam;
import com.vcredit.service.process.dto.ProcessContext;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程访问测试
 */
public class DefaultCreditPiplineThreadTest {

    private static DefaultCopyOnWritePipline<DefaultChannelParam> pipline;
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


