package com.aebiz.b2b2c.cms.tagscategoy.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 标签分类实体类
 * 
 * @author huangpinpin
 * 
 */
@Entity
@Table(name = "tags_categoy")
public class TagsCategoryModel extends BaseModel {
	/* 标签名称 */
	private String categoryName;

	/* 标签类型 */
	private String categoryType;

	/* 标签备注 */
	private String note;

	@Transient
	private String tagType = "";

	public void setCategoryName(String obj) {
		this.categoryName = obj;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryType(String obj) {
		this.categoryType = obj;
	}

	public String getCategoryType() {
		return this.categoryType;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public String getTagType() {
		return TagsType.getNameByKey(this.categoryType);
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	@Override
	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[categoryName=" + this.getCategoryName() + ",categoryType="
				+ this.getCategoryType() + ",note=" + this.getNote() + ",]";
	}
}
