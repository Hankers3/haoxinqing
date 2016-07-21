package com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.vo.GiftPackageCustomerRelModel;
import com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.vo.GiftPackageCustomerRelQueryModel;

public interface GiftPackageCustomerRelService
		extends
		BaseService<GiftPackageCustomerRelModel, GiftPackageCustomerRelQueryModel> {

	/**
	 * 给会员发送礼包
	 * 
	 * @param customerUuid
	 * @param giftPackageUuidList
	 */
	public void saveCustomerGiftPackageRel(String customerUuid,
			List<String> giftPackageUuidList);

}
