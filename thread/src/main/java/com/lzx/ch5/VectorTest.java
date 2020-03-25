package com.lzx.ch5;

import sun.text.resources.cldr.lag.FormatData_lag;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class VectorTest {
    public static void main(String[] args) {
//        List<String> strings = Arrays.asList("1", "2", "3");
        /**
         * 线程安全的list
         */
        //List<String> list = new Vector<>();
        /**
         *  juc下面的的包(线程安全)
         */
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i <10 ; i++) {
             new Thread(()->{
                 list.add(UUID.randomUUID().toString().substring(0,5));
                 System.out.println(list);
             },String.valueOf(i)).start();
        }
    }
}
