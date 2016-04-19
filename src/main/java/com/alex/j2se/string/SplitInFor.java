package com.alex.j2se.string;

import org.apache.commons.lang.StringUtils;

/**
 * Created by gaojun on 16/2/1.
 */
public class SplitInFor {

    public static void main(String[] args) {
        splitInFor("2|3|1,4");
    }

    private static void splitInFor(String strs) {
        // for中的split函数会在编译时进行优化,移动到for循环外部
        for (String str : StringUtils.split(strs, "|")) {
            System.out.println(str);
        }
    }

}
