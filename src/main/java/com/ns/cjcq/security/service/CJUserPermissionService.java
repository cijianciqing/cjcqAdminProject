package com.ns.cjcq.security.service;

import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import com.ns.cjcq.common.dataTables.entity.CJDataTablesReturnData;
import com.ns.cjcq.common.select2.entity.CJSelect2Entity;
import com.ns.cjcq.security.common.CJSpringSecurityProperty;
import com.ns.cjcq.security.dao.*;
import com.ns.cjcq.security.domain.*;
import com.ns.cjcq.security.dvo.CJEditUser;
import com.ns.cjcq.security.dvo.CJPermissionResource;
import com.ns.cjcq.security.dvo.CJUserDataTableSearchBean;
import com.ns.cjcq.security.dvo.CJViewUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Service
public class CJUserPermissionService {

    @Autowired
    CJSpringSecurityProperty cjSpringSecurityProperty;

    @Autowired
    CJDozerUtil cjDozerUtil;


    @Autowired
    CJUserRepository cjUserRepository;
    @Autowired
    CJUserAndRoleRepository cjUserAndRoleRepository;
    @Autowired
    CJRoleRepository cjRoleRepository;
    @Autowired
    CJRoleAndResourceRepository cjRoleAndResourceRepository;
    @Autowired
    CJResourceRepository cjResourceRepository;

    public List<CJResource> getMuneTree( List<CJResource> allMenus) {

        // 复制data数据
        List<CJResource> menuList = new ArrayList<>(allMenus);
        // 遍历两次data来组装带有children关联性的对象，如果找到子级就删除menuList的数据
        for (CJResource menu : allMenus) {
            for (CJResource menu2 : allMenus) {
                //如果本级id与数据的父id相同，就说明是子父级关系
                if (menu.getId().equals(menu2.getParent().getId())) {
                    menu.getChilds().add(menu2);
                    menuList.remove(menu2);
                }
            }
        }
        return menuList;
    }

    public List<CJPermissionResource> getResourcesById(Long userId) {
        Sort sort = new Sort(Sort.Direction.ASC, "parent_id");
        Sort sort02 = new Sort(Sort.Direction.ASC, "sort");//根据sortNo进行排序
        Sort and = sort.and(sort02);


        /*
        * 获取特定用户的resources
        * */
       /* List<CJUserAndRole> byCjUser_id = cjUserAndRoleRepository.findByCjUser_Id(userId);

        //获取用户所有的role
        List<CJRole> allRoles = byCjUser_id.stream().map(cjUserAndRole -> {
            return cjUserAndRole.getCjRole();
        }).collect(Collectors.toList());

        Set<CJRoleAndResource> byCjRoleId = new HashSet<>();
        allRoles.stream().peek(cjRole -> {
            byCjRoleId.addAll(cjRoleAndResourceRepository.findByCjRole_Id(cjRole.getId()));
        });

        //获取用户所有的resources
        List<CJResource> allResources = byCjRoleId.stream()
                .filter(cjRoleAndResource -> cjRoleAndResource.getCjResource().getCjResourceType().getMessage().equalsIgnoreCase("menu"))
                .map(CJRoleAndResource::getCjResource)
                .collect(Collectors.toList());
*/
//        获取super用户的权限--即获取所有的menus
        List<CJResource> menus = cjResourceRepository.findAll(and).stream()
                .filter(cjResource -> cjResource.getCjResourceType().getMessage().equalsIgnoreCase("menu"))
                .peek(cjResource -> cjResource.setChilds(new ArrayList<>()))
                .collect(Collectors.toList());


        return cjDozerUtil.convertor(getMuneTree(menus), CJPermissionResource.class);
    }

    public CJViewUser getUserInfo(Long id) {
        List<CJPermissionResource> resourcesById = getResourcesById(id);

        CJViewUser cjViewUser = new CJViewUser();
        cjViewUser.setShowname("王童");
        cjViewUser.setEmail("wangtong@nanshan.com.cn");
        cjViewUser.setTelephoneNo("15264549822");
        cjViewUser.setUsername("wangtong");
        cjViewUser.setCjMenus(resourcesById);
        return cjViewUser;
    }


}
