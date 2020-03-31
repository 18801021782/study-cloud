package com.lzx.ch5;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列：
 * 进去一个元素，必须等待取出来之后， 才能在往里面放一个元素。 一个线程必须放进去，一个取出来，再来一个线程取值就会堵塞
 * 操作： put   take
 */
public class SynchronousQueueTest {


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingqueue = new SynchronousQueue<String>();
        //一个线程写入， 一个线程拿取
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" put 1");
                blockingqueue.put("1");
                TimeUnit.SECONDS.sleep(90000000);
                System.out.println(Thread.currentThread().getName()+" put 2");
                blockingqueue.put("2");
                System.out.println(Thread.currentThread().getName()+" put 3");
                blockingqueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();
        // 取的线程
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"->"+ blockingqueue.take());
                System.out.println(Thread.currentThread().getName()+"->"+ blockingqueue.take());
                System.out.println(Thread.currentThread().getName()+"->"+ blockingqueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        },"T2").start();
        //
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"->"+ blockingqueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        },"T3").start();


        System.out.println(blockingqueue.take());
    }
}
