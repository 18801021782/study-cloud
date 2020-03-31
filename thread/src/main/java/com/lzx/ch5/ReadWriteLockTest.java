package com.lzx.ch5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：---
 * 读的时候,可被多个线程读取
 * 写入的时候可以 一个线程顺序写入---
 *  用synchd 和 lock 也可以解决，但是程序的细粒度不都
 *  读- 读  可以共存
 *  读- 写  不能共存
 *  写- 写  不能共存
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //写入
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "");
//                try {
//                    TimeUnit.SECONDS.sleep(1L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }, String.valueOf(i)).start();
        }
        //读取
        for (int k = 1; k <= 5; k++) {
            final int temp = k;
            new Thread(() -> {
                myCache.get(temp + "");
            }, String.valueOf(k)).start();
        }
    }
}

class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        //读写锁
        lock.writeLock().lock();
        try {
            //synchronized (map) {
            System.out.println(Thread.currentThread().getName() + "开始写入" + key);
            map.put(key, value);
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "写入完毕");
          //  }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    // 读 所有人都可以读
    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读开始" + key);
            map.get(key);
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "读完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }

    }
}
