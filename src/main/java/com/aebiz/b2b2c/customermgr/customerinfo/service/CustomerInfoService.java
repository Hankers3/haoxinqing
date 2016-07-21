package com.aebiz.b2b2c.customermgr.customerinfo.service;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthModel;
import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoQueryModel;

public interface CustomerInfoService extends
		BaseService<CustomerInfoModel, CustomerInfoQueryModel> {
	/**
	 * 根据会员编号获取会员基础信息
	 */
	public CustomerInfoModel getCustomerInfoModelByCustomerUuid(
			String customerUuid);

	/**
	 * 更新会员基础信息
	 * 
	 * @param customerModel
	 */
	public void updateCustomerInfo(CustomerCombModel cus,
			MultipartFile[] imgFiles);

	/**
	 * 更新会员实名认证信息的时候需要同时更新会员基础信息
	 * 
	 * @param cutomerAuthModel
	 */
	public void updateCustomerInfo(CustomerAuthModel customerAuthModel);

	/**
	 * 更新会员头像信息
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param image
	 *            头像名称
	 */
	public void updateImage(String customerUuid, String image);

	public String getRealNameByUuid(String uuid);

	public String getSexByUuid(String uuid);

	public String getBirthdayByUuid(String uuid);

	public String getAgeByUuid(String customerUuid);

	/**
	 * 根据出生日期获得age的大小
	 * 
	 * @param customerUuid
	 * @param birthday
	 * @return
	 */
	public void updateAgeByBirthday();

}
