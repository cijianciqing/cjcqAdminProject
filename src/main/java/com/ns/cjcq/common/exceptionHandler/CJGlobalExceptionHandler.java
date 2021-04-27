package com.ns.cjcq.common.exceptionHandler;

import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJRDataUtil;
import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJReturnedData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
/*暂时禁用此功能，便于开发调试*/
//@ControllerAdvice
public class CJGlobalExceptionHandler {
//
//    @Autowired
//    CJLogService cjLogService;


    /*校验错误处理*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String cjValidateException(MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        Map<String, Object> cjValidateErrors = new HashMap<>(fieldErrorList.size());
        fieldErrorList.forEach(error -> {
            // 将错误参数名称和参数错误原因存于map集合中
            cjValidateErrors.put(error.getField(), error.getDefaultMessage());
        });

//        CJReturnedData cjReturnedData = CJRDataUtil.Err(400, "后台校验错误");
//        cjReturnedData.setData(cjValidateError);
        CJAjaxResult cjAjaxResult = CJAjaxResult.error("cjValidateMessage",cjValidateErrors);

        Map<String, Object> map = new HashMap<>();
        map.put("cjMessage", cjAjaxResult);
        //request.setAttribute("javax.servlet.error.status_code", 500);
        request.setAttribute("cjExceptionHandlerMessage", map);
        return "forward:/error";
    }

    /*全局异常处理*/
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();

        //设置状态码
        //传入我们自己的错误状态码  4xx 5xx，否则就不会进入定制错误页面的解析流程
        request.setAttribute("javax.servlet.error.status_code", 500);

        map.put("cjMessage", "cjGlobalMessage :" +e.getMessage() );

        request.setAttribute("cjExceptionHandlerMessage", map);

        //写入数据库
        return "forward:/error";
    }


}
