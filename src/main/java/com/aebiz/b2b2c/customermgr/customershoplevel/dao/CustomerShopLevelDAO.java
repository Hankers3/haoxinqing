package com.aebiz.b2b2c.customermgr.customershoplevel.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelQueryModel;

public interface CustomerShopLevelDAO extends
		BaseDAO<CustomerShopLevelModel, CustomerShopLevelQueryModel> {
	/**
	 * 得到会员平台等级名称的集合
	 * 
	 * @return
	 */
	public List<String> getCustomerShopLevelName();

	/**
	 * 得到平台会员初始等级编号
	 * 
	 * @return
	 */
	public String getInitLevelUuid();

	/**
	 * 通过编号获取等级名称
	 * 
	 * @param uuid
	 * @return
	 */
	public String getLevelNameByUuid(String uuid);

	/**
	 * 得到平台会员等级model的集合
	 * 
	 * @return
	 */
	public List<CustomerShopLevelModel> getShopLevelModelList();

	/**
	 * 根据等级名称校验平台会员等级名称是否存在
	 * 
	 * @param levelName
	 *            等级名称
	 * @param uuid
	 *            登记编号
	 * @return
	 */
	public boolean checkLevelNameExist(String levelName, String uuid);
	
	/**
	 * 通过积分来获取等级名称
	 * 
	 * @param intergralcount
	 * @return
	 */
	public String getLevelNameByIntergral(int intergralcount);
	
	
	/**
	 * 通过等级名称来获取等级uuid
	 * 
	 * @param levelName
	 * @return
	 */
	public String getUuidByLevelName(String levelName);
	
	public List<String> getShopLevelUuids();
}