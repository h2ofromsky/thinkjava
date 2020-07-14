package com.conc.chapfour;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Priority {
    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;
    public static void main(String[] args) throws Exception {
        List<Job> jobs = new ArrayList<>();
        for(int i = 0;i<10;i++){
            int pri = i<5?Thread.MIN_PRIORITY:Thread.MAX_PRIORITY;
            Job job = new Job(pri);
            jobs.add(job);
            Thread thread = new Thread(job);
            thread.setPriority(pri);
            thread.start();
        }
        System.out.println("start...");
        notStart = false;
        TimeUnit.SECONDS.sleep(5);
        notEnd = false;
        System.out.println("end...");
        for(Job job:jobs){
            System.out.println(job.count+"..."+job.priority);
        }
    }
    static class Job implements Runnable{
        private int priority;
        private long count;
        public Job(int priority){
            this.priority = priority;
        }
        @Override
        public void run() {
            while(notStart){
                Thread.yield();
            }
            while(notEnd){
                count++;
                Thread.yield();
            }
        }
    }
}

