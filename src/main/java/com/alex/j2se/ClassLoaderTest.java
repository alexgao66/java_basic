package com.alex.j2se;

/**
 * Created by gaojun on 17/1/10.
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        System.out.println(ClassLoaderTest.class.getResource("").getPath());
        System.out.println(ClassLoaderTest.class.getClassLoader().getResource("").getPath());
        System.out.println(new ClassLoaderTest().getClass().getResource("").getPath());
    }
}
