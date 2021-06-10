package com.ns.cjcq;

import com.ns.cjcq.security.crud.dao.*;
import com.ns.cjcq.security.crud.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CjcqApplicationTests {
    @Autowired
    CJUserRepository cjUserRepository;
    @Autowired
    CJUserAndRoleRepository cjUserAndRoleRepository;
    @Autowired
    CJRoleRepository cjRoleRepository;
    @Autowired
    CJResourceRepository cjResourceRepository;
    @Autowired
    CJRoleAndResourceRepository cjRoleAndResourceRepository;

    /*
    * 添加超级管理员和管理员
    * */
    @Test
    public void initUser() {
        CJUser cjUser = new CJUser();
        cjUser.setUsername("super");
        cjUser.setShowname("超级管理员");
        cjUser.setEmail("SuperEmail");
        cjUser.setPassword("SuperPassword");
        cjUser.setTelephoneNo("SuperTel");

        CJUser cjUser0 = new CJUser();
        cjUser0.setUsername("admin");
        cjUser0.setShowname("管理员");
        cjUser0.setEmail("AdminEmail");
        cjUser0.setPassword("AdminPassword");
        cjUser0.setTelephoneNo("AdminTel");

        cjUserRepository.save(cjUser);
        cjUserRepository.save(cjUser0);
    }

    /*
     * 添加管理管理员角色与普通角色
     * */
    @Test
    public void initRole() {
        CJRole cjRole = new CJRole();
        cjRole.setName("管理员角色");
        cjRole.setRoleTag("CJAdminRole");
        cjRole.setRoleDesc("用于后台管理");

        CJRole cjRole0 = new CJRole();
        cjRole0.setName("普通角色");
        cjRole0.setRoleTag("CJNormalRole");
        cjRole0.setRoleDesc("普通用户的角色");

        cjRoleRepository.save(cjRole);
        cjRoleRepository.save(cjRole0);
    }

    /*
     * 添加用户与角色的映射
     * */
    @Test
    public void initUserRole() {
        CJUserAndRole cjUserAndRole = new CJUserAndRole();
        cjUserAndRole.setCjUser(cjUserRepository.getOne(2L));
        cjUserAndRole.setCjRole(cjRoleRepository.getOne(1L));

        cjUserAndRoleRepository.save(cjUserAndRole);
    }


    /*
    * 添加測試用戶
    * */
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
