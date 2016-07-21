package com.aebiz.b2b2c.customermgr.customershoplevel.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelQueryModel;

public interface CustomerShopLevelService extends
		BaseService<CustomerShopLevelModel, CustomerShopLevelQueryModel> {

	/**
	 * 得到平台会员等级model的集合
	 * 
	 * @return
	 */
	public List<CustomerShopLevelModel> getShopLevelModelList();

	/**
	 * 得到平台会员初始等级编号
	 * 
	 * @return
	 */
	public String getInitLevelUuid();

	/**
	 * 通过编号获取等级名称
	 * 
	 * @return
	 */
	public String getLevelNameByUuid(String uuid);

	/**
	 * 得到会员平台等级名称的集合
	 * 
	 * @return
	 */
	public List<String> getCustomerShopLevelName();

	/**
	 * 根据等级名称校验平台会员等级名称是否存在
	 * 
	 * @param levelName
	 *            等级名称
	 * @param uuid
	 *            等级编号
	 * @return
	 */

	public boolean checkLevelNameExist(String levelName, String uuid);

	/**
	 * 文件上传
	 * 
	 * @param customerShopLevelModel
	 * @param files
	 * @return
	 */
	public CustomerShopLevelModel uploadImage(
			CustomerShopLevelModel customerShopLevelModel, MultipartFile[] files);
	
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
	
}

