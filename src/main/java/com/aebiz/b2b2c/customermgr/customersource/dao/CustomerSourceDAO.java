package com.aebiz.b2b2c.customermgr.customersource.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceModel;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceQueryModel;

public interface CustomerSourceDAO extends
		BaseDAO<CustomerSourceModel, CustomerSourceQueryModel> {
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
	 * 邀请码列表
	 * @return
	 */
	public List<String> getInviteCodes();
	/**
	 * 根据注册生成的邀请码 MyInviteCode字段查询CustomerSourceModel
	 * @param cashId 注册生成的邀请码 MyInviteCode
	 * @return CustomerSourceModel集合
	 */
	public List<CustomerSourceModel> getModelByMyInviteCodes(String cashId);
	
	public String getCustomerSourceIdByCustomerUuid(
                       String customerUuid);
	/**
	 * 获取customersource的uuid
	 * @param cashId
	 * @return
	 */
	public List<String> getAllCutomerSourceUuids(String cashId);
}