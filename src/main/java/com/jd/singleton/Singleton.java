package com.jd.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Singleton {
    private Singleton(){
        System.out.println("creating Singleton");
    }
    private static class SingletonHandler{
        private static Singleton singleton = new Singleton();
        private SingletonHandler(){
            System.out.println("creating SingletonHandler");
        }
        static{
            System.out.println("initializing SingletonHandler");
        }
    }
    public static Singleton getInstance(){
        return SingletonHandler.singleton;
    }

    public static void display(){
        System.out.println("this is display");
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool =Executors.newFixedThreadPool(5);
        for (int i = 0; i < 15; i++) {
            Thread.sleep(20);
            threadPool.submit(()-> {System.out.println(Thread.currentThread().getName()+"获取实例"+Singleton.getInstance());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();

        /*Singleton.display();*/

    }
}
