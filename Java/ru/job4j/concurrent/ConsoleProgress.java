package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (int i = 0; i < 4; i++) {
                    Thread.sleep(500);
                    String x = "";
                    if (i == 0) {
                       x = "/";
                    } else if (i == 1) {
                        x = "|";
                    } else if (i == 2) {
                        x = "\\";
                    } else if (i == 3) {
                        x = "--";
                    }
                    System.out.print("\r load: " + x);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
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

