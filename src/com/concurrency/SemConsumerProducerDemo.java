package com.concurrency;

import java.util.concurrent.Semaphore;

class SemConsumerProducerDemo {
    int n;

    static Semaphore semCon = new Semaphore(0);
    static Semaphore semProd = new Semaphore(1);
    void get(){
        try{
            semCon.acquire();
        }
        catch(InterruptedException e){
            System.out.println("InterruptedException caught");
        }

        System.out.println("Got: " + n);
        semProd.release();
    }

    void put(int n){
        try{
            semProd.acquire();
        }
        catch (InterruptedException e){
            System.out.println("InterruptedException caught");
        }

        this.n = n;
        System.out.println("Put: " + n);
        semCon.release();
    }
}

class Producer implements Runnable {
    SemConsumerProducerDemo semConsumerProducerDemo;

    Producer(SemConsumerProducerDemo q){
        this.semConsumerProducerDemo = q;
    }

    public void run(){
        for(int i=0; i<20; i++) semConsumerProducerDemo.put(i);
    }
}

class Consumer implements Runnable{
    SemConsumerProducerDemo q;

    Consumer(SemConsumerProducerDemo q){
        this.q = q;
    }

    @Override
    public void run() {
        for(int i=0; i<20; i++) q.get();
    }
}

class ProdCon {
    public static void main(String[] args) {
        SemConsumerProducerDemo semConsumerProducerDemo = new SemConsumerProducerDemo();
        new Thread(new Consumer(semConsumerProducerDemo), "Consumer").start();
        new Thread(new Producer(semConsumerProducerDemo), "Producer").start();
    }
}

