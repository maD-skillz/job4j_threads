package ru.job4j.cas;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;


import static org.junit.Assert.*;

public class CASCountTest {

    @Ignore
    @Test
    public void countTest() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        CASCount casCount = new CASCount();

        Thread increment = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 5; i++) {
                            casCount.increment();
                        }
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );

        Thread consumer = new Thread(
                () -> {
                    while (!buffer.isEmpty() && !Thread.currentThread().isInterrupted()) {
                        buffer.add(casCount.getVal());
                    }
                }
        );
        increment.start();
        consumer.start();
        increment.join();
        consumer.join();

        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

    @Test
    public void test() {
        CASCount casCount = new CASCount();
        for (int i = 0; i < 10; i++) {
            casCount.increment();
        }
        assertEquals(10, casCount.getVal());
    }

}