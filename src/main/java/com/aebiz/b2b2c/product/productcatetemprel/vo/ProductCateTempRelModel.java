package com.aebiz.b2b2c.product.productcatetemprel.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 分类模板关系实体类
 * @author huangpinpin
 *
 */
@Entity
@Table(name="product_cate_temp_rel")
public class ProductCateTempRelModel extends BaseModel{
	/* 分类uuid*/
	private String categoryUuid;
	/* 模板uuid*/
	private String templateUuid;
	/* 位置*/
	private int position;
	
	public void setCategoryUuid(String obj){
		this.categoryUuid = obj;
	}
	public String getCategoryUuid(){
		return this.categoryUuid;
	}
	
	public void setTemplateUuid(String obj){
		this.templateUuid = obj;
	}
	public String getTemplateUuid(){
		return this.templateUuid;
	}
	
	public void setPosition(int obj){
		this.position = obj;
	}
	public int getPosition(){
		return this.position;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[categoryUuid=" + this.getCategoryUuid() + ",templateUuid=" + this.getTemplateUuid() + ",position=" + this.getPosition() + ",]";
	}	
}
