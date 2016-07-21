package com.aebiz.b2b2c.product.sysback.web.product.vo;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;

/**
 * 页面传值
 * 
 * @author huangpinpin
 *
 */
public class ProductWebModel extends BaseWebModel{
	/* 商品上下架状态*/
	private String state;
	
	/* 商品审核状态*/
	private String auditState;
	
	/* 商品类型*/
	private String type;
	
	/* 商品分类uuid*/
	private String categoryUuid;
	
	/* 分类名称*/
	private String categoryName;
	
	/* 是否是规格属性*/
	private String canSpce="";
	
	private String attributeUuids;
	
	private int imageCount=0;
	
	public String getCanSpce() {
		return canSpce;
	}
	public void setCanSpce(String canSpce) {
		this.canSpce = canSpce;
	}
	
	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}
	public int getImageCount() {
		return imageCount;
	}
	
	public void setAttributeUuids(String attributeUuids) {
		this.attributeUuids = attributeUuids;
	}
	public String getAttributeUuids() {
		return attributeUuids;
	}
	
	private String productUuid;
	
	public String getProductUuid() {
		return productUuid;
	}
	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
	public void setCategoryUuid(String categoryUuid) {
		this.categoryUuid = categoryUuid;
	}
	
	public String getCategoryUuid() {
		return categoryUuid;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
	
	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	
	public String getAuditState() {
		return auditState;
	}
	
}
