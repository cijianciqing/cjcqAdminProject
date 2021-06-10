package com.ns.cjcq.security.config;


import com.ns.cjcq.security.authorize.manager.ImoocAuthorizeConfigManager;
import com.ns.cjcq.security.common.CJSpringSecurityProperty;
import com.ns.cjcq.security.common.properties.ImoocSecurityProperties;
import com.ns.cjcq.security.common.properties.SecurityConstants;
import com.ns.cjcq.security.validator.filter.ValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.sql.DataSource;


@EnableWebSecurity
public class MyBrowserSpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ImoocSecurityProperties securityProperties;

    @Autowired
    CJSpringSecurityProperty cjSpringSecurityProperty;

    //认证成功处理器
    @Autowired
    AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    //认证处理器
    @Autowired
    AuthenticationFailureHandler imoocAuthenctiationFailureHandler;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    ImoocAuthorizeConfigManager authorizeConfigManager;

    //用于登录用户密码加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //用于存储remember-me功能的token
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //在初始化时创建表(第二次启动需要关闭下一行代码)
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        if(cjSpringSecurityProperty.getCjNeedValidate()){
            ValidatorFilter validatorFilter = new ValidatorFilter();
            validatorFilter.setAuthenticationFailureHandler(imoocAuthenctiationFailureHandler);
            validatorFilter.setSecurityProperties(securityProperties);
            validatorFilter.afterPropertiesSet();
            http.addFilterBefore(validatorFilter, UsernamePasswordAuthenticationFilter.class);
        }


        //super.configure(http);
        //定制请求的授权规则
//        http.authorizeRequests()
//                //未授权访问url和登录页面url允许访问
//                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
//                        securityProperties.getBrowser().getLoginPage(),
//                        "/code/image",
//                        "/session/invalid",
//                        "/imooc-logout.html").permitAll()
//                .antMatchers("/user").hasRole("WQN")
//                .antMatchers("/user/me").hasRole("WQN")
//                .anyRequest().authenticated();

        authorizeConfigManager.config(http.authorizeRequests());

        //开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面

                http.formLogin()
                //设置登录页面参数名称
                .usernameParameter("user").passwordParameter("pwd")
                //设置未登录（未授权）用户访问未授权url,设置认证处理url
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                //设置认证成功处理
                .successHandler(imoocAuthenticationSuccessHandler)
                //设置认证失败处理
                .failureHandler(imoocAuthenctiationFailureHandler);
                //跨站登录检测


                http.csrf().disable();
        //1、/login来到登陆页
        //2、重定向到/login?error表示登陆失败
        //3、更多详细规定
        //4、默认post形式的 /login代表处理登陆
        //5、一但定制loginPage；那么 loginPage的post请求就是登陆



        http.logout()
                //配置用户退出的URL
                .logoutUrl(securityProperties.getBrowser().getSignOutUrl())
                //删除当前用户session,否则会产生invalidSession错误
                .deleteCookies("JSESSIONID")
                //注销成功后的处理
                .logoutSuccessHandler(logoutSuccessHandler);


        //开启记住我功能
        http.rememberMe()
                .rememberMeParameter("rmb")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService);
        //登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        //点击注销会删除cookie.


        //session配置
        http.sessionManagement()
                //session失效后，访问的页面
                .invalidSessionStrategy(invalidSessionStrategy)
                //不允许单个帐户多次登录
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                //设置超出最大session，不允许再登录
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                //处理session超时策略
                .expiredSessionStrategy(sessionInformationExpiredStrategy);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
//        配置css.js.等静态资源
        web.ignoring().antMatchers("/cjThirdStatic/**");
        web.ignoring().antMatchers("/node_modules/**");
        web.ignoring().antMatchers("/sb2Static/**");
        web.ignoring().antMatchers("/icon/**");
        web.ignoring().antMatchers("/cjStatic/**");
    }

}
