package com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "gift_package_customer_rel")
public class GiftPackageCustomerRelModel extends BaseModel {
	/* 礼包uuid */
	private String giftPackageUuid;

	/* 会员uuid */
	private String customerUuid;

	public void setGiftPackageUuid(String obj) {
		this.giftPackageUuid = obj;
	}

	public String getGiftPackageUuid() {
		return this.giftPackageUuid;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[giftPackageUuid=" + this.getGiftPackageUuid()
				+ ",customerUuid=" + this.getCustomerUuid() + ",]";
	}
}
