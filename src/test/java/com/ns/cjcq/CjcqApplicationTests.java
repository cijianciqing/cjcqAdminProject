package com.ns.cjcq;

import com.ns.cjcq.security.dao.CJResourceRepository;
import com.ns.cjcq.security.dao.CJRoleRepository;
import com.ns.cjcq.security.dao.CJUserRepository;
import com.ns.cjcq.security.domain.CJResource;
import com.ns.cjcq.security.domain.CJResourceType;
import com.ns.cjcq.security.domain.CJRole;
import com.ns.cjcq.security.domain.CJUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CjcqApplicationTests {
    @Autowired
    CJUserRepository cjUserRepository;
    @Autowired
    CJRoleRepository cjRoleRepository;
    @Autowired
    CJResourceRepository cjResourceRepository;

    @Test
    public void contextLoads() {
        CJUser cjUser = new CJUser();
        cjUser.setUsername("account");
        cjUser.setShowname("用户");
        cjUser.setEmail("email");
        cjUser.setPassword("password");
        cjUser.setTelephoneNo("tel");
        cjUserRepository.save(cjUser);
    }

    @Test
    @Rollback(false)
    public void test01() {
        List<CJUser> cjUsers = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            CJUser cjUser = new CJUser();
            cjUser.setId((long) i);
            cjUser.setUsername("account" + i);
            cjUser.setShowname("用户" + i);
            cjUser.setEmail("email" + i);
            cjUser.setPassword("password" + i);
            cjUser.setTelephoneNo("tel" + i);
            cjUsers.add(cjUser);
        }
        cjUserRepository.saveAll(cjUsers);
    }

    @Test
    @Rollback(false)
    public void test02() {
        List<CJRole> cjroles = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            CJRole cjRole = new CJRole();
            //cjRole.setId((long) i);
            cjRole.setName("角色" + i);
            cjRole.setRoleTag("role" + i);
            cjRole.setRoleDesc("roleDesc" + i);
            cjroles.add(cjRole);
        }
        cjRoleRepository.saveAll(cjroles);
    }



    /*
    * 初始化resource
    * */
    @Test
    @Rollback(false)
    public void test0300() {
        //根节点
        CJResource rootResource = new CJResource();
//        rootResource.setCjResourceType(CJResourceType.DIR);
        rootResource.setName("cjRootResource");
        rootResource.setUrl("#");
        rootResource.setFontIcon("fas fa-anchor");
       // rootResource.setId(999L);

    }


    @Test
    @Rollback(false)
    public void test03() {
        //根节点id为1
        CJResource rootResource = new CJResource();
//        rootResource.setCjResourceType(CJResourceType.DIR);
        rootResource.setName("cjRootResource");
        rootResource.setUrl("#");
        rootResource.setCjResourceType(CJResourceType.DIR);
        rootResource.setFontIcon("fab fa-fort-awesome");
        cjResourceRepository.save(rootResource);

        List<CJResource> cjResources = new ArrayList<>();
        for (Long i = 2L; i < 10; i++) {
            CJResource cjResource = new CJResource();
//            cjResource.setCjResourceType(CJResourceType.MENU);
            cjResource.setName("resource" + i);
            cjResource.setUrl("url" + i);
            cjResource.setFontIcon("fas fa-anchor");
            cjResource.setParent(cjResourceRepository.getOne(1L));
            cjResource.setCjResourceType(CJResourceType.MENU);
            cjResources.add(cjResource);

        }
        cjResourceRepository.saveAll(cjResources);


        List<CJResource> cjResources00 = new ArrayList<>();


        for (Long i = 2L; i <10; i++) {
            for (int j = 1; j < 3; j++) {
                CJResource cjResource00 = new CJResource();
//                cjResource00.setCjResourceType(CJResourceType.BUTTON);
                cjResource00.setCjResourceType(CJResourceType.BUTTON);
                cjResource00.setName("cresource" + j);
                cjResource00.setUrl("curl" + j);
                cjResource00.setFontIcon("fas fa-anchor");
                cjResource00.setParent(cjResourceRepository.getOne(i));

                cjResources00.add(cjResource00);

            }
        }

        cjResourceRepository.saveAll(cjResources00);
    }

}
