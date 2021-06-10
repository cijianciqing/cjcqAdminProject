package com.ns.cjcq.security.validator.controller;


import com.ns.cjcq.security.common.CJSpringSecurityProperty;
import com.ns.cjcq.security.validator.ImageCode;
import com.ns.cjcq.security.validator.interfaceAndImpiment.ValidatorGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ValidatorController {

    @Autowired
    ValidatorGenerator validatorGenerator;

    @Autowired
    CJSpringSecurityProperty cjSpringSecurityProperty;

    //操作session
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    //生成key
    public static final  String  My_Session_Key = "My_Session_Key_For_ImageCode";

    @RequestMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ImageCode imageCode = validatorGenerator.createImageCode(new ServletWebRequest(request));

        sessionStrategy.setAttribute(new ServletWebRequest(request),My_Session_Key,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());

    }

}
