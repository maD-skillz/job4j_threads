package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);


    public void increment() {
        int x;
        do {
            x = getVal();
        } while (!count.compareAndSet(x, x + 1));
    }

    public int getVal() {
        return count.get();
    }

}
