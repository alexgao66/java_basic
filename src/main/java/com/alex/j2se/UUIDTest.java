package com.alex.j2se;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * Created by gaojun on 16/5/19.
 */
public class UUIDTest {

    public static void main(String[] args) {
        System.out.println(StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
//        System.out.println(StringUtils.isNotBlank(""));
    }

}
