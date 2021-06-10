package com.ns.cjcq.security.logout.config;

import com.ns.cjcq.security.common.properties.ImoocSecurityProperties;

import com.ns.cjcq.security.logout.ImoocLogoutScucessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class MyLogoutConfig {

    @Autowired
    ImoocSecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new ImoocLogoutScucessHandler(securityProperties);
    }
}
