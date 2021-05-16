package com.ns.cjcq.security.dao;

import com.ns.cjcq.security.domain.CJResource;
import com.ns.cjcq.security.domain.CJResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface CJResourceRepository extends JpaRepository<CJResource, Long>, JpaSpecificationExecutor<CJResource> {
    Set<CJResource> findByParent_Id(Long parentId);
    Set<CJResource> findByParent_IdAndCjResourceTypeOrderBySortAsc(Long parentId, CJResourceType cjResourceType);
}
