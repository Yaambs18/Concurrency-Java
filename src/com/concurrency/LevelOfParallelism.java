package com.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class Transform extends RecursiveAction{
    int seqThreshold;

    double[] data;
    int start, end;

    Transform(double[] vals, int s, int e, int t){
        data = vals;
        start = s;
        end = e;
        seqThreshold = t;
    }

    @Override
    protected void compute() {
        if((end-start)<seqThreshold){
            for(int i=start; i<end; i++){
                if((data[i]%2)==0)
                    data[i] = Math.sqrt(data[i]);
                else
                    data[i] = Math.cbrt(data[i]);
            }
        }
        else{
            int middle = (start + end)/2;

            invokeAll(new Transform(data, start, middle, seqThreshold), new Transform(data, middle, end, seqThreshold));
        }
    }
}
public class LevelOfParallelism {
    public static void main(String[] args) {
        int pLevel;
        int threshold;

        if(args.length!=2){
            System.out.println("Usage: ForkJoin parallelism threshold");
            return;
        }
        pLevel = Integer.parseInt(args[0]);
        threshold = Integer.parseInt(args[1]);

        long beginT, endT;

        ForkJoinPool fjp = new ForkJoinPool(pLevel);

        double[] nums = new double[100000];

        for(int i=0;i<nums.length;i++)
            nums[i] = i;

        Transform task = new Transform(nums, 0, nums.length, threshold);

        beginT = System.nanoTime();

        fjp.invoke(task);

        endT = System.nanoTime();

        System.out.println("Level of parallelism: "+ pLevel);
        System.out.println("No. of processors: "+ Runtime.getRuntime().availableProcessors());
//        System.out.println("Level of parallelism: "+ fjp.getParallelism());  // it returns the parallelism level  & if used with default ForkJoinPool then return total number of processors.
        System.out.println("Sequential threshold: "+ threshold);
        System.out.println("Ellapsed time: "+ (endT-beginT) + " ms");
        System.out.println();
    }
}
