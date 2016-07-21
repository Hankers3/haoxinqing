package com.aebiz.b2b2c.product.producttemplate.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.product.producttemplatecate.service.ProductTemplateCateService;

/**
 * 属性模板实体类
 * @author huangpinpin
 *
 */
@Entity
@Table(name="product_template")
@Component
public class ProductTemplateModel extends BaseModel{
	@Transient
	private static ProductTemplateCateService tempCateService;
	@Autowired
	public void setTempCateService(
			ProductTemplateCateService tempCateService) {
		this.tempCateService = tempCateService;
	}
	/* 模版名称	*/
	private String name = "";
	/* 模板分类 */
	private String categoryUuid="";
	/* 分类名称*/
	@Transient
	private String categoryName="";
	/* 是否启用 	0-未启用,1-已启用	*/
	private String canUse = "0";
	/* 模版描述	*/
	private String note = "";
	/* 创建时间	*/
	private String createTime = "";
	
	/* 是否已经关联分类:0-未关联,1-已关联*/
	@Transient
	private String isRel="";
	
	public void setName(String obj){
		this.name = obj;
	}
	public String getName(){
		return this.name;
	}
	
	public void setCanUse(String obj){
		this.canUse = obj;
	}
	public String getCanUse(){
		return this.canUse;
	}
	
	public void setNote(String obj){
		this.note = obj;
	}
	public String getNote(){
		return this.note;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setCategoryUuid(String categoryUuid) {
		this.categoryUuid = categoryUuid;
	}
	
	public String getCategoryUuid() {
		return categoryUuid;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return tempCateService.getCategoryNameByUuid(this.categoryUuid);
	}
	
	public String getIsRel() {
		return isRel;
	}
	
	public void setIsRel(String isRel) {
		this.isRel = isRel;
	}
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[name=" + this.getName() + ",canUse=" + this.getCanUse() + ",note=" + this.getNote() + ",createTime=" + this.getCreateTime() + ",]";
	}	
}
