package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] arr = new String[]{"--", "\\", "|",  "/"};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (String i : arr) {
                    System.out.print("\r load: " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ConsoleProgress());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

    }
}

