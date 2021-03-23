package com.ns.cjcq.security.dao;

import com.ns.cjcq.security.domain.CJUserAndRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CJUserAndRoleRepository extends JpaRepository<CJUserAndRole, Long>, JpaSpecificationExecutor<CJUserAndRole> {
}
