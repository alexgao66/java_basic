package com.alex.j2se.oop.interfaceTest;

/**
 * Created by gaojun on 16/6/5.
 */
public class SpeakTest {

    public static void main(String[] args) {
        invokeSpeak(new Cat(), "aaaa");
        invokeSpeak(new Dog(), "aaaa");
    }

    public static void invokeSpeak(ISpeak speaker, String content) {
        speaker.speak(content);

    }
    public static void dogSpeak(Dog dog, String content) {
        dog.speak(content);
    }
}
