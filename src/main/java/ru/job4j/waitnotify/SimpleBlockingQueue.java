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

    public SimpleBlockingQueue(int size) {
        maxSize = size;
    }

    public synchronized void offer(T value) {
        while (queue.size() == maxSize) {
            try {
                wait(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
            queue.offer(value);
            notifyAll();

    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
                wait(300);
            }
            T result = queue.poll();
            notifyAll();
            return result;

    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
