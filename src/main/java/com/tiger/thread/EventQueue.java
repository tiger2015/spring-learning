package com.tiger.thread;

import java.util.LinkedList;

public class EventQueue {
    private final int max;

    static class Event {
    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private static final int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }


    public void offer(Event event){
        synchronized (eventQueue){
            if(eventQueue.size() >= max){
                System.out.println(Thread.currentThread().getName()+":"+" the queue is full.");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+" the new event is submitted.");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take(){
        synchronized (eventQueue){
            if(eventQueue.isEmpty()){

                System.out.println(Thread.currentThread().getName()+":" +" the queue is empty.");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            System.out.println(Thread.currentThread().getName()+":"+" the event "+event+" is handled.");
            return event;
        }
    }

}
