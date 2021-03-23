package com.ns.cjcq.security.dao;

import com.ns.cjcq.security.domain.CJResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CJResourceRepository extends JpaRepository<CJResource, Long>, JpaSpecificationExecutor<CJResource> {
}
