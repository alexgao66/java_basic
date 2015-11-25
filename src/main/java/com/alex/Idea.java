package com.alex;

/**
 * Created by gao.jun on 2015/11/18.
 */
public class Idea {

    private String id;

    private int age;

    public Idea(String id, int age) {
        this.id = id;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
