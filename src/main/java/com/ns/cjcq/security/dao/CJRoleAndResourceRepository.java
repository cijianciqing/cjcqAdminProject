package com.ns.cjcq.security.dao;

import com.ns.cjcq.security.domain.CJRoleAndResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CJRoleAndResourceRepository extends JpaRepository<CJRoleAndResource, Long>, JpaSpecificationExecutor<CJRoleAndResource> {
}
