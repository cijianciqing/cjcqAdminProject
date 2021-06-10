package com.ns.cjcq.security.crud.service;

import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import com.ns.cjcq.common.dataTables.entity.CJDataTablesReturnData;
import com.ns.cjcq.common.select2.entity.CJSelect2Entity;
import com.ns.cjcq.security.crud.dao.CJRoleRepository;
import com.ns.cjcq.security.crud.domain.CJRole;

import com.ns.cjcq.security.crud.domain.CJUser;
import com.ns.cjcq.security.crud.dvo.CJEditUser;
import com.ns.cjcq.security.crud.dvo.CJRoleDataTableSearchBean;
import com.ns.cjcq.security.crud.dvo.CJViewRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class CJRoleService {
    @Autowired
    CJDozerUtil cjDozerUtil;

    @Autowired
    CJRoleRepository cjRoleRepository;

    public void test() {

    }


    public CJDataTablesReturnData<CJViewRole> getAllRole(CJRoleDataTableSearchBean cjDataTablesSearchBean) {
        Pageable pageable = PageRequest.of(cjDataTablesSearchBean.getPage() - 1, cjDataTablesSearchBean.getLimit());


        Specification<CJRole> specification = (Specification<CJRole>) (root, query, criteriaBuilder) -> {
            Path<Object> name = root.get("name");
            Path<Object> roleTag = root.get("roleTag");

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotEmpty(cjDataTablesSearchBean.getName())) {
                predicates.add(criteriaBuilder.like(name.as(String.class), "%" + cjDataTablesSearchBean.getName() + "%"));
            }
            if (StringUtils.isNotEmpty(cjDataTablesSearchBean.getRoleTag())) {
                predicates.add(criteriaBuilder.like(roleTag.as(String.class), "%" + cjDataTablesSearchBean.getRoleTag() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<CJRole> all = cjRoleRepository.findAll(specification, pageable);
        List<CJViewRole> convertor = cjDozerUtil.convertor(all.getContent(), CJViewRole.class);
        CJDataTablesReturnData<CJViewRole> returnData = new CJDataTablesReturnData<>();
        returnData.setData(convertor);
        returnData.setDraw(cjDataTablesSearchBean.getDraw());
        returnData.setTotal(all.getTotalElements());

        return returnData;
    }


    public void saveRole(CJViewRole cjViewRole) {
        CJRole convertor = cjDozerUtil.convertor(cjViewRole, CJRole.class);
        cjRoleRepository.save(convertor);
    }

    public CJViewRole getRoleById(String roleId) {
        CJRole one = cjRoleRepository.getOne(Long.valueOf(roleId));
        CJViewRole convertor = cjDozerUtil.convertor(one, CJViewRole.class);
        return convertor;
    }

    public void updateRole(CJViewRole cjViewRole) {
        CJRole convertor = cjDozerUtil.convertor(cjViewRole, CJRole.class);
        cjRoleRepository.save(convertor);
    }

    public void delRole(String roleId) {
        cjRoleRepository.deleteById(Long.valueOf(roleId));
    }

    public List<CJSelect2Entity> getPlainRoles() {
        List<CJRole> all = cjRoleRepository.findAll();
        return cjDozerUtil.convertor(all, CJSelect2Entity.class);
    }
}
