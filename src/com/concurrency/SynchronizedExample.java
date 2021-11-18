package com.concurrency;

public class SynchronizedExample {
    public static int counter = 0;
    public static Object lock = new Object();

    public static void incrementCounter(){
        synchronized (lock){
            int current = counter;
            System.out.println("Before: "+counter + " Current Thread: "+Thread.currentThread().getId());
            counter = current+1;
            System.out.println("After: "+counter + " Current Thread: "+Thread.currentThread().getId());
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<10; i++){
            Thread t = new Thread(() -> incrementCounter());
            t.start();
        }
    }
}
