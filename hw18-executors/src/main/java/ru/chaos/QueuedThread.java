package ru.chaos;

import java.util.concurrent.BlockingQueue;


public class QueuedThread extends Thread {
    BlockingQueue<String> in;
    BlockingQueue<String> out;

    public QueuedThread(BlockingQueue<String> in, BlockingQueue<String> out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        for (int i = 1; i < 10; i++) {
            try {
                in.take();
                System.out.println(Thread.currentThread().getName() + ": " + i);
                out.put("Твой ход!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 10; i >=1; i--) {
            try {
                in.take();
                System.out.println(Thread.currentThread().getName() + ": " + i);
                out.put("Твой ход!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}