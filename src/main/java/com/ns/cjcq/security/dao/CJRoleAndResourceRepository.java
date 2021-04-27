package com.ns.cjcq.security.dao;

import com.ns.cjcq.security.domain.CJRoleAndResource;
import com.ns.cjcq.security.domain.CJUserAndRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CJRoleAndResourceRepository extends JpaRepository<CJRoleAndResource, Long>, JpaSpecificationExecutor<CJRoleAndResource> {
    List<CJRoleAndResource> findByCjRole_Id(Long roleId);
}
