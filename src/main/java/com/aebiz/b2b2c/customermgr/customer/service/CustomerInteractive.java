package com.aebiz.b2b2c.customermgr.customer.service;

import java.util.List;

import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;

/**
 * 会员系统对外接口
 * 
 * @author likun
 * 
 */
public interface CustomerInteractive {

	/**
	 * 通过会员编号或者手机号或者用户名获取会员信息
	 * 
	 * @param param
	 *            用户名或者手机号或者会员编号
	 * @return
	 */
	public CustomerModel getCustomerModelByCondition(String param);

	/**
	 * 通过会员编号或者会员名称获取会员集合（编号和名称都是模糊查询）
	 * 
	 * @param qm
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	public List<CustomerModel> getCustomerModelListByCondition(
			CustomerQueryModel qm, int start, int pageShow);

	/**
	 * 通过会员会员名称模糊查询会员数量
	 * 
	 * @param qm
	 * @return
	 */
	public int getCountByCondition(CustomerQueryModel qm);

	/**
	 * 通过会员名编号来获取用户电话号码
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	public String getCustomerMobileByUuid(String customerUuid);

	/**
	 * 通过会员编号获取会员头像url地址
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	public String getCustomerInfoRemoteImageUrlByCustomerUuid(
			String customerUuid);

	/**
	 * 通过用户名或者手机号或者邮箱查询该会员信息
	 * 
	 * @param param
	 * @return
	 */
	public CustomerModel getCustomerModelByLoginNameOrMobileOrEmail(String param);

	
}
