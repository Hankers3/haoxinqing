package com.aebiz.b2b2c.finance.storeinvoiceset.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="store_Invoice_set")
public class StoreInvoiceSetModel extends BaseModel{
	//商户编号
	private String accountUuid;
	//发票种类
	private String invoiceCate;
	//发票类型
	private String invoiceType;
	//发票内容
	private String invoiceContent;
	
	public String getAccountUuid() {
		return accountUuid;
	}
	public void setAccountUuid(String accountUuid) {
		this.accountUuid = accountUuid;
	}
	public void setInvoiceCate(String obj){
		this.invoiceCate = obj;
	}
	public String getInvoiceCate(){
		return this.invoiceCate;
	}
	
	public void setInvoiceType(String obj){
		this.invoiceType = obj;
	}
	public String getInvoiceType(){
		return this.invoiceType;
	}
	
	public void setInvoiceContent(String obj){
		this.invoiceContent = obj;
	}
	public String getInvoiceContent(){
		return this.invoiceContent;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[accountUuid=" + this.getAccountUuid() + ",invoiceCate=" + this.getInvoiceCate() + ",invoiceType=" + this.getInvoiceType() + ",invoiceContent=" + this.getInvoiceContent() + ",]";
	}	
}
