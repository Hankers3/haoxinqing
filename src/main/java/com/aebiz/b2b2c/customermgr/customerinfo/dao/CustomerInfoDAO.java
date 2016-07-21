package com.aebiz.b2b2c.customermgr.customerinfo.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoQueryModel;

public interface CustomerInfoDAO extends
		BaseDAO<CustomerInfoModel, CustomerInfoQueryModel> {
	/**
	 * 根据会员编号获取会员基础信息
	 */
	public CustomerInfoModel getCustomerInfoModelByCustomerUuid(
			String customerUuid);

	/**
	 * 更新会员头像信息
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param image
	 *            头像名称
	 */
	public void updateImage(String customerUuid, String image);

	/**
	 * 通过客户的Uuid查询用户的真实姓名
	 * 
	 * @param uuid
	 * 
	 * @return
	 */
	public String getRealNameByUuid(String uuid);

	/**
	 * 通过客户的Uuid查询用户的性别
	 * 
	 * @param uuid
	 * 
	 * @return
	 */

	public String getSexByUuid(String uuid);

	/**
	 * 通过客户的Uuid查询用户的生日
	 * 
	 * @param uuid
	 * 
	 * @return
	 */

	public String getBirthdayByUuid(String uuid);

	/**
	 * 通过客户的Uuid查询用户的的年龄
	 * 
	 * @param uuid
	 * 
	 * @return
	 */

	public String getAgeByUuid(String customerUuid);

	/**
	 * 根据出生日期获得age的大小
	 * 
	 * @param customerUuid
	 * @param birthday
	 * @return
	 */
	public void updateAgeByBirthday(String customerUuid, String birthday);

}