package com.aebiz.b2b2c.cms.usercenter.web.productappraise.vo;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;

/**
 * 
 * 订单评价页面、商户信息等
 * 
 * @author cj
 * 
 */

public class OrderAppraiseWebModel extends BaseWebModel {
	/* 商户名称 */
	private String storeName = "";

	/* 店铺所在地 */
	private String storeAddress = "";

	/* 店铺LOGO */
	private String storeLogo = "";

	/* 店铺联系人手机 */
	private String storeMobile = "";

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStoreLogo() {
		return storeLogo;
	}

	public void setStoreLogo(String storeLogo) {
		this.storeLogo = storeLogo;
	}

	public String getStoreMobile() {
		return storeMobile;
	}

	public void setStoreMobile(String storeMobile) {
		this.storeMobile = storeMobile;
	}

}
