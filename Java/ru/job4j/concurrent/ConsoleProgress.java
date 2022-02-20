package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() ->
        {
            System.out.println("\r load: " + "\\//");
            System.out.println("\r load: " + "\\|/");
            System.out.println("\r load: " + "\\\\/");
        });
        thread.start();
        thread.sleep(500);
        thread.interrupt();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {

            }
        }
    }

