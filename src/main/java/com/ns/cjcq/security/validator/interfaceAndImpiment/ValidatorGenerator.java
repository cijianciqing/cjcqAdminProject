package com.ns.cjcq.security.validator.interfaceAndImpiment;

import com.ns.cjcq.security.validator.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/*
* 最开始，验证码在Controller生成
* */
public interface ValidatorGenerator {
    public ImageCode createImageCode(ServletWebRequest request);
}
