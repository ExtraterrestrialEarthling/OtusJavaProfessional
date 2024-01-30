package ru.chaos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerformanceTest {
    private static final Logger logger = LoggerFactory.getLogger(PerformanceTest.class);

        public static void main(String[] args) {
            testMedianOddSizeCalculation();
            testMedianEvenSizeCalculation();
            testPerformance();
        }

        private static void testMedianOddSizeCalculation() {
            MedianList<Integer> medianList = new MedianList<>();
            medianList.add(5);
            medianList.add(2);
            medianList.add(8);

            double median = medianList.getMedian();
            logger.info("Median: " + median);
        }

    private static void testMedianEvenSizeCalculation() {
        MedianList<Integer> medianList = new MedianList<>();
        medianList.add(5);
        medianList.add(2);
        medianList.add(8);
        medianList.add(10);

        double median = medianList.getMedian();
        logger.info("Median: " + median);
    }

        private static void testPerformance() {
            final int numThreads = 10;
            final int operationsPerThread = 1_000_000;

            MedianList<Integer> medianList = new MedianList<>();
            ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
            CountDownLatch latch = new CountDownLatch(numThreads);

            long startTime = System.currentTimeMillis();

            for (int i = 0; i < numThreads; i++) {
                final int threadNum = i;
                executorService.submit(() -> {
                    for (int j = 0; j < operationsPerThread; j++) {
                        medianList.add(threadNum * operationsPerThread + j);
                    }
                    latch.countDown();
                });
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            logger.info("Total operations: " + (numThreads * operationsPerThread));
            logger.info("Total time: " + duration + " milliseconds");
            logger.info("Operations per second: " + ((numThreads * operationsPerThread) / (duration / 1000.0)));
            executorService.shutdown();
        }
    }

