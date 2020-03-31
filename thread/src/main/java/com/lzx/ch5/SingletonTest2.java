package com.lzx.ch5;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 懒汉式单例模式
 * 懒加载，lazy
 */
public class SingletonTest2 {

    private boolean qinjiang = true;

    private SingletonTest2() {
        System.out.println(Thread.currentThread().getName()+" OK");
    }
    // 这个new 对象有可能发生指令重排 ， 所以加上volatile
    private volatile static SingletonTest2 lazyMan ;

    private static SingletonTest2 getInstance() {
       // return lazyMan;
        if(lazyMan == null){
            synchronized (SingletonTest2.class){
                if(lazyMan == null){
                    lazyMan = new SingletonTest2();
                    //大致过程如下：
                    //分配内存空间
                    //执行构造方法，初始化对象
                    //把这个对象指向分配的内存空间
                }
            }
        }
        return lazyMan;
    }

    // 多线程是有问题的， 单线程是OK 的
    public static void main(String[] args) throws Exception {
        //多线程测试 调用是否单例
        /*for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonTest2.getInstance();
            }).start();
        }*/
      //SingletonTest2.getInstance();
        // 验证反射是否相等
        SingletonTest2 instance1 = SingletonTest2.getInstance();
        //反射拿到对象, 拿到构造器
        Constructor<SingletonTest2> declaredConstructor = SingletonTest2.class.getDeclaredConstructor(null);
        //这个方法是将 类里面原来私有方法，变成public，无视私有的构造器
        declaredConstructor.setAccessible(true);
        SingletonTest2 instance2 = declaredConstructor.newInstance();
        // 结论：反射可以破坏单例
        System.out.println(instance1 == instance2);


        // 反射拿到声明的字段
        Field qinjiang = lazyMan.getClass().getDeclaredField("qinjiang");
        //破坏字段private
        qinjiang.setAccessible(true);
        //将某个具体的对象，字段赋值
        qinjiang.set(instance2,"123");


    }





}
