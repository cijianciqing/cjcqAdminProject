/**
 * 
 */
package com.ns.cjcq.security.common.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhailiang
 *
 */
@Setter
@Getter
public class BrowserProperties {
	
	private SessionProperties session = new SessionProperties();

	//首页
	private String index = "/";

	//注册页面
	private String signUpUrl = "/imooc-imooc-login.html";

	//登录成功后退出页面
	private String signOutUrl="/logout";

	//登录成功后退出页面
	private String signOutTo="/";
	
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	
	private LoginResponseType loginType = LoginResponseType.JSON;
	
	private int rememberMeSeconds = 3600;


	
}
