package com.alex.j2se.oop.interfaceTest;

/**
 * Created by gaojun on 16/6/5.
 */
public class SleepTest {
    public static void main(String[] args){
        allSleep(new Cat(),"bbbb");
        allSleep(new Dog(),"mmmm");


    }
    public static void allSleep(ISleep sleeper,String content){
        sleeper.sleep(content);

    }

}
