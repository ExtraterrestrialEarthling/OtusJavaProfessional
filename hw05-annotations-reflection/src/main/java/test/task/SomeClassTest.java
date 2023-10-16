package test.task;

import custom.annotations.*;

public class SomeClassTest {

    @Before
    public void init(){
        System.out.println("Сначала сработает before");
    }

    @Test
    public void testGoodMethod() {
        System.out.println("Затем сработает testGoodMethod.");

    }

    @Test
    public void testBadMethod() throws Exception {
        System.out.println("Затем сработает testBadMethod.");
        throw new Exception("test failed");
    }



    @After
    public void finish(){
        System.out.println("Последним сработает after");

    }

}
