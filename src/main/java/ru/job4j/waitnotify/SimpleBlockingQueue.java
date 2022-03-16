package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final Object lock = new Object();

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private final int maxSize = 5;


    public void offer(T value) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == maxSize) {
                wait();
            }
            queue.offer(value);
            notifyAll();
        }
    }


    public T poll() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                wait();
            }
            lock.notify();
            return queue.poll();
        }
    }

}
