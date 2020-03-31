package com.lzx.ch5;


/**
 *  饿汉式单例模式
 *  弊端：如果这里面存在大量的数组，初始化需要占用大量的内存
 */
public class SingletonTest1 {

     /*int[] ss= new int[20];
     int[] s= new int[20];
     int[] ssss= new int[20];
     int[] sss= new int[20];*/

     private SingletonTest1(){


     }

     private final static SingletonTest1 singletonTest = new SingletonTest1();

     private static SingletonTest1  getInstance(){
         return singletonTest;
     }

}
