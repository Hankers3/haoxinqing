package com.aebiz.b2b2c.product.frontproductcatetemprel.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="front_product_cate_temp_rel")
public class FrontProductCateTempRelModel extends BaseModel{
	private String categoryUuid;
	private String attrJson;
	
	public void setCategoryUuid(String obj){
		this.categoryUuid = obj;
	}
	public String getCategoryUuid(){
		return this.categoryUuid;
	}
	
	public void setAttrJson(String obj){
		this.attrJson = obj;
	}
	public String getAttrJson(){
		return this.attrJson;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[categoryUuid=" + this.getCategoryUuid() + ",attrJson=" + this.getAttrJson() + ",]";
	}	
}
