package com.concurrency;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo extends Thread {
    static CopyOnWriteArrayList<String> l = new CopyOnWriteArrayList<>();
    public void run()
    {
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            System.out.println("Child Thread going to add element");
        }

        l.add("D");
    }

    public static void main(String[] args) throws InterruptedException {
        l.add("A");
        l.add("B");
        l.add("C");

        CopyOnWriteArrayListDemo t = new CopyOnWriteArrayListDemo();
        t.start();

        for (String s : l) {
            System.out.println(s);
            Thread.sleep(6000);
        }
        System.out.println(l);
    }
}
