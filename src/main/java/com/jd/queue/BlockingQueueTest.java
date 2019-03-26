package com.jd.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> dataList = new ArrayList<>();
        initDataList(dataList);
        // 声明一个容量为10的缓存队列
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(10);

        Producer producer1 = new Producer(queue,dataList);
        Producer producer2 = new Producer(queue,dataList);
        Producer producer3 = new Producer(queue,dataList);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);

        // 借助Executors
        ExecutorService service = Executors.newCachedThreadPool();
        // 启动线程
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);

        // 退出Executor
        service.shutdown();
    }

    private static void initDataList(List<Integer> dataList) {
        for (int i = 1; i <= 100; i++) {
            dataList.add(i);
        }
    }
}
