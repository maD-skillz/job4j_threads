package ru.job4j.pool;

import org.junit.Ignore;
import org.junit.Test;


public class ThreadPoolTest {
    @Ignore
    @Test
    public void test() {
        ThreadPool threadPool = new ThreadPool(4, 4);
        Runnable runnable = null;
        threadPool.work(runnable);
        threadPool.run();
        threadPool.shutdown();


    }
}