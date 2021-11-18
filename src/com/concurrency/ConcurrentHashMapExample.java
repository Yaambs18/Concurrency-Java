package com.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("Yansh", "Python");
        map.put("Aditya", "Java");
        map.put("Nikhil", "Mern");
        map.put("Parminder", "MERN");
        map.put("Abhay", "MERN");

        for(String k : map.keySet()){
            System.out.println(k + " loves coding "+ map.get(k));
            map.remove(k);
        }


    }
}
