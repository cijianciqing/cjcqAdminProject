package com.ns.cjcq;

import com.ns.cjcq.security.dao.CJResourceRepository;
import com.ns.cjcq.security.dao.CJRoleRepository;
import com.ns.cjcq.security.dao.CJUserRepository;
import com.ns.cjcq.security.domain.CJResource;
import com.ns.cjcq.security.domain.CJResourceType;
import com.ns.cjcq.security.domain.CJRole;
import com.ns.cjcq.security.domain.CJUser;
import com.ns.cjcq.security.dvo.CJPermissionResource;
import com.ns.cjcq.security.service.CJUserPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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
