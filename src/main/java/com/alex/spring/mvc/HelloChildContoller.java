package com.alex.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试通过继承修改mapping的ur
 * Created by gaojun on 16/5/4.
 */
@Controller
@RequestMapping(value = {"", "/open"})
public class HelloChildContoller extends HelloController {
}
