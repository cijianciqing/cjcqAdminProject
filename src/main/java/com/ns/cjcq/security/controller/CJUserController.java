package com.ns.cjcq.security.controller;

import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import com.ns.cjcq.common.dataTables.entity.CJDataTablesReturnData;
import com.ns.cjcq.security.dvo.CJEditUser;
import com.ns.cjcq.security.dvo.CJUserDataTableSearchBean;
import com.ns.cjcq.security.dvo.CJViewUser;
import com.ns.cjcq.security.service.CJUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/system/user")
public class CJUserController {

    @Autowired
    private CJUserService cjUserService;

    @GetMapping
    public ModelAndView getSystemUserPage(ModelAndView mv) {
        mv.setViewName("/system/user");
        return mv;
    }

    @PostMapping("/all")
    public CJAjaxResult getUsers(@RequestBody @Valid CJUserDataTableSearchBean cjDataTablesSearchBean) {
        //log.info(String.valueOf(cjDataTablesSearchBean));

        CJDataTablesReturnData<CJViewUser> allUser = cjUserService.getAllUser(cjDataTablesSearchBean);

        //log.info(String.valueOf(returnData));
        return CJAjaxResult.success("all_CJUsers", allUser);
    }

    @PostMapping
    public CJAjaxResult addUser(@RequestBody @Valid CJEditUser cjEditUser) {
        System.out.println("cjEditUser--->" + cjEditUser);
        cjUserService.saveUser(cjEditUser);
        return CJAjaxResult.success("保存成功");
    }

    /*
     * update user---获取单个用户的信息
     * */
    @GetMapping(value = "/{userId}")
    public CJAjaxResult getUser(@PathVariable(name = "userId") String userId) {
        CJEditUser userById = cjUserService.getUserById(userId);
        return CJAjaxResult.success("cjUser", userById);
    }

    @PutMapping
    public CJAjaxResult getUser(@RequestBody @Valid CJEditUser cjEditUser) {
       log.warn(String.valueOf(cjEditUser));
        cjUserService.updateUser(cjEditUser);
        return CJAjaxResult.success("更新成功");
    }

    @DeleteMapping(value = "/{userId}")
    public CJAjaxResult addUser(@PathVariable(name = "userId") String userId) {
        cjUserService.delUser(userId);
        return CJAjaxResult.success("删除成功");
    }
}
