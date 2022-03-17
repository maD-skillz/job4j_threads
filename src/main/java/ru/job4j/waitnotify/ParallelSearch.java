package ru.job4j.waitnotify;

public class ParallelSearch {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
            final Thread consumer = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                System.out.println(queue.poll());
                            } catch (InterruptedException e) {
                               Thread.currentThread().interrupt();
                            }
                        }

                    }
            );

            final Thread producer = new Thread(
                    () -> {
                        for (int index = 0; index != 3; index++) {
                                queue.offer(index);
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                        }
                    }
            );
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();



    }
}