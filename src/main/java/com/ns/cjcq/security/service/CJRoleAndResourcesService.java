package com.ns.cjcq.security.service;

import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.ns.cjcq.common.zTree.entity.CJZTreeNodeResourceEntity;
import com.ns.cjcq.security.dao.CJResourceRepository;
import com.ns.cjcq.security.dao.CJRoleAndResourceRepository;
import com.ns.cjcq.security.domain.CJResource;
import com.ns.cjcq.security.domain.CJRole;
import com.ns.cjcq.security.domain.CJRoleAndResource;
import com.ns.cjcq.security.dvo.CJViewResource;
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
public class CJRoleAndResourcesService {

    @Autowired
    CJResourceRepository cjResourceRepository;
    @Autowired
    CJRoleAndResourceRepository cjRoleAndResourceRepository;
    @Autowired
    CJDozerUtil cjDozerUtil;




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
            cJZTreeNodeResourceEntity.setNocheck(false);
//            cJZTreeNodeResourceEntity.setNocheck(!cJZTreeNodeResourceEntity.getCjResourceType().equalsIgnoreCase("button"));
            cJZTreeNodeResourceEntity.setChecked(selectedResources.contains(cJZTreeNodeResourceEntity.getId()));

        }).collect(Collectors.toList());


        return collect;
    }


    @Transactional
    public void setResourcesByRoleId(String roleId, List<Long> resourceIds) {
        CJRole cjRole = new CJRole();
        cjRole.setId(Long.valueOf(roleId));

        cjRoleAndResourceRepository.deleteAll(cjRoleAndResourceRepository.findByCjRole_Id(Long.valueOf(roleId)));
//        cjRoleAndResourceRepository.deleteInBatch(cjRoleAndResourceRepository.findByCjRole_Id(Long.valueOf(roleId)));

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
