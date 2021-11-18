package com.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(new LockThread(lock, "A")).start();
        new Thread(new LockThread(lock, "B")).start();
    }
}

class LockThread implements Runnable{
    String name;
    ReentrantLock lock;

    LockThread(ReentrantLock lck, String nm){
        lock = lck;
        name = nm;
    }

    @Override
    public void run() {
        System.out.println("Starting "+name);

        try{
            System.out.println(name + " is waiting to lock count.");
            lock.lock();
            System.out.println(name + " is locking count");

            Shared.count++;
            System.out.println(name + ": " + Shared.count);

            System.out.println(name + " is sleeping.");
            Thread.sleep(100);
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println(name + " is unlocking count.");
            lock.unlock();
        }
    }
}
