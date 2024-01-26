package ru.chaos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockThreadRunner {
    private final static Lock lock = new ReentrantLock();
    private final static Condition ready = lock.newCondition();


    static class MyThread extends Thread {
        private int counter = 0;

        public void run() {
            while (counter < 10) {
                lock.lock();
                try {
                    counter++;
                    System.out.println(counter);
                    ready.signal();
                    ready.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


            public static void main (String[]args) {
                MyThread t1 = new MyThread();
                MyThread t2 = new MyThread();
                t1.start();
                t2.start();
            }
}