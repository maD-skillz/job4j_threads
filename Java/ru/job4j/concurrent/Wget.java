package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.*;
import java.net.URL;

public class Wget implements Runnable {

    private final String url;
    private final int speed;
    private final String fileName;
    public static final int TIME_MILLIS = 1000;

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
                downloadData += bytesRead;
                if (downloadData >= speed) {
                    long afterTime = System.currentTimeMillis();
                    long downloadTime = afterTime - startTime;
                    if (downloadTime < TIME_MILLIS) {
                        Thread.sleep(TIME_MILLIS - downloadTime);
                    }
                    downloadData = 0;
                    startTime = System.currentTimeMillis();
                }
            }
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            throw new InterruptedException("Arguments must be 3!");
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
