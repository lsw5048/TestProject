package com.jd.future;

import org.junit.Test;

import java.util.concurrent.*;

public class FutureTest {
    @Test
    public void testGet() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() -> {
            Thread.sleep(5000);
            return 3;
        });
        while (true) {
            System.out.println(future.isDone());

            System.out.println(future.get(13,TimeUnit.SECONDS));
            if (future.isDone()) {
                break;
            }

        }


    }
}
