package com.concurrency;

import java.util.concurrent.Phaser;

class MyPhaser extends Phaser{
    int numPhases;

    MyPhaser(int parties, int phasecount){
        super(parties);
        numPhases = phasecount -1;
    }

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        System.out.println("Phase "+ phase + " completed.\n");

        if(phase==numPhases || registeredParties==0)
            return true;
        return false;
    }
}

public class PhaserDemo2 {
    public static void main(String[] args) {
        MyPhaser phsr = new MyPhaser(1,4);

        System.out.println("Starrting");

        new Thread(new MyThread3(phsr, "A")).start();
        new Thread(new MyThread3(phsr, "B")).start();
        new Thread(new MyThread3(phsr, "C")).start();

        while (!phsr.isTerminated()){
            phsr.arriveAndAwaitAdvance();
        }
        System.out.println("The phaser is Terminated");
    }
}

class MyThread3 implements Runnable{
    Phaser phsr;
    String name;

    MyThread3(Phaser p, String n){
        phsr = p;
        name = n;
        phsr.register();
    }

    @Override
    public void run() {
        while (!phsr.isTerminated()){
            System.out.println("Thread "+ name + " Beginning Phase "+ phsr.getPhase());

            phsr.arriveAndAwaitAdvance();

            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
