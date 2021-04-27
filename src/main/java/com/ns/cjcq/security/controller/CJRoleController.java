package com.ns.cjcq.security.controller;

import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import cn.hutool.core.util.ObjectUtil;
import com.ns.cjcq.common.dataTables.entity.CJDataTablesReturnData;

import com.ns.cjcq.common.select2.entity.CJSelect2Entity;
import com.ns.cjcq.common.select2.entity.CJSelect2Result;
import com.ns.cjcq.security.dvo.*;
import com.ns.cjcq.security.service.CJRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/system/role")
public class CJRoleController {

    @Autowired
    private CJRoleService cjRoleService;

    @GetMapping
    public  ModelAndView getSystemRolePage(ModelAndView mv){
        mv.setViewName("/system/role");
        return mv;
    }
    /*
    * 1.用于user进行权限设置
    * */
    @GetMapping("/all")
    public  CJSelect2Result getPlainRoles() {

        List<CJSelect2Entity> allRole = cjRoleService.getPlainRoles();

        return new CJSelect2Result(allRole);
    }

    @PostMapping("/all")
    public  CJAjaxResult getRoles(@RequestBody(required = false) @Valid CJRoleDataTableSearchBean cjRoleDataTableSearchBean) {
        log.info(String.valueOf(cjRoleDataTableSearchBean));
        CJDataTablesReturnData<CJViewRole> allRole = cjRoleService.getAllRole(cjRoleDataTableSearchBean);
        return CJAjaxResult.success("all_CJRoles",allRole);
    }




    @PostMapping
    public  CJAjaxResult addRole(@RequestBody @Valid CJViewRole cjViewRole) {
        cjRoleService.saveRole(cjViewRole);
        return CJAjaxResult.success("保存成功");
    }
    /*
     * update user---获取单个用户的信息
     * */
    @GetMapping(value = "/{roleId}")
    public  CJAjaxResult getRole(@PathVariable(name = "roleId") String roleId) {
        CJViewRole roleById = cjRoleService.getRoleById(roleId);
        return CJAjaxResult.success("cjRole",roleById);
    }
    @PutMapping
    public  CJAjaxResult getRole(@RequestBody @Valid CJViewRole cjViewRole) {
        cjRoleService.updateRole(cjViewRole);
        return CJAjaxResult.success("更新成功");
    }

    @DeleteMapping(value = "/{roleId}")
    public  CJAjaxResult addRole(@PathVariable(name = "roleId") String roleId) {
        cjRoleService.delRole(roleId);
        return CJAjaxResult.success("删除成功");
    }


}
