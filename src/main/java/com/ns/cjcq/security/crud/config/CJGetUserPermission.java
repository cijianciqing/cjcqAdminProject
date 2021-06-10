package com.ns.cjcq.security.crud.config;


import com.ns.cjcq.security.crud.dvo.CJViewUser;
import com.ns.cjcq.security.crud.service.CJUserPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice(basePackages = {"com.ns.cjcq.security.crud.controller"})
public class CJGetUserPermission {

    @Autowired
    private CJUserPermissionService cjUserPermissionService;
    @ModelAttribute
    public void getPermissionsInUserPage( ModelAndView mv) {
        CJViewUser userInfo = cjUserPermissionService.getUserInfo(1L);
        mv.addObject("cjUser",userInfo);
    }

}
