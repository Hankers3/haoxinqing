package com.aebiz.b2b2c.finance.invoice.vo;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "invoice")
public class InvoiceModel extends BaseModel {
	
	/* 订单编号 */
	private String orderMainUuid;

	/* 发票编号 */
	private String invoiceNO;

	/* 发票金额 */
	private double invoiceAmount;

	/* 发票种类 */
	private String invoiceCate;

	/* 发票类型 */
	private String invoiceType;

	/* 发票内容 */
	private String invoiceContent;

	/* 发票抬头 */
	private String invoiceTitle;

	/* 创建时间 */
	private String createTime;

	/* 店铺编号 */
	private String storeUuid;
	
	/*交易类型*/
	private String saleType;
	
	/*发票状态*/
	private String invoiceState;
	
	/*开票时间*/
	private String invoiceTime;
	
	/*发票内容数组类型*/
	@Transient
	private String[] invoiceContents;

	public String[] getInvoiceContents() {
		return this.invoiceContents;
	}

	public void setInvoiceContents(String[] invoiceContents) {
		this.invoiceContents = invoiceContents;
	}

	public void setOrderMainUuid(String obj) {
		this.orderMainUuid = obj;
	}

	public String getOrderMainUuid() {
		return this.orderMainUuid;
	}

	public void setInvoiceNO(String obj) {
		this.invoiceNO = obj;
	}

	public String getInvoiceNO() {
		return this.invoiceNO;
	}

	public void setInvoiceAmount(double obj) {
		this.invoiceAmount = obj;
	}

	public double getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceCate(String obj) {
		this.invoiceCate = obj;
	}

	public String getInvoiceCate() {
		return this.invoiceCate;
	}

	public void setInvoiceType(String obj) {
		this.invoiceType = obj;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceContent(String obj) {	
		this.invoiceContent=obj;	
	}

	public String getInvoiceContent() {
		return this.invoiceContent;
	}

	public void setInvoiceTitle(String obj) {
		this.invoiceTitle = obj;
	}

	public String getInvoiceTitle() {
		return this.invoiceTitle;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setStoreUuid(String obj) {
		this.storeUuid = obj;
	}

	public String getStoreUuid() {
		return this.storeUuid;
	}
	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
	public String getInvoiceState() {
		return invoiceState;
	}
	
	public String getInvoiceTime() {
		return invoiceTime;
	}

	public void setInvoiceTime(String invoiceTime) {
		this.invoiceTime = invoiceTime;
	}

	public void setInvoiceState(String invoiceState) {
		this.invoiceState = invoiceState;
	}

	@Override
	public String toString() {
		return "InvoiceModel [orderMainUuid=" + orderMainUuid + ", invoiceNO="
				+ invoiceNO + ", invoiceAmount=" + invoiceAmount
				+ ", invoiceCate=" + invoiceCate + ", invoiceType="
				+ invoiceType + ", invoiceContent=" + invoiceContent
				+ ", invoiceTitle=" + invoiceTitle + ", createTime="
				+ createTime + ", storeUuid=" + storeUuid + ", saleType="
				+ saleType + ", invoiceState=" + invoiceState
				+ ", invoiceTime=" + invoiceTime + ", invoiceContents="
				+ Arrays.toString(invoiceContents) + "]";
	}	
}
