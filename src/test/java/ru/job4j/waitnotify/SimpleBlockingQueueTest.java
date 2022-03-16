package ru.job4j.waitnotify;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    static class Offer implements Runnable {
        SimpleBlockingQueue sbq;
        Offer(SimpleBlockingQueue sbq) {
            this.sbq = sbq;
        }
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    sbq.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Poll implements Runnable {
        SimpleBlockingQueue sbq;
        Poll(SimpleBlockingQueue sbq) {
            this.sbq = sbq;
        }
        @Override
        public void run() {
            for (int i = 5; i > 0; i--) {
                try {
                    sbq.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void simpleBlockingQueueTest() {
        SimpleBlockingQueue sbp = new SimpleBlockingQueue();
        Offer offer = new Offer(sbp);
        Poll poll = new Poll(sbp);
        Thread thread1 = new Thread(offer);
        Thread thread2 = new Thread(poll);
        thread1.start();
        thread2.start();

    }


}