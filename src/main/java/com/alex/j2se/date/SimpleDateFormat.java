package com.alex.j2se.date;

import java.text.DateFormat;
import java.text.ParseException;

/**
 * Created by gaojun on 2017/5/9.
 */
public class SimpleDateFormat {

    public static void main(String[] args) throws ParseException {
        DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.parse("2017-05-18"));
    }
}
