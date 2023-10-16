package test.task;

import custom.annotations.After;
import custom.annotations.Before;
import custom.annotations.Test;

import java.lang.reflect.Method;

public class UnitTestRunner<T> {
    private static int testsPassed;
    private static int testsTotal;


    public void runAllTestsFor(T test) {
        runBefore(test);
        runTests(test);
        runAfter(test);
    }

    private void runBefore(T test) {
        for (Method method : test.getClass().getMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                try {
                    method.invoke(test);
                } catch (Exception e) {
                    System.out.println("Ошибка в методе before");
                }
            }
        }
    }

    private void runAfter(T test) {
            for (Method method : test.getClass().getMethods()) {
                if (method.isAnnotationPresent(After.class)) {
                    try {
                        method.invoke(test);
                    } catch (Exception e) {
                        System.out.println("Ошибка в методе after");
                    }
                }
            }
        System.out.println("Тестов пройдено: " + testsPassed + " из " + testsTotal);
    }


    private void runTests(T test) {
            for (Method method : test.getClass().getMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    testsTotal++;
                    try {
                    method.invoke(test);
                    testsPassed++;
                    } catch (Exception e) {
                        System.out.println("Тест " + method.getName() + " не пройден.");
                    }
                }
            }

    }

}
