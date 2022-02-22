package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.*;
import java.net.URL;

public class Wget implements Runnable {

    private final String url;
    private final int speed;
    private final String fileName;

    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            int downloadData = 0;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
                downloadData = bytesRead;
                if (downloadData == speed) {
                    long afterTime = System.currentTimeMillis();
                    if ((afterTime - startTime) < 1000) {
                        Thread.sleep(1000 - (afterTime - startTime));
                        downloadData = 0;
                        Thread.currentThread().start();
                    }
                }

            }
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args[0].length() == 0 || args[1].length() == 0 || args[2].length() == 0) {
            throw new IllegalArgumentException();
        } else {
            String url = args[0];
            int speed = Integer.parseInt(args[1]);
            String fileName = args[2];
            Thread wget = new Thread(new Wget(url, speed, fileName));
            wget.start();
            wget.join();
        }
    }
}
