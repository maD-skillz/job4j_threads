package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.*;
import java.net.URL;
import java.sql.Timestamp;

public class Wget implements Runnable {

    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
        FileOutputStream fileOutputStream = new FileOutputStream("output_file_wget.xml")) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            long startTime = System.currentTimeMillis();
            while((bytesRead = in.read(buffer, 0, 1024)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
                long afterTime = System.currentTimeMillis();
                if (speed < bytesRead / startTime - afterTime) {
                    Thread.sleep(speed);
                }
            }
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
