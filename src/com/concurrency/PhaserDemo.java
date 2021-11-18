package com.concurrency;

import java.util.concurrent.Phaser;

public class PhaserDemo {
    public static void main(String[] args) {
        Phaser phsr = new Phaser(1);
        int curPhase;

        System.out.println("Starting");

        new Thread(new MyThread2(phsr, "A")).start();
        new Thread(new MyThread2(phsr, "B")).start();
        new Thread(new MyThread2(phsr, "C")).start();

        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Phase "+curPhase + " Completed");

        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Phase "+curPhase + " Completed");

        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Phase "+curPhase + " Completed");

        phsr.arriveAndDeregister();

        if(phsr.isTerminated())
            System.out.println("The Phaser is terminated");
    }
}

class  MyThread2 implements Runnable{
    Phaser phsr;
    String name;

    MyThread2(Phaser p, String n){
        phsr = p;
        name = n;
        phsr.register();
    }

    @Override
    public void run() {
        System.out.println("Thread "+ name + " Beginning Phase One");
        phsr.arriveAndAwaitAdvance();

        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread "+ name + " Beginning Phase Two");
        phsr.arriveAndAwaitAdvance();

        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread "+ name + " Beginning Phase Three");
        phsr.arriveAndDeregister();
    }
}
