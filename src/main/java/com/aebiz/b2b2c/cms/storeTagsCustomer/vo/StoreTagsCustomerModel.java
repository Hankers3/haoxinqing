package com.aebiz.b2b2c.cms.storeTagsCustomer.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 商户、标签、会员的关联关系
 * 
 * @author cj
 * 
 */

@Entity
@Table(name = "store_tags_customer_rel")
public class StoreTagsCustomerModel extends BaseModel {
	/* 标签ID */
	private String tagUuid;

	/* 商户ID */
	private String storeUuid;

	/* 会员ID */
	private String customerUuid;

	public String getTagUuid() {
		return tagUuid;
	}

	public void setTagUuid(String tagUuid) {
		this.tagUuid = tagUuid;
	}

	public String getStoreUuid() {
		return storeUuid;
	}

	public void setStoreUuid(String storeUuid) {
		this.storeUuid = storeUuid;
	}

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

}
