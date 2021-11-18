package com.concurrency;

public class DeadlockExample {
    public static void run(){
        final String resource1 = "Stuck";
        final String resource2  = "Forever";

        Thread thread1 = new Thread(() -> {
            synchronized (resource1){
                System.out.println("Thread1 has a lock on resource1");

                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            synchronized (resource2){
                System.out.println("Thread1 has a lock on resource2");
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resource1){
                System.out.println("Thread2 has a lock on resource1");

                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            synchronized (resource2){
                System.out.println("Thread2 has a lock on resource2");
            }
        });

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        run();
    }
}
