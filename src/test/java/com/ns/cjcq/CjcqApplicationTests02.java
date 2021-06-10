package com.ns.cjcq;

import com.ns.cjcq.security.crud.dao.CJResourceRepository;
import com.ns.cjcq.security.crud.dao.CJRoleRepository;
import com.ns.cjcq.security.crud.dao.CJUserRepository;
import com.ns.cjcq.security.crud.domain.CJResource;
import com.ns.cjcq.security.crud.domain.CJResourceType;
import com.ns.cjcq.security.crud.domain.CJRole;
import com.ns.cjcq.security.crud.domain.CJUser;
import com.ns.cjcq.security.crud.dvo.CJPermissionResource;
import com.ns.cjcq.security.crud.service.CJUserPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CjcqApplicationTests02 {
    @Autowired
    CJUserRepository cjUserRepository;
    @Autowired
    CJRoleRepository cjRoleRepository;
    @Autowired
    CJResourceRepository cjResourceRepository;

    @Autowired
    CJUserPermissionService cjUserPermissionService;

    @Test
    public void tt() {
//        Set<CJResource> resourcesById = cjUserPermissionService.getResourcesById(1L);
//        System.out.println(resourcesById);
    }



}
