package com.ns.cjcq.security.config;

import com.ns.cjcq.security.dvo.CJPermissionResource;
import com.ns.cjcq.security.dvo.CJViewUser;
import com.ns.cjcq.security.service.CJUserPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Slf4j
@ControllerAdvice(basePackages = {"com.ns.cjcq.security.controller"})
public class CJGetUserPermission {

    @Autowired
    private CJUserPermissionService cjUserPermissionService;
    @ModelAttribute
    public void getPermissionsInUserPage( ModelAndView mv) {
//        log.info("getPermissionsInUserPage--ModelAttribute-------START");
//        Set<CJPermissionResource> resourcesById = cjUserPermissionService.getResourcesById(1L);
        CJViewUser userInfo = cjUserPermissionService.getUserInfo(1L);
        mv.addObject("cjUser",userInfo);
    }

}
