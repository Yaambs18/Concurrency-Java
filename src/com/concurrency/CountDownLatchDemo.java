package com.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch cd1 = new CountDownLatch(5);

        System.out.println("Starting");

        new Thread(new MyThread(cd1)).start();

        try{
            System.out.println("before latch closes");
            cd1.await();
            System.out.println("after latch opens");
        }
        catch (InterruptedException err){
            System.out.println(err.getMessage());
        }

        System.out.println("Done");
    }
}

class MyThread implements Runnable{
    CountDownLatch latch;

    MyThread(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        for(int i = 0; i<5; i++){
            System.out.println(i);
            latch.countDown();
        }
    }
}