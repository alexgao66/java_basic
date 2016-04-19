package com.alex.spring.mvc;

import com.alex.spring.service.ExceptionService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gaojun on 16/4/12.
 */
@Controller
@RequestMapping("/exception")
public class ExceptionHandlerController {

    @Autowired
    ExceptionService service;

    @ResponseBody
    @RequestMapping(method= RequestMethod.GET)
    public JSONObject get(@RequestParam(required = false) String flag) throws Exception {
        JSONObject obj = new JSONObject();
        if("1".equals(flag)) {
            throw new RuntimeException("abc");
        }
        if("2".equals(flag)) {
            throw new Exception("ee");
        }
        if("3".equals(flag)) {
            service.exception();
        }
        if("4".equals(flag)) {
            service.runException();
        }
        obj.put("success", true);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/handleExcp", method= RequestMethod.GET)
    public JSONObject handlerException() {
        JSONObject obj = new JSONObject();
        obj.put("success", true);

        service.runException();

        return obj;
    }
}
