package com.alex.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gaojun on 16/7/19.
 */
public class ArrayParse {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ids", Lists.newArrayList("3","1","4"));
        System.out.println(jsonObject.toJSONString());

        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.toJSONString());
        String idString = jsonObject1.getString("ids");
        ArrayList<String> idList = null;

        System.out.println(JSONObject.parseObject(idString, new TypeReference<List<String>>(){}).get(0));


    }
}
