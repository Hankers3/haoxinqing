package com.aebiz.b2b2c.product.producttemplatecate.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 
 * 模板分类实体类
 * 
 * @author huangpinpin
 *
 */
@Entity
@Table(name="product_template_cate")
public class ProductTemplateCateModel extends BaseModel{
	/* 模板分类名称*/
	private String categoryName;
	
	public void setCategoryName(String obj){
		this.categoryName = obj;
	}
	public String getCategoryName(){
		return this.categoryName;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[categoryName=" + this.getCategoryName() + ",]";
	}	
}
