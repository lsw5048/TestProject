package com.jd.queue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    private List<Integer> dataList;

    public Producer(BlockingQueue queue, List<Integer> dataList) {
        this.queue = queue;
        this.dataList = dataList;
    }

    @Override
    public void run() {
        Integer data = 0;
        Random r = new Random();

        System.out.println("启动生产者线程！");
        try {
            while (isRunning) {
                System.out.println("正在生产数据...");

                if(count.get()==dataList.size()){
                    isRunning = false;
                    System.out.println("生产者线程停止生产");
                    break;
                }
                data = dataList.get(count.getAndIncrement());
                System.out.println("将数据：" + data + "放入队列...");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("放入数据失败：" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出生产者线程！");
        }
    }

    public void stop() {
        isRunning = false;
    }

    private volatile boolean      isRunning               = true;
    private BlockingQueue queue;
    private static AtomicInteger count                   = new AtomicInteger();
    private static final int      DEFAULT_RANGE_FOR_SLEEP = 1000;

}
