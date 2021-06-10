package com.ns.cjcq;

import com.ns.cjcq.security.crud.dao.*;
import com.ns.cjcq.security.crud.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CjcqApplicationTests03 {
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
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void getPassword(){
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }



}
