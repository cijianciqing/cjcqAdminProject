package com.ns.cjcq.security.demo;

import com.ns.cjcq.security.authorize.MyAuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MAX_VALUE)
public class UserAuthorizeProviderImpl implements MyAuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config
                //.antMatchers("/user").hasRole("WQN")
                //.antMatchers("/user/me").hasRole("WW")
                //rbacService是jsjzx.wlyw.springbootwithsecurity.security.authentication.rabc.MyRBACServiceImpl
                //hasPermission是方法
                .anyRequest().access("@rbacService.hasPermission(request, authentication)");
    }
}
