package com.alex.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public JSONObject get() {
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		return obj;
	}
	
}
