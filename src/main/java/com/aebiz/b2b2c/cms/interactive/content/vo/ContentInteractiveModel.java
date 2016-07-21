package com.aebiz.b2b2c.cms.interactive.content.vo;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;


public class ContentInteractiveModel extends BaseModel {
	
	/* 内容名称 */
	private String contentTitle = "";

	/* 内容分类uuid */
	private String contentCategoryUuid = "";

	/* 介绍 */
	private String introduction = "";

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getContentCategoryUuid() {
		return contentCategoryUuid;
	}

	public void setContentCategoryUuid(String contentCategoryUuid) {
		this.contentCategoryUuid = contentCategoryUuid;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
}
