package com.aebiz.b2b2c.cms.tags.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 标签实体类
 * 
 * @author huangpinpin
 * 
 */
@Entity
@Table(name = "tags")
public class TagsModel extends BaseModel {
	/* 标签名称 */
	private String tagName;

	/* 标签分类uuid */
	@Transient
	private String categoryId = "";

	/* 标签分类名称 */
	@Transient
	private String categoryName = "";

	public void setTagName(String obj) {
		this.tagName = obj;
	}

	public String getTagName() {
		return this.tagName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	@Override
	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[tagName=" + this.getTagName() + ",]";
	}
}
