package com.aebiz.b2b2c.product.producttemplateattr.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 属性实体类
 * @author huangpinpin
 */
@Entity
@Table(name="product_template_attr")
public class ProductTemplateAttrModel extends BaseModel{
	
	/* 属性名称*/
	private String attributeName;

	/* 属性英文名*/
	private String engName;
	
	public void setAttributeName(String obj){
		this.attributeName = obj;
	}
	public String getAttributeName(){
		return this.attributeName;
	}
	
	public void setEngName(String obj){
		this.engName = obj;
	}
	public String getEngName(){
		return this.engName;
	}
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[attributeName=" + this.getAttributeName() + ",engName=" + this.getEngName() +  ",]";
	}	
}
