package com.ns.cjcq.security.crud.controller;

import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import com.ns.cjcq.common.zTree.entity.CJZTreeNodeResourceEntity;
import com.ns.cjcq.security.crud.dvo.CJViewResource;
import com.ns.cjcq.security.crud.dvo.CJViewRole;
import com.ns.cjcq.security.crud.service.CJResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/system/resource")
public class CJResourceController {

    @Autowired
    private CJResourceService cjResourceService;

    /*
     * 打开resource.html
     * */
    @GetMapping
    public ModelAndView getSystemUserPage(ModelAndView mv) {
        mv.setViewName("admin/system/resource");
        return mv;
    }

    /*
     * 获取所有的resource
     * */
    @GetMapping(value = "/all")
    public CJAjaxResult getAllResources() {
        List<CJZTreeNodeResourceEntity> allResoucres = cjResourceService.getAllResoucres();
        return CJAjaxResult.success("cjAllResources", allResoucres);
    }


    /*
    * 添加resource
    * */
    @PostMapping
    public  CJAjaxResult addRole(@RequestBody @Valid CJViewResource cjViewResource) {
        cjResourceService.saveResource(cjViewResource);
        return CJAjaxResult.success("保存成功");
    }

    /*
     * resource--zTree---获取单个resource的信息
     * */
    @GetMapping(value = "/{resourceId}")
    public  CJAjaxResult getResource(@PathVariable(name = "resourceId") String resourceId) {
        CJViewResource viewResource = cjResourceService.getResourceById(resourceId);
        return CJAjaxResult.success("cjResource",viewResource);
    }


//    @ModelAttribute(value = "/{resourceId}")
//    public void getResource(@PathVariable(required = false,value = "resourceId") String resourceId,Model model) {
//        log.info("Resource--ModeolAttribute-------START");
//        if(ObjectUtil.isNotNull(resourceId) ) {
//            log.info("Resource--ModeolAttribute-------BEGINE-----"+resourceId);
//            CJViewResource cjViewResource = cjResourceService.getResourceById(resourceId);
//            log.info("Resource--ModeolAttribute-------END-----"+cjViewResource);
//            model.addAttribute("cjViewResource", cjViewResource);
//        }
//    }
    /*
    * resource,基本信息更新
    * */
    @PutMapping(value = "/{resourceId}")
    public  CJAjaxResult updateResource( @RequestBody @Valid CJViewResource cjViewResource) {
        log.info(String.valueOf(cjViewResource));
        cjResourceService.updateResource(cjViewResource);
        return CJAjaxResult.success("更新成功");
    }

    /*
     * 根据resource_id删除对应的资源，及其子资源
     * */
    @DeleteMapping(value = "/{resourceId}")
    public  CJAjaxResult deleteResource( @PathVariable(required = true,value = "resourceId") String resourceId) {

        cjResourceService.deleteResource(resourceId);
        return CJAjaxResult.success("删除成功");
    }

}
