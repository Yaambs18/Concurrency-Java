package com.concurrency;

import java.util.concurrent.*;

public class CallableDemo2 {
    public static void main(String[] args) {
        ExecutorService es  = Executors.newFixedThreadPool(3);
        Future<Integer> f1;
        Future<Double> f2;
        Future<Integer> f3;

        System.out.println("Starting");

        f1 = es.submit(new Sum(10));
        f2 = es.submit(new Hypo(3,4));
        f3 = es.submit(new Factorial(5));

        try{
            System.out.println(f1.get(10, TimeUnit.MILLISECONDS));
            System.out.println(f2.get(10, TimeUnit.MILLISECONDS));
            System.out.println(f3.get(10, TimeUnit.MILLISECONDS));
        }
        catch (InterruptedException | ExecutionException | TimeoutException ex){
            System.out.println(ex.getMessage());
        }

        es.shutdown();
        System.out.println("Done");
    }
}
