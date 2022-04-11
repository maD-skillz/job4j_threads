package ru.job4j.pool;


import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;


public class ThreadPoolTest {

    @Test
    public void test() {
        ThreadPool threadPool = new ThreadPool();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 12; i++) {
            threadPool.work(atomicInteger::getAndIncrement);
        }
        threadPool.shutdown();

    }
}