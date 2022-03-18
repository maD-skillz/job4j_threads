package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int x;
        int temp = 0;
        do {
            x = getVal();
            if (x == 0) {
                throw new UnsupportedOperationException("Count is not impl.");
            }
        } while (!count.compareAndSet(x, temp + 1));
    }

    public int getVal() {
        if (count.get() == null) {
            throw new UnsupportedOperationException("Count is not impl.");
        }
        return count.get();
    }
}
