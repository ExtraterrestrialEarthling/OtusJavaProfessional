package extra.task;

import task.CustomToString;

@CustomToString
public class Person {
    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    public Person(){

    }
}
