package com.alex.spring.service;

import org.springframework.stereotype.Service;

/**
 * Created by gaojun on 16/4/12.
 */
@Service
public class ExceptionService {

    public void exception() throws Exception {
        throw new Exception("exception");
    }

    public void runException() {
        throw new RuntimeException("exception");
    }
}
