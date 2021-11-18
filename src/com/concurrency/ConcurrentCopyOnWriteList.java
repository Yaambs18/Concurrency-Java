package com.concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCopyOnWriteList {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        list.add("Tim");
        list.add("Pascal");
        list.add("Elias");

        for (String s : list) {
            System.out.println(s);
            list.add(s);
        }

        System.out.println(list);
    }
}
