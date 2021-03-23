package com.ns.cjcq.security.service;

import cn.com.ns.cj.cjuniversalspringbootstarter.dozer.CJDozerUtil;
import com.ns.cjcq.security.dao.CJUserRepository;
import com.ns.cjcq.security.dto.CJViewUser;
import com.ns.cjcq.security.domain.CJUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class CJUserService {
    @Autowired
    CJDozerUtil cjDozerUtil;

    @Autowired
    CJUserRepository cjUserRepository;

    public void test(){

    }

    public List<CJViewUser> getAllUser() {
        List<CJUser> allUsers = cjUserRepository.findAll();
        List<CJViewUser> convertor = cjDozerUtil.convertor(allUsers, CJViewUser.class);
        return convertor;
    }
}
