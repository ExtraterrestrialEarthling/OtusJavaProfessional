package ru.chaos;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerformanceTest {
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
            System.out.println("Median: " + median); // Ожидаемый результат: 5.0
        }

    private static void testMedianEvenSizeCalculation() {
        MedianList<Integer> medianList = new MedianList<>();
        medianList.add(5);
        medianList.add(2);
        medianList.add(8);
        medianList.add(10);

        double median = medianList.getMedian();
        System.out.println("Median: " + median); // Ожидаемый результат: 6.5
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
                latch.await(); // Ждем завершения всех потоков
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Total operations: " + (numThreads * operationsPerThread));
            System.out.println("Total time: " + duration + " milliseconds");
            System.out.println("Operations per second: " + ((numThreads * operationsPerThread) / (duration / 1000.0)));
            executorService.shutdown();
        }
    }

