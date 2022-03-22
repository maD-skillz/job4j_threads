package ru.job4j.cas;

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;


import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void countTest() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        CASCount casCount = new CASCount();

        Thread thread1 = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 5; i++) {
                            casCount.increment();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

        );
        thread1.start();

        Thread thread2 = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 5; i++) {
                            casCount.increment();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                );
        thread2.start();
        thread1.join();
        thread2.join();

        assertEquals(casCount.getVal(), 10);
    }

}