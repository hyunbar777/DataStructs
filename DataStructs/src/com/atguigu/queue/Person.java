package com.atguigu.queue;

/**
 * @author z
 * @createdate 2019-07-24 10:47
 */
public class Person {
    public String name;
    public int id;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }
}
