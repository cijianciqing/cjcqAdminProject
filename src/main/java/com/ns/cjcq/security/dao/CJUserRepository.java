package com.ns.cjcq.security.dao;

import com.ns.cjcq.security.domain.CJUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

    public interface CJUserRepository extends JpaRepository<CJUser, Long>, JpaSpecificationExecutor<CJUser> {
}
