package com.ns.cjcq.security.authorize;

import com.ns.cjcq.security.common.properties.ImoocSecurityProperties;
import com.ns.cjcq.security.common.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ImoocAuthorizConfigProvider implements MyAuthorizeConfigProvider {
    @Autowired
    ImoocSecurityProperties securityProperties;

    //安全模块的授权
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
         config.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                 //登录页面
                 securityProperties.getBrowser().getLoginPage(),
                 //首页
                 securityProperties.getBrowser().getIndex(),
                 // /code/image
                 securityProperties.getCode().getSms().getUrl(),
                 securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                 //"/imooc-logout.html"
                 securityProperties.getBrowser().getSignOutTo()
                ).permitAll();
    }


}
