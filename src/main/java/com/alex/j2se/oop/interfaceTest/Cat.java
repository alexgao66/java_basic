package com.alex.j2se.oop.interfaceTest;

/**
 * Created by gaojun on 16/6/5.
 */
public class Cat implements ISpeak ,ISleep{

    @Override
    public void speak(String content) {
        System.out.println("miaomiao"+ content);
    }

    @Override
    public void sleep(String content) {
        System.out.println("zizizi" + content);
    }
}
