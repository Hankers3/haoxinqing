package com.aebiz.b2b2c.customermgr.customersource.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceModel;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceQueryModel;

public interface CustomerSourceService extends
		BaseService<CustomerSourceModel, CustomerSourceQueryModel> {
	/**
	 * 通过会员编号获取会员来源信息
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	public CustomerSourceModel getCustomerSourceModelByCustomerUuid(
			String customerUuid);
	/**
	 * 根据注册生成的邀请码 MyInviteCode字段查询CustomerSourceModel
	 * @param cashId 注册生成的邀请码 MyInviteCode
	 * @return CustomerSourceModel集合
	 */
	public CustomerSourceModel getModelByMyInviteCodes(String cashId);
	/**
	 * 邀请码是否存在
	 * 存在返回1、不存在返回0
	 * @param inviteCode
	 * @return
	 */
	public String inviteCodeIsExist(String inviteCode);
	/**
	 * 判断用户注册时生成的邀请码是否存在
	 * @param cashId 邀请码
	 * @return true为存在；false为不存在
	 */
	public boolean MyInviteCodeIsExist(String cashId);
}
