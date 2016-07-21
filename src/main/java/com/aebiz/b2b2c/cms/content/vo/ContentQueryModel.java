package com.aebiz.b2b2c.cms.content.vo;

public class ContentQueryModel extends ContentModel {
	private String createTime2;
	
	private String categoryTypeq;
	
	/*app 搜索疾病编号*/
	private String appIllnessId;
	
	/*app 症状编号*/
	private String appSymptomId;
	
	/*app 内容名*/
	private String appContentName;
	
	/*app 病种*/
	private String appEntity;
	
	/*app 国家*/
	private String appCountry;
	
	/*app 作者*/
	private String appAuthor;
	
	
	public String getAppAuthor() {
		return appAuthor;
	}

	public void setAppAuthor(String appAuthor) {
		this.appAuthor = appAuthor;
	}

	public String getAppIllnessId() {
		return appIllnessId;
	}

	public void setAppIllnessId(String appIllnessId) {
		this.appIllnessId = appIllnessId;
	}

	public String getAppSymptomId() {
		return appSymptomId;
	}

	public void setAppSymptomId(String appSymptomId) {
		this.appSymptomId = appSymptomId;
	}

	public String getAppContentName() {
		return appContentName;
	}

	public void setAppContentName(String appContentName) {
		this.appContentName = appContentName;
	}

	public String getAppEntity() {
		return appEntity;
	}

	public void setAppEntity(String appEntity) {
		this.appEntity = appEntity;
	}

	public String getAppCountry() {
		return appCountry;
	}

	public void setAppCountry(String appCountry) {
		this.appCountry = appCountry;
	}

	public String getCategoryTypeq() {
		return categoryTypeq;
	}

	public void setCategoryTypeq(String categoryTypeq) {
		this.categoryTypeq = categoryTypeq;
	}

	public void setCreateTime2(String obj) {
		this.createTime2 = obj;
	}

	public String getCreateTime2() {
		return this.createTime2;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[createTime2=" + this.getCreateTime2() + ",]";
	}
}
