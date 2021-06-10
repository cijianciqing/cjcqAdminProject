package com.ns.cjcq.security.crud.controller;

import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import com.ns.cjcq.common.dataTables.entity.CJDataTablesReturnData;

import com.ns.cjcq.security.crud.dvo.CJRoleDataTableSearchBean;
import com.ns.cjcq.security.crud.dvo.CJViewRole;
import com.ns.cjcq.security.crud.service.CJRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/system/role")
public class CJRoleController {

    @Autowired
    private CJRoleService cjRoleService;


    /*
    * 返回到role.html
    * */
    @GetMapping
    public  ModelAndView getSystemRolePage(ModelAndView mv){
        mv.setViewName("admin/system/role");
        return mv;
    }

    /*
    * 返回role  dataTables的所有数据
    * */
    @PostMapping("/all")
    public  CJAjaxResult getRoles(@RequestBody(required = false) @Valid CJRoleDataTableSearchBean cjRoleDataTableSearchBean) {
        log.info(String.valueOf(cjRoleDataTableSearchBean));
        CJDataTablesReturnData<CJViewRole> allRole = cjRoleService.getAllRole(cjRoleDataTableSearchBean);
        return CJAjaxResult.success("all_CJRoles",allRole);
    }



/*
* 添加role
* */
    @PostMapping
    public  CJAjaxResult addRole(@RequestBody @Valid CJViewRole cjViewRole) {
        cjRoleService.saveRole(cjViewRole);
        return CJAjaxResult.success("保存成功");
    }

    /*
     * get role---获取单个角色的信息
     * */
    @GetMapping(value = "/{roleId}")
    public  CJAjaxResult getRole(@PathVariable(name = "roleId") String roleId) {
        CJViewRole roleById = cjRoleService.getRoleById(roleId);
        return CJAjaxResult.success("cjRole",roleById);
    }

    /*
    * 更新role
    * */
    @PutMapping
    public  CJAjaxResult updateRole(@RequestBody @Valid CJViewRole cjViewRole) {
        cjRoleService.updateRole(cjViewRole);
        return CJAjaxResult.success("更新成功");
    }

    /*
    * 删除role
    * */
    @DeleteMapping(value = "/{roleId}")
    public  CJAjaxResult addRole(@PathVariable(name = "roleId") String roleId) {
        cjRoleService.delRole(roleId);
        return CJAjaxResult.success("删除成功");
    }


}
