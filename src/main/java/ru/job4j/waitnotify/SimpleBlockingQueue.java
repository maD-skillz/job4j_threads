package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private final int maxSize;

    public SimpleBlockingQueue() {
        maxSize = 5;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == maxSize) {
                wait();
            }
            queue.offer(value);
            notifyAll();

    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
                wait();
            }
            T result = queue.poll();
            notifyAll();
            return result;

    }

}
