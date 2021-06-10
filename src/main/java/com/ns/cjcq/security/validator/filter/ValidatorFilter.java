package com.ns.cjcq.security.validator.filter;

import com.ns.cjcq.security.common.properties.ImoocSecurityProperties;
import com.ns.cjcq.security.validator.ImageCode;
import com.ns.cjcq.security.validator.controller.ValidatorController;
import com.ns.cjcq.security.validator.exception.ValidatorException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/*
* OncePerRequestFilter保证一次只调用一次
* */
public class ValidatorFilter extends OncePerRequestFilter implements InitializingBean {

    //在MyBrowserSpringSecurityConfig中进行装配
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private ImoocSecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public ImoocSecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(ImoocSecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    //InitializeBean的方法
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        //此处需要注意，如果不加if,并且properties没有设置url，则会出现空指针错误。。？？？？？
        if (securityProperties.getCode().getImage().getUrl() != null) {
            String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");

            for (String configURL : configUrls) {
                urls.add(configURL);
            }
        }
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }
        if (action) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidatorException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidatorController.My_Session_Key);

        //imageCode:登录页面中校验码的名称
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidatorException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidatorException("验证码不存在");
        }

        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(request, ValidatorController.My_Session_Key);
            throw new ValidatorException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidatorException("验证码不匹配");
        }


    }
}
