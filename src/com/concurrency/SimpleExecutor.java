package com.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleExecutor {
    public static void main(String[] args) {
        CountDownLatch cdl1 = new CountDownLatch(5);
        CountDownLatch cdl2 = new CountDownLatch(5);
        CountDownLatch cdl3 = new CountDownLatch(5);
        CountDownLatch cdl4 = new CountDownLatch(5);
        ExecutorService exs = Executors.newFixedThreadPool(2);

        System.out.println("Starting");

        exs.execute(new MiThread(cdl1, "A"));
        exs.execute(new MiThread(cdl2, "B"));
        exs.execute(new MiThread(cdl3, "C"));
        exs.execute(new MiThread(cdl4, "D"));

        try {
            cdl1.await();
            cdl2.await();
            cdl3.await();
            cdl4.await();
        }
        catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }

        exs.shutdown();
        System.out.println("Done");
    }
}

class MiThread implements  Runnable{
    String name;
    CountDownLatch latch;

    MiThread(CountDownLatch latch, String name){
        this.latch = latch;
        this.name = name;

    }

    @Override
    public void run() {
        for(int i=0; i<5; i++){
            System.out.println(name + ": "+ i);
            latch.countDown();
        }
    }
}

