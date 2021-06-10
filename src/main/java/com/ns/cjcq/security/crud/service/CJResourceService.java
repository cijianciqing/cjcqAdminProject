package com.ns.cjcq.security.crud.service;

import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ns.cjcq.common.zTree.entity.CJZTreeNodeResourceEntity;
import com.ns.cjcq.security.crud.dao.CJResourceRepository;
import com.ns.cjcq.security.crud.dao.CJRoleAndResourceRepository;
import com.ns.cjcq.security.crud.domain.CJResource;
import com.ns.cjcq.security.crud.domain.CJRole;
import com.ns.cjcq.security.crud.domain.CJRoleAndResource;
import com.ns.cjcq.security.crud.dvo.CJViewResource;
import com.ns.cjcq.security.crud.dvo.CJViewRole;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CJResourceService {

    @Autowired
    CJResourceRepository cjResourceRepository;
    @Autowired
    CJRoleAndResourceRepository cjRoleAndResourceRepository;
    @Autowired
    CJDozerUtil cjDozerUtil;

    public String convertToFontAwesomeIcon(String resourceIcon, String recourceName){
        return "<i class=\""+ resourceIcon +"\"></i>"+recourceName;
    }
    /*
    *用于zTree
    * 获取ztree专用的Title[CJResource中为resDesc]
    * */

    @SneakyThrows
    private String transferDesc(CJResource cjResource)  {
        ObjectMapper objectMapper = new ObjectMapper();
        String toJsonString = objectMapper.writeValueAsString(cjResource);
        return toJsonString;
    }
    /*
    * 用于Resource--zTree
    * */
    public List<CJZTreeNodeResourceEntity> getAllResoucres() {

        Sort sort = new Sort(Sort.Direction.ASC, "parent_id");
        Sort sort02 = new Sort(Sort.Direction.ASC, "sort");//根据sortNo进行排序
        Sort and = sort.and(sort02);

        List<CJResource> all = cjResourceRepository.findAll(and);
        List<CJResource> collect = all.stream().peek(cjResource -> {
            String name = cjResource.getName();
            String icon = cjResource.getFontIcon();
            String desc = null;
            desc = transferDesc(cjResource);
            cjResource.setResDesc(desc);
            String s = convertToFontAwesomeIcon(icon, name);
            cjResource.setName(s);
        }).collect(Collectors.toList());
        return cjDozerUtil.convertor(collect, CJZTreeNodeResourceEntity.class);
    }

    /*
     * 用于role资源分配--zTree
     * */
    public List<CJZTreeNodeResourceEntity> getResourcesByRoleID(String roleId) {


        //获取role的Resource
        List<CJRoleAndResource> byCjRole_id = cjRoleAndResourceRepository.findByCjRole_Id(Long.valueOf(roleId));
        List<Long> selectedResources = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(byCjRole_id)){
            byCjRole_id.forEach(cjRoleAndResource -> {
                selectedResources.add(cjRoleAndResource.getCjResource().getId());
            });
        }
        //获取所有的resource
        Sort sort = new Sort(Sort.Direction.ASC, "parent_id");
        Sort sort02 = new Sort(Sort.Direction.ASC, "sort");//根据sortNo进行排序
        Sort and = sort.and(sort02);
        List<CJResource> all = cjResourceRepository.findAll(and);
        List<CJZTreeNodeResourceEntity> convertor = cjDozerUtil.convertor(all, CJZTreeNodeResourceEntity.class);

        List<CJZTreeNodeResourceEntity> collect =convertor.stream().peek(cJZTreeNodeResourceEntity->{

            cJZTreeNodeResourceEntity.setNocheck(!cJZTreeNodeResourceEntity.getCjResourceType().equalsIgnoreCase("button"));
            cJZTreeNodeResourceEntity.setChecked(selectedResources.contains(cJZTreeNodeResourceEntity.getId()));

        }).collect(Collectors.toList());


        return collect;
    }


    public void saveResource(CJViewResource cjViewResource) {
        CJResource convertor = cjDozerUtil.convertor(cjViewResource, CJResource.class);
        cjResourceRepository.save(convertor);
    }

    public CJViewResource getResourceById(String resourceId) {
        CJResource one = cjResourceRepository.getOne(Long.valueOf(resourceId));
        return cjDozerUtil.convertor(one, CJViewResource.class);
    }

    public void updateResource(CJViewResource cjViewResource) {
        CJResource convertor = cjDozerUtil.convertor(cjViewResource, CJResource.class);
//        log.info(String.valueOf(convertor));
        cjResourceRepository.save(convertor);
    }

    /*
    * 根据resource_id删除对应的资源，及其子资源
    * */
    public void deleteResource(String resourceId) {
        cjResourceRepository.deleteById(Long.valueOf(resourceId));
    }


    @Transactional
    public void setResourcesByRoleId(String roleId, List<Long> resourceIds) {
        CJRole cjRole = new CJRole();
        cjRole.setId(Long.valueOf(roleId));
        Set<CJRoleAndResource> collect = resourceIds.stream().map(resourceId -> {
                    CJResource cjResource = new CJResource();
                    cjResource.setId(resourceId);

                    CJRoleAndResource cjRoleAndResource = new CJRoleAndResource();
                    cjRoleAndResource.setCjRole(cjRole);
                    cjRoleAndResource.setCjResource(cjResource);

                    return cjRoleAndResource;
                }
        ).collect(Collectors.toSet());
        cjRoleAndResourceRepository.saveAll(collect);

    }



}
