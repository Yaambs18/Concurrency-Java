package com.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceWithInvokeAny {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<Callable<Integer>> callables = new ArrayList<>();
        callables.add(() -> 1);
        callables.add(() -> 2);

        try {
            Thread.sleep(100);
            System.out.println(executorService.invokeAny(callables));
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

}
