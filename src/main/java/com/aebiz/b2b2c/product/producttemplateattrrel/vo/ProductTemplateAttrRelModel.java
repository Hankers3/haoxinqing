package com.aebiz.b2b2c.product.producttemplateattrrel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.product.producttemplateattr.service.ProductTemplateAttrService;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrModel;

/**
 * 模板属性关系表
 * 
 * @author huangpinpin
 */
@Entity
@Table(name="product_template_attr_rel")
public class ProductTemplateAttrRelModel extends BaseModel{
	
	/* 是-规格属性*/
	public static final String ATTRIBUTE_YES="1";
	/* 否-规格属性*/
	public static final String ATTRIBUTE_NO="0";
	
	/* 属性uuid*/
	private String attributeUuid;
	
	/* 属性名称*/
	@Transient
	private String attributeName;
	
	/* 模板uuid*/
	private String templateUuid;
	
	/* 模板名称*/
	@Transient
	private String templateName;
	
	/* 类型*/
	private String type;
	
	/* 是否规格属性*/
	private String canAttribute;
	
	/* 位置*/
	private int position=0;
	
 
	
	public void setAttributeUuid(String obj){
		this.attributeUuid = obj;
	}
	public String getAttributeUuid(){
		return this.attributeUuid;
	}
	
	public void setAttributeName(String obj){
		this.attributeName = obj;
	}
	public String getAttributeName(){
		return this.attributeName;
	}
	
	public void setTemplateUuid(String obj){
		this.templateUuid = obj;
	}
	public String getTemplateUuid(){
		return this.templateUuid;
	}
	
	public void setTemplateName(String obj){
		this.templateName = obj;
	}
	public String getTemplateName(){
		return this.templateName;
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCanAttribute() {
		return canAttribute;
	}
	public void setCanAttribute(String canAttribute) {
		this.canAttribute = canAttribute;
	}
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[attributeUuid=" + this.getAttributeUuid() + ",attributeName=" + this.getAttributeName() + ",templateUuid=" + this.getTemplateUuid() + ",templateName=" + this.getTemplateName() + ",position=" + this.getPosition() + ",]";
	}	
}
