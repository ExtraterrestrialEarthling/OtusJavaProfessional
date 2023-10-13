package test.task;

import custom.annotations.*;

public class SomeClassTest {

    @Before
    public void init(){
        System.out.println("сначала сработает before");
    }

    @Test
    public void testGoodMethod() {
        System.out.println("затем сработает testGoodMethod");

    }

    @Test
    public void testBadMethod() throws Exception {
        System.out.println("затем сработает testBadMethod");
        throw new AssertionError("test failed");
    }



    @After
    public void finish(){
        System.out.println("Последним сработает after");

    }

}
