package ru.job4j.pool;


import ru.job4j.waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;


public class ThreadPool implements Runnable {

    private int currentThreads;
    private  int currentTasks;
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(currentTasks);
    private boolean isStopped = false;

    public ThreadPool(int currentThreads, int currentTasks) {
        this.currentThreads = currentThreads;
        this.currentTasks = currentTasks;
    }

    public void work(Runnable job) {
             if (!isStopped) {
                 for (int i = 0; i < currentThreads; i++) {
                     System.out.println("Work started");
                     threads.add(new Thread(job));
                     tasks.offer(job);
                 }
                 for (Thread tr : threads) {
                     tr.start();
                     System.out.println(tr.getName() + " thread started");
                 }
             }
    }

    public void shutdown() {
        this.isStopped = true;
        for (Thread tr : threads) {
            tr.interrupt();
            System.out.println(Thread.currentThread().getName() + " shutdown");
        }
    }

    @Override
    public void run() {
        while (!isStopped) {
            try {
                System.out.println(Thread.currentThread().getName() + " running");
                Runnable runnable = tasks.poll();
                runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        isStopped = true;
        }
    }


