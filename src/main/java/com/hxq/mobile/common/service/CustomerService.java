package com.hxq.mobile.common.service;

import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 患者注册信息
 */
public interface CustomerService extends SimpleEntityService{
	/**
	 * 通过手机获取患者信息
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public Customer selectCustomerByMobile(String mobile) throws Exception;

	/**
	 * 检查手机号和密码
	 * @param mobile
	 * @param password
	 * @return
	 */
	public Customer select4CheckMobileAndPassword(String mobile, String password);
	
	/**
	 * 通过患者id 查询 customerName
	 */
	public String selectCustomerName(String uuid);
}
