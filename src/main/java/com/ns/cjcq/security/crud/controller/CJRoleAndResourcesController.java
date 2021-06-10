package com.ns.cjcq.security.crud.controller;

import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import com.ns.cjcq.common.zTree.entity.CJZTreeNodeResourceEntity;
import com.ns.cjcq.security.crud.dvo.CJRoleDataTableSearchBean;
import com.ns.cjcq.security.crud.dvo.CJViewRole;
import com.ns.cjcq.security.crud.service.CJRoleAndResourcesService;
import com.ns.cjcq.security.crud.service.CJRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/system/roleAndResources")
public class CJRoleAndResourcesController {

    @Autowired
    private CJRoleAndResourcesService cjRoleAndResourcesService;

    /*
     * 获取role 所有的 resource
     * */
    @GetMapping(value = "/{roleId}")
    public CJAjaxResult getResourcesByRoleID(@PathVariable(value = "roleId",required = true) String roleId) {
        List<CJZTreeNodeResourceEntity> allResoucres = cjRoleAndResourcesService.getResourcesByRoleID(roleId);
        return CJAjaxResult.success("cjAllResources", allResoucres);
    }
    /*
     * 设置 role的 resources
     * */
    @PostMapping(value = "/{roleId}")
    public CJAjaxResult setResourcesByRoleID(@PathVariable(value = "roleId",required = true) String roleId,@RequestBody List<Long> resourceIds) {
        log.info(roleId);
        log.info(String.valueOf(resourceIds));
        cjRoleAndResourcesService.setResourcesByRoleId(roleId,resourceIds);

        return CJAjaxResult.success("cjRoleResourceAllocate");
    }


}
