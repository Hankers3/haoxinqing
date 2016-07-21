package com.aebiz.b2b2c.vipclub.giftpackagecouponrel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 礼包关联优惠券
 * 
 * @author cj
 * 
 */
@Entity
@Table(name = "gift_package_coupon_rel")
public class GiftPackageCouponRelModel extends BaseModel {
	/* 会员礼包uuid */
	private String giftPackageUuid;

	/* 优惠券uuid */
	private String couponUuid;

	public void setGiftPackageUuid(String obj) {
		this.giftPackageUuid = obj;
	}

	public String getGiftPackageUuid() {
		return this.giftPackageUuid;
	}

	public void setCouponUuid(String obj) {
		this.couponUuid = obj;
	}

	public String getCouponUuid() {
		return this.couponUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[giftPackageUuid=" + this.getGiftPackageUuid()
				+ ",couponUuid=" + this.getCouponUuid() + ",]";
	}
}
