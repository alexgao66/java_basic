package com.alex.j2se.oop.interfaceTest;

/**
 * Created by gaojun on 16/6/5.
 */
public class Dog implements ISpeak,ISleep {
    @Override
    public void speak(String content) {
        System.out.println("wangwang," + content);
    }

    @Override
    public void sleep(String content) {
        System.out.println("huhuhu" + content);
    }
}
