package ru.job4j.waitnotify;


public class ParallelSearch {

        public static void main(String[] args) {
            SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);
            final Thread consumer = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                System.out.println(queue.poll());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
            );
            consumer.start();
            new Thread(
                    () -> {
                        for (int index = 0; index != 3; index++) {
                            queue.offer(index);
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                }
                            }
                            consumer.interrupt();
                        }
                    ).start();
        }
    }
