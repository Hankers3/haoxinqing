package com.aebiz.b2b2c.cms.tagscategoyrel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 标签与分类关系实体类
 * 
 * @author huangpinpin
 * 
 */
@Entity
@Table(name = "tags_categoy_rel")
public class TagsCategoyRelModel extends BaseModel {
	/* 标签id */
	private String tagUuid;

	/* 标签名称 */
	@Transient
	private String tagName;

	/* 标签分类id */
	private String categoryUuid;

	/* 标签分类名称 */
	@Transient
	private String categoryName;

	/* 是否推荐 */
	private String introduce;

	/* 标签位置 */
	private String position;

	public void setTagUuid(String obj) {
		this.tagUuid = obj;
	}

	public String getTagUuid() {
		return this.tagUuid;
	}

	public void setTagName(String obj) {
		this.tagName = obj;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setCategoryUuid(String obj) {
		this.categoryUuid = obj;
	}

	public String getCategoryUuid() {
		return this.categoryUuid;
	}

	public void setCategoryName(String obj) {
		this.categoryName = obj;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setIntroduce(String obj) {
		this.introduce = obj;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setPosition(String obj) {
		this.position = obj;
	}

	public String getPosition() {
		return this.position;
	}

	@Override
	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[tagUuid=" + this.getTagUuid() + ",tagsName="
				+ this.getTagName() + ",categoryUuid=" + this.getCategoryUuid()
				+ ",categoryName=" + this.getCategoryName() + ",introduce="
				+ this.getIntroduce() + ",position=" + this.getPosition()
				+ ",]";
	}
}
