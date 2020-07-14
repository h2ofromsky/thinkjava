package com.conc.chapfour;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyWaiter {
    private static volatile boolean flag = true;
    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(new One());
        exec.submit(new Two());
        TimeUnit.SECONDS.sleep(3);
        exec.shutdown();

    }

    static class One implements Runnable{
        @Override
        public void run() {
            try{
                synchronized (MyWaiter.class){
                    while(flag){
                        System.out.println("waiting...");
                        MyWaiter.class.wait();
                    }
                    System.out.println("111111111");
                }
            }catch(InterruptedException e){
                System.out.println("one interrupted...");
            }
        }
    }
    static class Two implements Runnable{
        @Override
        public void run() {
            synchronized (MyWaiter.class){
                System.out.println("2222222");
                flag = false;
                MyWaiter.class.notifyAll();
            }
        }
    }
}
