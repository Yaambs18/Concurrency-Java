package com.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapExample {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentSkipListMap<>();
        map.put("Yansh", "Python");
        map.put("Aditya", "Java");
        map.put("Nikhil", "Mern");
        map.put("Parminder", "MERN");
        map.put("Abhay", "MERN");

        for(String s : map.keySet()){
            System.out.println(s + ": "+ map.get(s));
        }
    }
}
