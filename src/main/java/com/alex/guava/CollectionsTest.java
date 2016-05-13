package com.alex.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;

/**
 * Created by gaojun on 16/3/21.
 */
public class CollectionsTest {

    public static void main(String[] args) {
        // Sets
        Sets.newHashSet(StringUtils.split("a|b", "|"));

        Lists.newArrayList(1,2);
    }
}
