package com.aebiz.b2b2c.product.productbrand.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.product.productbrand.service.ProductBrandService;

/**
 * 商品品牌
 * 
 * @author huangpinpin
 * 
 */
@Entity
@Table(name = "product_brand")
@Component
public class ProductBrandModel extends BaseModel {
	@Transient
	private static ProductBrandService myService;
	@Autowired
	public void setMyService(ProductBrandService myService) {
		this.myService = myService;
	}
	
	/* 品牌名称*/
	private String brandName = "";
	/* 品牌英文名*/
	private String brandEnName="";
	
	/* 图片1-正方形*/
	private String image1 = "";
	@Transient
	private String imgUrl1="";
	
	/* 图片2-长方形*/
	private String image2 = "";
	@Transient
	private String imgUrl2="";
	/* 父品牌Id*/
	private String parentUuid = "";
	/* 介绍*/
	private String note = "";
	
	/* 是否选择 1是 0否 */
	@Transient
	private String chooseOrNo = "0";
	
	
	public void setBrandEnName(String brandEnName) {
		this.brandEnName = brandEnName;
	}
	public String getBrandEnName() {
		return brandEnName;
	}
	
	public void setChooseOrNo(String chooseOrNo) {
		this.chooseOrNo = chooseOrNo;
	}
	public String getChooseOrNo() {
		return chooseOrNo;
	}
	
	public void setBrandName(String obj){
		this.brandName = obj;
	}
	public String getBrandName(){
		return this.brandName;
	}
	
	public void setImage1(String obj){
		this.image1 = obj;
	}
	public String getImage1(){
		return this.image1;
	}
	
	public void setImage2(String obj){
		this.image2 = obj;
	}
	public String getImage2(){
		return this.image2;
	}
	
	public void setParentUuid(String obj){
		this.parentUuid = obj;
	}
	public String getParentUuid(){
		return this.parentUuid;
	}
	
	public void setNote(String obj){
		this.note = obj;
	}
	public String getNote(){
		return this.note;
	}
	public void setImgUrl1(String imgUrl1) {
		this.imgUrl1 = imgUrl1;
	}
	public String getImgUrl1() {
		return myService.getUrlByFileName(this.image1);
	}
	
	public void setImgUrl2(String imgUrl2) {
		this.imgUrl2 = imgUrl2;
	}
	
	public String getImgUrl2() {
		return myService.getUrlByFileName(this.image2);
	}
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[brandName=" + this.getBrandName() + ",image1=" + this.getImage1() + ",image2=" + this.getImage2() + ",parentUuid=" + this.getParentUuid() + ",note=" + this.getNote() + ",]";
	}	
}
