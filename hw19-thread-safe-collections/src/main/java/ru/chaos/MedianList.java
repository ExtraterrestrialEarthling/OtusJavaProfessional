package ru.chaos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MedianList<T extends Number> {
    private final List<T> list = Collections.synchronizedList(new ArrayList<>());
    Lock lock = new ReentrantLock();

    public int size() {
        return list.size();
    }

    public void add(T item) {
        lock.lock();
        try {
            list.add(item);
        } finally {
            lock.unlock();
        }
    }

    public void remove(T item) {
        lock.lock();
        try {
            list.remove(item);
        } finally {
            lock.unlock();
        }
    }

    public double getMedian() {
        lock.lock();
        try {
            List<T> sorted = list.stream().sorted().toList();
            if (list.isEmpty()) {
                return Double.NaN;
            }
            if (sorted.size() % 2 == 0) {
                int firstMiddleElementIndex = (list.size() / 2) - 1;
                int secondMiddleElementIndex = list.size() / 2;
                T firstMiddleElement = sorted.get(firstMiddleElementIndex);
                T secondMiddleElement = sorted.get(secondMiddleElementIndex);
                return (firstMiddleElement.doubleValue() + secondMiddleElement.doubleValue()) / 2.0;
            }
            if (sorted.size() % 2 != 0) {
                return sorted.get(sorted.size() / 2).doubleValue();

            }
        } finally {
            lock.unlock();
        }
        return -1;
    }

    @Override
    public String toString() {
        lock.lock();
        try {
            return list.toString();
        } finally {
            lock.unlock();
        }
    }
}
