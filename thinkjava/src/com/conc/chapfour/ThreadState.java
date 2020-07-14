package com.conc.chapfour;

public class ThreadState {
    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
    }
    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while(true){
                SleepUtils.second(100);
            }
        }
    }
    static class Waiting implements Runnable{

        @Override
        public void run() {
            while(true){
                synchronized (Waiting.class){
                    try{
                        Waiting.class.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    static class Blocked implements Runnable{

        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtils.second(100);
                }
            }
        }
    }
}
