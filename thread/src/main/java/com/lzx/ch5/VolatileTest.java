package com.lzx.ch5;

import java.util.concurrent.TimeUnit;

/**
 * volatile：
 *  可见性，非原子性
 *  JMM,内存的工作模型，主内存和工作内存，和执行引擎，每个线程有自己的工作内存和执行引擎
 *  多线程情况下：一个线程修改值，同步到主内存，而另一个线程引用但是工作内存还是使用自己的变量赋值
 */
public class VolatileTest {

    //两个线程一个main线程， 一个是A线程，A线程 拿不到最新主内存的变量值， 休眠2s,main 已经改变值，放在主内存里面了
    // volatile 是可以保证主内存的透明， 可见性,不加 volatile 会死循环
    private  volatile static int num  = 0 ;

    public static void main(String[] args)  {
        new Thread(()->{ // 线程A
            while(num == 0){

            }
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num =1;

        System.out.println(num);

    }
}
