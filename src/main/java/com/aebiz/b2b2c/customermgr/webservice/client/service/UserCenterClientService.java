package com.aebiz.b2b2c.customermgr.webservice.client.service;

public interface UserCenterClientService {

	/**
	 * 获取用户信息
	 * 
	 * @param sign
	 * @param customerUuid
	 * @return
	 */
	public String getUserInfo(String sign, String loginName, String pwd);

	/**
	 * 获取管理员信息
	 * 
	 * @param sign
	 * @param customerUuid
	 * @return
	 */
	public String getManagerInfo(String sign, String loginName, String pwd);

}
