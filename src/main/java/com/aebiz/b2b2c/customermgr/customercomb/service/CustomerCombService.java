package com.aebiz.b2b2c.customermgr.customercomb.service;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombModel;
import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombQueryModel;

public interface CustomerCombService extends
		BaseService<CustomerCombModel, CustomerCombQueryModel> {
	/**
	 * 添加会员
	 * 
	 * @param m
	 * @param files
	 * @return
	 */
	public void createCustomer(CustomerCombModel m);

	/**
	 * 通过会员编号获取复合model，会员基础信息和账户信息
	 * 
	 * @param uuid
	 */
	public CustomerCombModel getCustomerCombModelByCustomerUuid(String uuid);

	/**
	 * 根据会员编号获取会员账户信息，基础信息，实名认证信息，来源信息
	 * 
	 * @param uuid
	 * @return
	 */
	public CustomerCombModel getAllCustomerCombModelByCustomerUuid(String uuid);

	/**
	 * 更新会员账户信息
	 * 
	 * @param customerModel
	 */
	public void updateCustomerAccountInfo(CustomerModel customerModel);

	/**
	 * 更新会员基础信息
	 * 
	 * @param customerInfoModel
	 */
	public void updateCustomerBaseInfo(CustomerCombModel customerCombModel,
			MultipartFile[] imgFiles);

	/**
	 * 会员更新页面添加会员冻结或者解冻日志
	 * 
	 * @param customerCombModel
	 */
	public void updateCustomerFrozenLog(CustomerCombModel customerCombModel);
	

}
