package com.ns.cjcq.security.demo;

import com.ns.cjcq.security.validator.ImageCode;
import com.ns.cjcq.security.validator.interfaceAndImpiment.ValidatorGenerator;
import org.springframework.web.context.request.ServletWebRequest;

//仅用于测试封装的Validator
//@Component("validatorGenerator")
public class DemoValidatorGenerator implements ValidatorGenerator {


    @Override
    public ImageCode createImageCode(ServletWebRequest request) {
        System.out.println("jsjzx.wlyw.springbootwithsecurity.component.DemoValidatorGenerator..");
        return null;
    }
}
