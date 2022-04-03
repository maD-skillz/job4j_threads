package ru.job4j.mailsender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());


    public void emailTo(User user) throws InterruptedException {
       pool.submit(() -> {
                if (user.getEmail() != null) {
                    send(
                            new StringBuilder()
                                    .append("Notification for ")
                                    .append(user.getName())
                                    .append(" to email ")
                                    .append(user.getEmail()).toString(),
                            new StringBuilder()
                                    .append(" Add a new event to ")
                                    .append(user.getName()).toString(),
                            user.getEmail()
                            );
                }
       });
    }

    public void send(String subject, String body, String email) {

    }

    public void close() throws InterruptedException {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
