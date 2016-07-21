package com.aebiz.b2b2c.product.productmainaudit.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 商品审核实体类
 * 
 * @author huangpinpin
 *
 */
@Entity
@Table(name="product_main_audit")
public class ProductMainAuditModel extends BaseModel{
	public static final String PASS = "1";//通过
	public static final String NO_PASS = "2";//未通过
	
	/* 商品uuid*/
	private String productUuid;
	
	/* 审核类型：基础信息、价格信息、图片信息、描述信息*/
	private String auditType;
	
	/* 审核字段*/
	private String auditField;
	
	/* 审核人*/
	private String auditMan;
	
	/* 审核时间*/
	private String auditTime;
	
	/* 审核状态 1-通过,2-不通过*/
	private String auditState;
	
	/* 审核原因*/
	private String auditReason;
	
	/* 审核级别*/
	private String auditLevel;
	
	
	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}
	public String getProductUuid() {
		return productUuid;
	}
	
	public void setAuditType(String obj){
		this.auditType = obj;
	}
	public String getAuditType(){
		return this.auditType;
	}
	
	public void setAuditField(String obj){
		this.auditField = obj;
	}
	public String getAuditField(){
		return this.auditField;
	}
	
	public void setAuditMan(String obj){
		this.auditMan = obj;
	}
	public String getAuditMan(){
		return this.auditMan;
	}
	
	public void setAuditTime(String obj){
		this.auditTime = obj;
	}
	public String getAuditTime(){
		return this.auditTime;
	}
	
	public void setAuditState(String obj){
		this.auditState = obj;
	}
	public String getAuditState(){
		return this.auditState;
	}
	
	public void setAuditReason(String obj){
		this.auditReason = obj;
	}
	public String getAuditReason(){
		return this.auditReason;
	}
	
	public void setAuditLevel(String obj){
		this.auditLevel = obj;
	}
	public String getAuditLevel(){
		return this.auditLevel;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName() + "[auditType=" + this.getAuditType() + ",auditField=" + this.getAuditField() + ",auditMan=" + this.getAuditMan() + ",auditTime=" + this.getAuditTime() + ",auditState=" + this.getAuditState() + ",auditReason=" + this.getAuditReason() + ",auditLevel=" + this.getAuditLevel() + ",]";
	}	
}
