package test.task;


public class Main {
    public static void main(String[] args) {
        UnitTestRunner<SomeClassTest> testRunner = new UnitTestRunner<>();
        SomeClassTest test = new SomeClassTest();
        testRunner.runAllTestsFor(test);

    }

}
