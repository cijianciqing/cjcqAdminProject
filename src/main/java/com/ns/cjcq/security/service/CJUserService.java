package com.ns.cjcq.security.service;

import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ns.cjcq.common.dataTables.entity.CJDataTablesReturnData;
import com.ns.cjcq.common.select2.entity.CJSelect2Entity;
import com.ns.cjcq.common.zTree.entity.CJZTreeNodeResourceEntity;
import com.ns.cjcq.security.dao.CJRoleRepository;
import com.ns.cjcq.security.dao.CJUserAndRoleRepository;
import com.ns.cjcq.security.dao.CJUserRepository;
import com.ns.cjcq.security.domain.CJRole;
import com.ns.cjcq.security.domain.CJUserAndRole;
import com.ns.cjcq.security.dvo.CJEditUser;
import com.ns.cjcq.security.dvo.CJUserDataTableSearchBean;
import com.ns.cjcq.security.dvo.CJViewUser;
import com.ns.cjcq.security.domain.CJUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class CJUserService {
    @Autowired
    CJDozerUtil cjDozerUtil;

    @Autowired
    CJRoleRepository cjRoleRepository;

    @Autowired
    CJUserRepository cjUserRepository;
    @Autowired
    CJUserAndRoleRepository cjUserAndRoleRepository ;



    public CJDataTablesReturnData<CJViewUser> getAllUser(CJUserDataTableSearchBean cjDataTablesSearchBean) {
        Pageable pageable =  PageRequest.of(cjDataTablesSearchBean.getPage()-1,cjDataTablesSearchBean.getLimit());


        Specification<CJUser> specification = (Specification<CJUser>) (root, query, criteriaBuilder) -> {
            Path<Object> username = root.get("username");
            Path<Object> showname = root.get("showname");
            Path<Object> telephoneNo = root.get("telephoneNo");
            Path<Object> email = root.get("email");

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.isNotEmpty(cjDataTablesSearchBean.getUsername())){
                predicates.add(criteriaBuilder.like(username.as(String.class), "%"+cjDataTablesSearchBean.getUsername()+"%"));
            }
            if(StringUtils.isNotEmpty(cjDataTablesSearchBean.getShowname())){
                predicates.add(criteriaBuilder.like(showname.as(String.class), "%"+cjDataTablesSearchBean.getShowname()+"%"));
            }
            if(StringUtils.isNotEmpty(cjDataTablesSearchBean.getTelephoneNo())){
                predicates.add(criteriaBuilder.like(telephoneNo.as(String.class), "%"+cjDataTablesSearchBean.getTelephoneNo()+"%"));
            }
            if(StringUtils.isNotEmpty(cjDataTablesSearchBean.getEmail())){
                predicates.add(criteriaBuilder.like(email.as(String.class), "%"+cjDataTablesSearchBean.getEmail()+"%"));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<CJUser> all = cjUserRepository.findAll(specification, pageable);
        List<CJViewUser> convertor = cjDozerUtil.convertor(all.getContent(), CJViewUser.class);
        CJDataTablesReturnData<CJViewUser> returnData = new CJDataTablesReturnData<>();
        returnData.setData(convertor);
        returnData.setDraw(cjDataTablesSearchBean.getDraw());
        returnData.setTotal(all.getTotalElements());

        return returnData;
    }

    @Transactional
    public void saveUser(CJEditUser cjEditUser) {
        //保存用户
        CJUser convertor = cjDozerUtil.convertor(cjEditUser, CJUser.class);
        CJUser savedUser = cjUserRepository.save(convertor);

        //保存UserAndRole
        List<CJSelect2Entity> userRoles = cjEditUser.getUserRoles();
        List<CJRole> roles;
        List<CJUserAndRole> userAndRoles = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(userRoles)){
            roles = cjDozerUtil.convertor(userRoles, CJRole.class);
            System.out.println(roles);
            roles.forEach(role->userAndRoles.add(new CJUserAndRole(savedUser,role)));
        }
        cjUserAndRoleRepository.saveAll(userAndRoles);
    }

    public void delUser(String userId) {
        cjUserRepository.deleteById(Long.valueOf(userId));
    }

    public CJEditUser getUserById(String userId) {
        CJUser user = userId.equalsIgnoreCase("0") ? new CJUser() : cjUserRepository.getOne(Long.valueOf(userId));
        //获取userId的所有role_id
        Set<Long> userRoleIds = userId.equalsIgnoreCase("0") ? new HashSet<>() : user.getCjUserAndRoles().stream().map(s -> s.getCjRole().getId()).collect(Collectors.toSet());
        //获取所有的role,并根据user的role进行转换-->user的role对应的设置为selected
        List<CJSelect2Entity> allRoles = cjDozerUtil.convertor(cjRoleRepository.findAll(), CJSelect2Entity.class);
        List<CJSelect2Entity> userRoles =allRoles.stream().peek(cjSelect2Option->{
            cjSelect2Option.setSelected(userRoleIds.contains(cjSelect2Option.getId()));
        }).collect(Collectors.toList());

        CJEditUser cjEditUser = cjDozerUtil.convertor(user, CJEditUser.class);
        cjEditUser.setUserRoles(userRoles);
        return  cjEditUser;

    }

    @Transactional
    public void updateUser(CJEditUser cjEditUser) {
        CJUser updateUser = cjDozerUtil.convertor(cjEditUser, CJUser.class);
        cjUserRepository.save(updateUser);

        //获取用户之前的userAndRole

       cjUserAndRoleRepository.deleteAllByCjUser_Id(updateUser.getId());


        //保存UserAndRole
        List<CJSelect2Entity> userRoles = cjEditUser.getUserRoles();
        List<CJRole> roles;
        List<CJUserAndRole> userAndRoles = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(userRoles)){
            roles = cjDozerUtil.convertor(userRoles, CJRole.class);
            System.out.println(roles);
            roles.forEach(role->userAndRoles.add(new CJUserAndRole(updateUser,role)));
        }
        cjUserAndRoleRepository.saveAll(userAndRoles);

    }
}
