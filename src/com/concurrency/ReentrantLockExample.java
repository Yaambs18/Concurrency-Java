package com.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    public static int counter = 0;
    public static Lock lock = new ReentrantLock();

    public static void incrementCounter(){
        try{
            lock.lock();
            int current = counter;
            System.out.println("Before: "+counter + " Current Thread: "+Thread.currentThread().getId());
            counter = current+1;
            System.out.println("After: "+counter + " Current Thread: "+Thread.currentThread().getId());
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<10; i++){
            Thread t = new Thread(() -> incrementCounter());
            t.start();
        }
    }
}
