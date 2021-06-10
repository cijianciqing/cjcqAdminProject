package com.ns.cjcq.security.authorize.manager;

import com.ns.cjcq.security.authorize.MyAuthorizeConfigProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(Integer.MIN_VALUE)
public class ImoocAuthorizeConfigManager implements MyAuthorizeConfigManager {

    //所有实现MyAuthorizeConfigProvider接口的授权配置
    //之所以使用List不用set：因为security模块的授权需要放在最上面，demo的授权需要最后起效
    @Autowired
    private List<MyAuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry managerConfig) {
        for (MyAuthorizeConfigProvider  authorizeConfigProvider: authorizeConfigProviders) {
            authorizeConfigProvider.config(managerConfig);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(authorizeConfigProvider.getClass().getName());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        }
        //会覆盖demo模块的anyRequest配置
       // managerConfig.anyRequest().authenticated();

    }
}
