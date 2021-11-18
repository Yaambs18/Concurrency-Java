package com.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());

        System.out.println("Starting");

        new Thread(new MyThread1(cb, "A")).start();
        new Thread(new MyThread1(cb, "B")).start();
        new Thread(new MyThread1(cb, "C")).start();

        new Thread(new MyThread1(cb, "X")).start();  // reuse of CyclicBarrier
        new Thread(new MyThread1(cb, "Y")).start();
        new Thread(new MyThread1(cb, "Z")).start();
    }
}

class MyThread1 implements Runnable{
    CyclicBarrier cbar;
    String name;

    MyThread1(CyclicBarrier cb, String name){
        cbar = cb;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);

        try{
            cbar.await();
        }
        catch (BrokenBarrierException err1){
            System.out.println(err1.getMessage());
        }
        catch (InterruptedException err2){
            System.out.println(err2.getMessage());
        }
    }
}

class BarAction implements Runnable{
    @Override
    public void run() {
        System.out.println("Barrier Reached!");
    }
}