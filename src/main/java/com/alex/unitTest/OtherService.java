package com.alex.unitTest;

import org.springframework.stereotype.Component;

/**
 * Created by gaojun on 2017/6/6.
 */
@Component
public class OtherService {
    public boolean doSomething() {
        System.out.println("Do something in OtherService!");
        return true;
    }
}
