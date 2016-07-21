package com.aebiz.b2b2c.cms.storetags.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 商户标签管理
 * 
 * @author cj
 * 
 */

@Entity
@Table(name = "storeTags")
public class StoreTagsModel extends BaseModel {
	/* 标签名称 */
	private String tagName;

	/* 标签类型 1会员类型 */
	private String tagType;

	/* 商户id */
	private String storeUuid;

	/* 商户名称 */
	private String storeName;

	public void setTagName(String obj) {
		this.tagName = obj;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagType(String obj) {
		this.tagType = obj;
	}

	public String getTagType() {
		return this.tagType;
	}

	public void setStoreUuid(String obj) {
		this.storeUuid = obj;
	}

	public String getStoreUuid() {
		return this.storeUuid;
	}

	public void setStoreName(String obj) {
		this.storeName = obj;
	}

	public String getStoreName() {
		return this.storeName;
	}
}
