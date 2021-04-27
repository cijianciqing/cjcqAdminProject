package com.ns.cjcq.common.exceptionHandler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/*
* 自定义返回的错误信息
* */
@Slf4j
@Component
public class CJErrorAttribute extends DefaultErrorAttributes {

    //返回的值map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map =super.getErrorAttributes(webRequest,includeStackTrace);

        //自己的异常处理器（CJGlobalExceptionHandler）携带的数据
        //0 是request中
        //1 是session中
        Map<String, Object> exc = (Map<String, Object>) webRequest.getAttribute("cjExceptionHandlerMessage", 0);
        map.put("cjExceptionHandlerMessage",exc);
        return map;
    }
}
