package com.concurrency;

import java.util.concurrent.*;

public class ScheduledExecutorServiceExample {
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(40);

    public static void main(String[] args) {
        Future<Double> doubleFuture = executorService.schedule(() -> {
            Thread.sleep((int) (Math.random() * 200));
            System.out.println(1 + " Thread id: " + Thread.currentThread().getId());
            return Math.random();
        }, 1000, TimeUnit.MILLISECONDS);

        try {
            System.out.println(doubleFuture.get());
//            System.out.println(doubleFuture.get(100, TimeUnit.MILLISECONDS));
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();

        }
//        catch (TimeoutException e){
//            e.printStackTrace();
//            doubleFuture.cancel(false);
//        }

        if(doubleFuture.isCancelled())
            System.out.println("Sorry, Future is cancelled");

        if(doubleFuture.isDone())
            System.out.println("I'm done.");

        executorService.shutdown();
    }
}
