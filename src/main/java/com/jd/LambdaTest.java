package com.jd;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class LambdaTest {
    private static final int MAX_THREADS = 5;
    private static final int CORE_POOL_SIZE = 3;

    @Test
    public void test(){
        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
            }
        };*/

        /*new Thread(()->{
            System.out.println("111");
        }).start();
        Supplier<Integer> supplier = ()->{return 1;};*/
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        long start = System.currentTimeMillis();
        ThreadPoolExecutor executor = buildThreadPool();

        /*list.forEach(integer -> {
            try {
                System.out.println(Thread.currentThread().getName()+"----"+integer);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/
        System.out.println("总耗时1："+(System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        list.forEach(integer -> {
            CompletableFuture.runAsync(()->{try {
                System.out.println(Thread.currentThread().getName()+"----"+integer);
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }},executor).whenComplete((result,ex)->{
                System.out.println(Thread.currentThread().getName()+"执行完成");
            });
        });
        System.out.println("总耗时2："+(System.currentTimeMillis() - start));
        //List<Integer> collect = list.stream().map(i -> i + 1).collect(Collectors.toList());
        /*List<Integer> collect = list.stream().map(i -> CompletableFuture.supplyAsync(() -> {
            return i + 1;
        }, executor)).map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println("总耗时："+(System.currentTimeMillis() - start));

        collect.forEach(i -> System.out.println(i));*/
        /*try {
            //主线程等待子线程运行完
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

    private ThreadPoolExecutor buildThreadPool() {
        ThreadFactory threadFactory = new ThreadFactory(){


            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return  thread;
            }
        };


        RejectedExecutionHandler rejectedExecutionHandler = (r, executor) -> {
            long taskCount = executor.getTaskCount();
            System.out.println("线程池满了" + taskCount);
        };

        return new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_THREADS,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2),
                //threadFactory,
                //rejectedExecutionHandler
                //ThreadPoolExecutor.CallerRunsPolicy
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    @Test
    public void testMap(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        List<Integer> collect = list.stream().map(i -> {
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            return i + 1;
        }).collect(Collectors.toList());
    }
}
