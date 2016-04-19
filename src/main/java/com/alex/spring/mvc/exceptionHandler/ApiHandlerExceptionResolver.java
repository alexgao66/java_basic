package com.alex.spring.mvc.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一异常处理,返回500状态,格式:{"success":false,"msg":"xxx"}
 * Created by gaojun on 16/4/5.
 */
@Component
public class ApiHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response,
                                         Object o, Exception e) {
        LOGGER.error("Exception in ApiHandlerExceptionResolver!", e);

        ModelAndView modelAndView = new ModelAndView();
        // 500异常
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            response.getWriter().write("{\"success\":false,\"msg\":\"服务器异常!\"}");
        } catch (IOException ex) {
            LOGGER.error("HttpServletResponse failed:"+ ex.getMessage(), ex);
        }
        return modelAndView;
    }
}
