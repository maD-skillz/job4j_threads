package ru.job4j.concurrent;

public class Wget {

    public static void main(String[] args) {
        Thread thread = new Thread(() ->
        {
            try {
                System.out.println("Start loading ... ");
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(1000);
                    System.out.println("\rLoading : " + i + "%");
                    if (i == 100) {
                        System.out.println("Loading complete.");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();


    }
}
