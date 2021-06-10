package com.ns.cjcq.security.authentication;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/*
* 用于登录用户认证、授权
* */
@Setter
@Getter
public class MyUserDetailsService implements UserDetailsService {

//    @Autowired
    PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public MyUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("jsjzx.wlyw.springbootwithsecurity.security.authentication.MyUserDetailsService 登录用户名："+username);

        String password = passwordEncoder.encode("123456");
        logger.info("jsjzx.wlyw.springbootwithsecurity.security.authentication.MyUserDetailsService 登录密码："+password);

        return new User(username,password,
                true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_WQN")
                );

    }
}
