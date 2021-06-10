package com.ns.cjcq.security.crud.controller;

import com.ns.cjcq.security.crud.dvo.CJEditUser;
import com.ns.cjcq.security.crud.dvo.CJPermissionResource;
import com.ns.cjcq.security.crud.dvo.CJUserDataTableSearchBean;
import com.ns.cjcq.security.crud.dvo.CJViewUser;
import com.ns.cjcq.security.crud.service.CJResourceService;
import com.ns.cjcq.security.crud.service.CJUserPermissionService;
import com.ns.cjcq.security.crud.service.CJUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/admin")
public class CJAdminIndexController {

    @RequestMapping
    public ModelAndView toIndexPage(ModelAndView mv){
        mv.setViewName("admin/index");
        return mv;
    }



}
