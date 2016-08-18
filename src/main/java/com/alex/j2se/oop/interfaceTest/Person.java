package com.alex.j2se.oop.interfaceTest;

/**
 * Created by gaojun on 16/6/5.
 */
public class Person implements ISpeak {

    @Override
    public void speak(String content) {
        System.out.println(String.format("say %s", content));
    }
}
