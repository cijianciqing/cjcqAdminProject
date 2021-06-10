package com.ns.cjcq.security.crud.dao;

import com.ns.cjcq.security.crud.domain.CJRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CJRoleRepository extends JpaRepository<CJRole, Long>, JpaSpecificationExecutor<CJRole> {
}
