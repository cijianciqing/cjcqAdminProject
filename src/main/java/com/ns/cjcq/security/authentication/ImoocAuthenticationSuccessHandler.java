/**
 * 
 */
package com.ns.cjcq.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ns.cjcq.security.common.properties.ImoocSecurityProperties;

import com.ns.cjcq.security.common.properties.LoginResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author zhailiang
 *
 * 认证成功处理器
 */
@Component("imoocAuthenticationSuccessHandler")
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	//spring自动注册的
	//将authentication转换为json
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ImoocSecurityProperties securityProperties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

		logger.info("com.ns.cjcq.security.authentication.ImoocAuthenticationSuccessHandler.onAuthenticationSuccess 登录成功");

		if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {//loginType 默认设置JSON
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
