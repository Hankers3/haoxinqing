package com.hxq.mobile.patient;

import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.wxcommon.util.ObjectUtils;

/**
 * 与业务相关的公用方法
 *
 */
public class CommonLogic {

	/**
	 * 判断用户信息是否完整
	 * @param customer 用户注册信息
	 * @param info 用户基本信息
	 * @return 完整返回真，否则返回假
	 */
	public static boolean isComplated(Customer customer, CustomerInfo info) {
		if(customer == null || info == null) return false;
		if(ObjectUtils.isEmpty(customer.getCustomerName())
			|| ObjectUtils.isEmpty(info.getRealName())
			|| ObjectUtils.isEmpty(info.getSex())
			|| ObjectUtils.isEmpty(info.getBirthday())
			|| ObjectUtils.isEmpty(info.getIllnessDescription())
			|| ObjectUtils.isEmpty(info.getDiseaseTime())
			|| ObjectUtils.isEmpty(info.getFirstDiagnosis())
			|| ObjectUtils.isEmpty(info.getIfStart())
			|| ObjectUtils.isEmpty(info.getSeizureTimes())
			|| ObjectUtils.isEmpty(info.getHeight())
			|| ObjectUtils.isEmpty(info.getWeight())) {
			return false;
		}
		return true;
	}
	//适用于旧接口（个人信息的查询，个人信息的保存）判断信息是否填写完全
	public static boolean isComplated2(CustomerModel customer, CustomerInfoModel info) {
		if(customer == null || info == null) return false;
		if(ObjectUtils.isEmpty(customer.getCustomerName())
				|| ObjectUtils.isEmpty(info.getSex())
				|| ObjectUtils.isEmpty(info.getAge())) {
			return false;
		}
		return true;
	}
	//新需求，（登录时判断信息是否填写完成，申请医生随访）
	public static boolean isComplated3(Customer customer, CustomerInfo info) {
		if(customer == null || info == null) return false;
		if(ObjectUtils.isEmpty(customer.getCustomerName())
				|| ObjectUtils.isEmpty(info.getSex())
				|| ObjectUtils.isEmpty(info.getBirthday())
				) {
			return false;
		}
		return true;
	}
}
