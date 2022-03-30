package ru.job4j.pool;


import ru.job4j.waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;


public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(12);

    public ThreadPool() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            System.out.println("add new thread");
            threads.add(new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
    }


    public void work(Runnable job) {
        System.out.println("job");
        tasks.offer(job);

    }

    public void shutdown() {
        for (Thread tr : threads) {
            System.out.println("shut");
            tr.interrupt();
        }
    }

}


