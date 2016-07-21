package com.aebiz.b2b2c.userfront.platimagecategory.vo;

import javax.persistence.Entity;

import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 平台的图片库分类,用于添加平台的图片,广告可以去选取图片库中的图片
 *
 * @author tangyunkai
 *
 * @date 2015年1月5日 下午2:08:14
 *
 */
@Entity
@Table(name = "plat_image_category")
public class PlatImageCategoryModel extends BaseModel {

	/* 分类名称 */
	private String categoryName = "";

	/* 位置 */
	private int position;

	/* 上级编号 */
	private String parentUuid = "";

	public void setCategoryName(String obj) {
		this.categoryName = obj;
	}
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setPosition(int obj) {
		this.position = obj;
	}
	public int getPosition() {
		return this.position;
	}

	public void setParentUuid(String obj) {
		this.parentUuid = obj;
	}
	public String getParentUuid() {
		return this.parentUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[categoryName=" + this.getCategoryName() + ",position="
				+ this.getPosition() + ",parentUuid=" + this.getParentUuid()
				+ ",]";
	}
}
