package com.ns.cjcq.security.controller;

import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import com.ns.cjcq.common.dataTables.entity.CJDataTablesReturnData;
import com.ns.cjcq.common.dataTables.entity.CJDataTablesSearchBean;
import com.ns.cjcq.security.dto.CJViewUser;
import com.ns.cjcq.security.service.CJUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/system/user")
public class CJUserController {

    @Autowired
    private CJUserService cjUserService;

    @GetMapping
    public  ModelAndView getSystemUserPage(ModelAndView mv){
        mv.setViewName("/system/user");
        return mv;
    }

    @PostMapping("/all")
    public  CJAjaxResult getUsers(@RequestBody @Valid CJDataTablesSearchBean cjDataTablesSearchBean) {
        log.info(String.valueOf(cjDataTablesSearchBean));
        List<CJViewUser> allUser = cjUserService.getAllUser();
        CJDataTablesReturnData<CJViewUser> returnData = new CJDataTablesReturnData<>();
        returnData.setData(allUser);
        returnData.setDraw(cjDataTablesSearchBean.getDraw());
        log.info(String.valueOf(returnData));
        return CJAjaxResult.success("all_CJUsers",returnData);
    }

}
