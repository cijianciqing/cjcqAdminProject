package com.ns.cjcq.security.dao;

import com.ns.cjcq.security.domain.CJUserAndRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CJUserAndRoleRepository extends JpaRepository<CJUserAndRole, Long>, JpaSpecificationExecutor<CJUserAndRole> {
    List<CJUserAndRole> findByCjUser_Id(Long userId);
    void deleteAllByCjUser_Id(Long userId);
}
