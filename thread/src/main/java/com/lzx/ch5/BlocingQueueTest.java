package com.lzx.ch5;


import java.util.concurrent.ArrayBlockingQueue;

/**
 * 堵塞队列
 * FIFO 先进先出, 集合框架，不是新的东西， list 和 set 同一个级别 queue  堵塞 和 非堵塞队列,同步队列
 * 1. 抛出异常
 * 2. 不会抛出异常
 * 3. 堵塞 等待
 * 4. 超时等待
 *
 */
public class BlocingQueueTest {

    public static void main(String[] args) {
        //test1();
        test2();

    }
    /**
     *  抛出异常
     */
    public static void test1(){
        //队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
        //队列满了， 插入抛出异常
        //System.out.println(arrayBlockingQueue.add("d"));
        //弹出元素
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        // 抛出异常 的弹出
        //System.out.println(arrayBlockingQueue.remove());

    }

    /**
     * 不抛出异常
     */
    public static void test2(){
        //队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        //检测队首的元素  peek 不抛出异常， element ，抛出异常。
        System.out.println(arrayBlockingQueue.peek());
        //队列满了， 插入不抛出异常 ,返回 false
        //System.out.println(arrayBlockingQueue.offer("d"));
        //弹出元素 ,
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        // 抛出异常 的弹出
        System.out.println(arrayBlockingQueue.poll());
    }
    // 查看队首的元素

    /**
     * 等待， 堵塞一直等待
     */
    public static void test3(){


    }
}
