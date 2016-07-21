package com.aebiz.b2b2c.order.ordermaincomment.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermainaddress.service.OrderMainAddressService;

@Entity
@Table(name="order_main_comment")
@Component
public class OrderMainCommentModel extends BaseModel{
	/*获取主订单的service*/
	@Transient
	private static OrderMainService  OrderMainService;

	@Autowired
	public void setOrderMainService(OrderMainService  orderMainServic) {
		this.OrderMainService = orderMainServic;
	}
	
	/*获取订单服务信息的service*/
	@Transient
	private static OrderMainAddressService orderMainAddressService;
	
	@Autowired
	public void setOrderMainAddressService(OrderMainAddressService orderMainAddressService) {
		this.orderMainAddressService = orderMainAddressService;
	}
	
	

	/*会员名称*/
	@Transient
	private String customerName="";
	
	/*会员手机*/
	@Transient
	private String customerMobile="";
	/*订单编号*/
	private String orderMainUuid;
	/*会员编号*/
	private String customerUuid;
	/*评价时间*/
	private String commentTime;
	/*评价内容*/
	private String content;
	/*服务评分*/
	private String serviceScore;
	/*处理意见*/
	private String conductIdea;
	/*处理状态*/
	private String conductState;
	/*订单编号*/
	@Transient
	private String orderId="";
	
	
	public String getOrderId() {
		String orderId= OrderMainService.getOrderIdByUuid(this.orderMainUuid);
		if(!StringUtil.isEmpty(orderId)){
			return orderId ;
		}
		return this.orderMainUuid;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/*会员手机*/
	public String getCustomerMobile() {
		return orderMainAddressService.getOrderMainAddresscustomerMobile(this.orderMainUuid);
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	
	/*会员名称*/
	public String getCustomerName() {
		return  customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setOrderMainUuid(String obj){
		this.orderMainUuid = obj;
	}
	public String getOrderMainUuid(){
		return this.orderMainUuid;
	}
	
	public void setCustomerUuid(String obj){
		this.customerUuid = obj;
	}
	public String getCustomerUuid(){
		return this.customerUuid;
	}
	
	public void setCommentTime(String obj){
		this.commentTime = obj;
	}
	public String getCommentTime(){
		return this.commentTime;
	}
	
	public void setContent(String obj){
		this.content = obj;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setServiceScore(String obj){
		this.serviceScore = obj;
	}
	public String getServiceScore(){
		return this.serviceScore;
	}
	
	public void setConductIdea(String obj){
		this.conductIdea = obj;
	}
	public String getConductIdea(){
		return this.conductIdea;
	}
	
	public void setConductState(String obj){
		this.conductState = obj;
	}
	public String getConductState(){
		return this.conductState;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[orderMainUuid=" + this.getOrderMainUuid() + ",customerUuid=" + this.getCustomerUuid() + ",commentTime=" + this.getCommentTime() + ",content=" + this.getContent() + ",serviceScore=" + this.getServiceScore() + ",conductIdea=" + this.getConductIdea() + ",conductState=" + this.getConductState() + ",]";
	}	
}
