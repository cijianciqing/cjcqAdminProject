package com.ns.cjcq.security.controller;

import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import com.ns.cjcq.common.dataTables.entity.CJDataTablesReturnData;
import com.ns.cjcq.security.dvo.CJEditUser;
import com.ns.cjcq.security.dvo.CJPermissionResource;
import com.ns.cjcq.security.dvo.CJUserDataTableSearchBean;
import com.ns.cjcq.security.dvo.CJViewUser;
import com.ns.cjcq.security.service.CJResourceService;
import com.ns.cjcq.security.service.CJUserPermissionService;
import com.ns.cjcq.security.service.CJUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

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
