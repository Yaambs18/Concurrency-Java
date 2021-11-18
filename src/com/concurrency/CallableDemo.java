package com.concurrency;

import java.util.concurrent.*;

public class CallableDemo {
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
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
        }
        catch (InterruptedException | ExecutionException ex){
            System.out.println(ex.getMessage());
        }

        es.shutdown();
        System.out.println("Done");
    }
}

class Sum implements Callable<Integer> {
    int stop;

    Sum(int num){
        stop = num;
    }

    @Override
    public Integer call() throws Exception {
        int sum =0;
        for(int i=1; i<=stop;i++){
            sum+=i;
        }
        return sum;
    }
}

class Hypo implements Callable<Double>{
    double side1, side2;

    Hypo(double s1, double s2){
        side1 = s1;
        side2 = s2;
    }

    @Override
    public Double call() throws Exception {
        return Math.sqrt((side1*side1)+(side2*side2));
    }
}

class Factorial implements Callable<Integer>{
    int stop;

    Factorial(int num){
        stop = num;
    }

    @Override
    public Integer call() throws Exception {
        int fact = 1;
        for(int i=2; i<=stop;i++){
            fact *=i;
        }
        return fact;
    }
}