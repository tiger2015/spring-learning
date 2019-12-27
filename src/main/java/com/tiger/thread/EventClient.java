package com.tiger.thread;

import java.util.concurrent.TimeUnit;

public class EventClient {

    public static void main(String[] args) {

        final EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer1").start();
//        new Thread(() -> {
//            for (; ; ) {
//                eventQueue.offer(new EventQueue.Event());
//            }
//        }, "Producer2").start();

        new Thread(() -> {
            for (; ; ) {
                eventQueue.take();
//                try {
//                    TimeUnit.MILLISECONDS.sleep(1L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        }, "Consumer1").start();
        new Thread(() -> {
            for (; ; ) {
                eventQueue.take();
//                try {
//                    TimeUnit.MILLISECONDS.sleep(1L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        }, "Consumer2").start();
        new Thread(() -> {
            for (; ; ) {
                eventQueue.take();
//                try {
//                    TimeUnit.MILLISECONDS.sleep(1L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        }, "Consumer3").start();
//        new Thread(() -> {
//            for (; ; ) {
//                eventQueue.take();
//                try {
//                    TimeUnit.MILLISECONDS.sleep(10L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }, "Consumer4").start();

    }

}
