package com.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class Summation extends RecursiveTask<Double> {
    final int seqThreshold = 500;

    double[] data;
    int start, end;

    Summation(double[] vals, int s, int e) {
        data = vals;
        start = s;
        end = e;
    }

    @Override
    protected Double compute() {
        double sum = 0;

        if((end-start)<seqThreshold){
            for(int i=start; i<end; i++)
                sum += data[i];
        }
        else {
            int middle = (start + end)/2;

            Summation subTaskA = new Summation(data, start, middle);
            Summation subTaskB = new Summation(data, middle, end);

            subTaskA.fork();
//            subTaskB.fork();

//            sum = subTaskA.join() + subTaskB.join();
            sum = subTaskB.invoke() + subTaskA.join();  // 2nd approach here we use invoke subTaskB to start and wait for it's execution
//            sum = subTaskB.compute() + subTaskA.join(); // another approach where we called directly compute method on B
        }
        return sum;
    }
}
public class RecursiveTaskExample {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();

        double[] nums = new double[5000];

        for(int i=0; i<nums.length; i++)
            nums[i] = (((i%2)==0) ? i: -i);

        Summation task = new Summation(nums, 0, nums.length);

        double summation = fjp.invoke(task);

        System.out.println("Summation "+ summation);
    }
}
