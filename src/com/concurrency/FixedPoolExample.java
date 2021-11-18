package com.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedPoolExample {
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static Future<Double> getRandom(int num){
        return executorService.submit(() -> {
            Thread.sleep((int)(Math.random()*200));
            System.out.println(num + " Thread id: "+ Thread.currentThread().getId());
            return Math.random();
        });
    }

    public static void main(String[] args) {
        for(int i=0; i<100; i++){
            getRandom(i);
        }
        executorService.shutdown();
    }
}
