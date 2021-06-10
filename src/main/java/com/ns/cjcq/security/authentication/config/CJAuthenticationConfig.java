package com.ns.cjcq.security.authentication.config;

import com.ns.cjcq.security.authentication.MyUserDetailsService;
import com.ns.cjcq.security.common.properties.ImoocSecurityProperties;
import com.ns.cjcq.security.validator.interfaceAndImpiment.ValidatorGenerator;
import com.ns.cjcq.security.validator.interfaceAndImpiment.ValidatorGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CJAuthenticationConfig {

    @Autowired
    ImoocSecurityProperties securityProperties;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    @ConditionalOnMissingBean(name="myUserDetailsService")
    public MyUserDetailsService myUserDetailsService(){
        MyUserDetailsService myUserDetailsService =  new MyUserDetailsService(passwordEncoder);
        return myUserDetailsService;
    }
}
