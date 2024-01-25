package ru.chaos;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueuedThreadRunner {
    public static void main(String[] args) {
        BlockingQueue<String> t1t2 = new LinkedBlockingQueue<>();
        BlockingQueue<String> t2t1 = new LinkedBlockingQueue<>();
        QueuedThread t1 = new QueuedThread(t1t2, t2t1);
        QueuedThread t2 = new QueuedThread(t2t1, t1t2);
        t1.start();
        t2.start();
        try {
            t1t2.put("Старт");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
