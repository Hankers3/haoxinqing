package com.aebiz.b2b2c.product.frontproductcategorybrandrel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "front_product_category_brand_rel")
public class FrontProductCategoryBrandRelModel extends BaseModel {

	/* 商品分类uuid */
	private String categoryUuid;

	/* 品牌uuid */
	private String brandUuid;

	/* 分类类型 */
	private String categoryType;

	public void setCategoryUuid(String obj) {
		this.categoryUuid = obj;
	}

	public String getCategoryUuid() {
		return this.categoryUuid;
	}

	public void setBrandUuid(String obj) {
		this.brandUuid = obj;
	}

	public String getBrandUuid() {
		return this.brandUuid;
	}

	public void setCategoryType(String obj) {
		this.categoryType = obj;
	}

	public String getCategoryType() {
		return this.categoryType;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[categoryUuid=" + this.getCategoryUuid() + ",brandUuid="
				+ this.getBrandUuid() + ",categoryType="
				+ this.getCategoryType() + ",]";
	}
}
