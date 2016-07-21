package com.aebiz.b2b2c.customermgr.usercenter.web.customer;

/**
 * 获取会员登录时最大错误次数,如果次数超过这个数值,则需要提示会员20分钟在登陆或者找回密码
 *
 * @author tangyunkai
 *
 * @date 2014年12月12日 下午3:36:47 
 *
 */
public class LoginMaxErrorTimeConst {
	
	/**
	 * 获取会员登录时最大错误次数,值有5,10,15,20,默认是10次
	 * 
	 */
	public static final String CUSTOMER_MAX_LOGIN_ERROR = "cutomer.max.login.error";

}
