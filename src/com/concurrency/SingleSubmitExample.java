package com.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleSubmitExample {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static Future<Double> getRandom(int num){
        return executorService.submit(() -> {
            System.out.println(num);
            Thread.sleep(1000);
            return Math.random();
        });

    }

    public static void main(String[] args) {
        Future<Double> doubleFuture = getRandom(1);
        getRandom(2);
        getRandom(3);
        getRandom(4);
        while (!doubleFuture.isDone()){
            if(doubleFuture.isCancelled()){
                System.out.println("Your future was cancelled, we're sorry");
                break;
            }
        }
        try {
            System.out.println(doubleFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

}
