package com.lzx.ch5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyThread thread = new MyThread();
        //适配类
        FutureTask futureTask = new FutureTask(thread);
        new Thread(futureTask,"A").start();

        Integer o = (Integer) futureTask.get();
        System.out.println(o);

    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call()  {

        System.out.println();
        return 1024;
    }
}

