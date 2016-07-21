package com.hxq.mobile.common.service;

import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
* 患者基本(补充)信息 
* @author  作者 E-mail: liuyang
* @date 创建时间：2016年5月17日 下午7:54:43 
* @version 2.0 
* @parameter  
* @since  
* @return  
*/
public interface CustomerInfoService extends SimpleEntityService{
	/**
	 * 获取患者基本信息数据
	 * @param doctorUuid
	 * @return
	 */
	public CustomerInfo selectByCustomerUuid(String customerUuid);
}
