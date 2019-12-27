package com.tiger.thread;

public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {

            while (true) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
        Thread.sleep(2000L);
        System.out.println("main thread has finished lifecycle.");
    }
}
