package com.alex.spring.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	private static Logger log = LoggerFactory.getLogger(HelloController.class);
	
	private static Map<String, Object> map = new HashMap<String, Object>();
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public JSONObject get() {
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		return obj;
	}
	
	@ResponseBody
	@RequestMapping(value= "/perform", method=RequestMethod.GET)
	public JSONObject performanceTest() {
		long start = System.currentTimeMillis();
		List<String> vals = new ArrayList<String>();
		for(int i = 0; i < 10000; ++i) {
			vals.add("value" + i);
		}
		for(int i = 0; i < 10000; ++i) {
			map.put(String.valueOf(map.size()) + i, vals);
		}
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		log.info("size:{}, perform cost:{}", map.size(), System.currentTimeMillis() - start);
		return obj;
	}
}
