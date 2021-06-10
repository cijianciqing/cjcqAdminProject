/**
 * 
 */
package com.ns.cjcq.security.controller;

import cn.com.ns.cj.cjuniversalspringbootstarter.returnData.CJAjaxResult;
import com.ns.cjcq.security.common.properties.ImoocSecurityProperties;

import com.ns.cjcq.security.common.properties.LoginResponseType;
import com.ns.cjcq.security.common.properties.SecurityConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author zhailiang
 *
 *判断客户端是浏览器还是手机APP
 */
@RestController
public class BrowserSecurityController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private RequestCache requestCache = new HttpSessionRequestCache();

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private ImoocSecurityProperties securityProperties;

//	@Autowired
//	private ProviderSignInUtils providerSignInUtils;

	/**
	 * 当需要身份认证时，跳转到这里
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
	//设置返回的状态码401
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public CJAjaxResult requireAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			logger.info("引发跳转的请求是:" + targetUrl);
			//如果请求是.html则返回到自定义的html
			if (LoginResponseType.REDIRECT.equals(securityProperties.getBrowser().getLoginType())){
//			if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
				redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
			}
		}
		//否则，则返回特定的值
		return CJAjaxResult.success("访问的服务需要身份认证，请引导用户到登录页");
	}


//	session失效页面？？？？
/*	@GetMapping("/session/invalid")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public CJAjaxResult sessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (LoginResponseType.REDIRECT.equals(securityProperties.getBrowser().getLoginType())){
			redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getSession().getSessionInvalidUrl());
		}
		String message = "cj session失效";
		return CJAjaxResult.success(message);
	}*/

}
