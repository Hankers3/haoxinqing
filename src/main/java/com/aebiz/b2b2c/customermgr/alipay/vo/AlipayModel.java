package com.aebiz.b2b2c.customermgr.alipay.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="alipay")
public class AlipayModel extends BaseModel{
	
	/* 当前系统时间 */
	private String time;
	/* 支付接口 */
	private String paygateway;
	/* 服务类型 */
	private String service;
	/* 签名方式 */
	private String signType;
	/* 商户网站订单 */
	private String outTradeNo;
	/* 编码 */
	private String inputCharset;
	/* 合作伙伴id */
	private String partner;
	/* 支付宝安全校验码 */
	private String key;
	/* 卖家支付宝帐户 */
	private String sellerEmail;
	/* 商品描述 */
	private String body;
	/* 商品名称 */
	private String subject;
	/* 订单总价 */
	private String price;
	/* quantity */
	private String quantity;
	/* show_url */
	private String showUrl;
	/* payment_type */
	private String paymentType;
	/* discount */
	private String discount;
	/* logistics_type */
	private String logisticsType;
	/* logistics_fee */
	private String logisticsFee;
	/* logistics_payment */
	private String logisticsPayment;
	/* notify_url */
	private String notifyUrl;
	/* return_url */
	private String returnUrl;
	
	public void setTime(String obj){
		this.time = obj;
	}
	public String getTime(){
		return this.time;
	}
	
	public void setPaygateway(String obj){
		this.paygateway = obj;
	}
	public String getPaygateway(){
		return this.paygateway;
	}
	
	public void setService(String obj){
		this.service = obj;
	}
	public String getService(){
		return this.service;
	}
	
	public void setSignType(String obj){
		this.signType = obj;
	}
	public String getSignType(){
		return this.signType;
	}
	
	public void setOutTradeNo(String obj){
		this.outTradeNo = obj;
	}
	public String getOutTradeNo(){
		return this.outTradeNo;
	}
	
	public void setInputCharset(String obj){
		this.inputCharset = obj;
	}
	public String getInputCharset(){
		return this.inputCharset;
	}
	
	public void setPartner(String obj){
		this.partner = obj;
	}
	public String getPartner(){
		return this.partner;
	}
	
	public void setKey(String obj){
		this.key = obj;
	}
	public String getKey(){
		return this.key;
	}
	
	public void setSellerEmail(String obj){
		this.sellerEmail = obj;
	}
	public String getSellerEmail(){
		return this.sellerEmail;
	}
	
	public void setBody(String obj){
		this.body = obj;
	}
	public String getBody(){
		return this.body;
	}
	
	public void setSubject(String obj){
		this.subject = obj;
	}
	public String getSubject(){
		return this.subject;
	}
	
	public void setPrice(String obj){
		this.price = obj;
	}
	public String getPrice(){
		return this.price;
	}
	
	public void setQuantity(String obj){
		this.quantity = obj;
	}
	public String getQuantity(){
		return this.quantity;
	}
	
	public void setShowUrl(String obj){
		this.showUrl = obj;
	}
	public String getShowUrl(){
		return this.showUrl;
	}
	
	public void setPaymentType(String obj){
		this.paymentType = obj;
	}
	public String getPaymentType(){
		return this.paymentType;
	}
	
	public void setDiscount(String obj){
		this.discount = obj;
	}
	public String getDiscount(){
		return this.discount;
	}
	
	public void setLogisticsType(String obj){
		this.logisticsType = obj;
	}
	public String getLogisticsType(){
		return this.logisticsType;
	}
	
	public void setLogisticsFee(String obj){
		this.logisticsFee = obj;
	}
	public String getLogisticsFee(){
		return this.logisticsFee;
	}
	
	public void setLogisticsPayment(String obj){
		this.logisticsPayment = obj;
	}
	public String getLogisticsPayment(){
		return this.logisticsPayment;
	}
	
	public void setNotifyUrl(String obj){
		this.notifyUrl = obj;
	}
	public String getNotifyUrl(){
		return this.notifyUrl;
	}
	
	public void setReturnUrl(String obj){
		this.returnUrl = obj;
	}
	public String getReturnUrl(){
		return this.returnUrl;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[time=" + this.getTime() + ",paygateway=" + this.getPaygateway() + ",service=" + this.getService() + ",signType=" + this.getSignType() + ",outTradeNo=" + this.getOutTradeNo() + ",inputCharset=" + this.getInputCharset() + ",partner=" + this.getPartner() + ",key=" + this.getKey() + ",sellerEmail=" + this.getSellerEmail() + ",body=" + this.getBody() + ",subject=" + this.getSubject() + ",price=" + this.getPrice() + ",quantity=" + this.getQuantity() + ",showUrl=" + this.getShowUrl() + ",paymentType=" + this.getPaymentType() + ",discount=" + this.getDiscount() + ",logisticsType=" + this.getLogisticsType() + ",logisticsFee=" + this.getLogisticsFee() + ",logisticsPayment=" + this.getLogisticsPayment() + ",notifyUrl=" + this.getNotifyUrl() + ",returnUrl=" + this.getReturnUrl() + ",]";
	}	
}
