package com.alex.httpClient;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by gaojun on 16/10/24.
 */
public class Md5Test {

    public static void main(String[] args) {
        try {
            System.out.println(new String(MessageDigest.getInstance("MD5").digest("abc".getBytes())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
