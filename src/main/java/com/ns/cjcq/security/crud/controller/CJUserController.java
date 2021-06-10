package com.ns.cjcq.security.crud.controller;

import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import com.ns.cjcq.common.dataTables.entity.CJDataTablesReturnData;
import com.ns.cjcq.security.crud.dvo.CJEditUser;
import com.ns.cjcq.security.crud.dvo.CJUserDataTableSearchBean;
import com.ns.cjcq.security.crud.dvo.CJViewUser;
import com.ns.cjcq.security.crud.service.CJUserPermissionService;
import com.ns.cjcq.security.crud.service.CJUserService;
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


    @Autowired
    private CJUserPermissionService cjUserPermissionService;




    /*
    * 返回到user.html
    * */
    @GetMapping
    public ModelAndView getSystemUserPage(ModelAndView mv) {
        mv.setViewName("admin/system/user");
        return mv;
    }

    /*
     * 获取user dataTables数据
     * */
    @PostMapping("/all")
    public CJAjaxResult getUsers(@RequestBody @Valid CJUserDataTableSearchBean cjDataTablesSearchBean) {

        CJDataTablesReturnData<CJViewUser> allUser = cjUserService.getAllUser(cjDataTablesSearchBean);

        return CJAjaxResult.success("all_CJUsers", allUser);
    }

    /*
    * 添加user
    * */
    @PostMapping
    public CJAjaxResult addUser(@RequestBody @Valid CJEditUser cjEditUser) {
//        System.out.println("cjEditUser--->" + cjEditUser);
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

    /*
    * 更新user
    * */
    @PutMapping
    public CJAjaxResult getUser(@RequestBody @Valid CJEditUser cjEditUser) {
       log.warn(String.valueOf(cjEditUser));
        cjUserService.updateUser(cjEditUser);
        return CJAjaxResult.success("更新成功");
    }

    /*
    * deleteUser
    * */
    @DeleteMapping(value = "/{userId}")
    public CJAjaxResult addUser(@PathVariable(name = "userId") String userId) {
        cjUserService.delUser(userId);
        return CJAjaxResult.success("删除成功");
    }
}
