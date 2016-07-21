package com.aebiz.b2b2c.customermgr.sysback.web.customersubscriptionlog.vo;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;

public class CustomerSubscriptionLogWebModel extends BaseWebModel {
	private String customerUuid;

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
}