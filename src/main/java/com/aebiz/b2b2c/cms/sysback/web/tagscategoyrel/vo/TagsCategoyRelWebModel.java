package com.aebiz.b2b2c.cms.sysback.web.tagscategoyrel.vo;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;

public class TagsCategoyRelWebModel extends BaseWebModel {
	// 分类id
	private String categoryUuid;

	public String getCategoryUuid() {
		return categoryUuid;
	}

	public void setCategoryUuid(String categoryUuid) {
		this.categoryUuid = categoryUuid;
	}

}