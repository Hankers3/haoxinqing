package com.hxq.mobile.entity.common;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;
/**
 * 会员意见信息表
 * @author aebiz
 *
 */

@SuppressWarnings("serial")
@Entity(name="customer_advice")
public class CustomerAdvice  extends AbstractEntity<String> {
	
	
	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
    public String uuid;
	/*会员编号 */
	private String customerUuid;
	/*意见内容*/
	private String adviceContent;
	/*创建时间*/
	private String createTime;
	/*回馈意见*/
	private String refundConten;
	/*回馈时间*/
	private String refundTime;
	/*回馈人*/
	private String managerUuid;
	/*回馈状态0,未处理   1，已处理*/
	private String status;
	/*用户类型 1:医生 2:患者*/
	private String type;
	
	/*用户电话*/
	private String customerMobile;

	/*用户邮箱*/
	private String customerEmail;
	
	/*用户QQ号码*/
	private String customerQQ;

	public CustomerAdvice() {super();}
	public CustomerAdvice(String uuid) {
		super();
		this.uuid = uuid;
	}
	@Override
	public String getId() {
		return uuid;
	}
	@Override
	public void setId(String id) {
		this.uuid = id;	
	}
	
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerQQ() {
		return customerQQ;
	}
	public void setCustomerQQ(String customerQQ) {
		this.customerQQ = customerQQ;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public void setCustomerUuid(String obj){
		this.customerUuid = obj;
	}
	public String getCustomerUuid(){
		return this.customerUuid;
	}
	
	public void setAdviceContent(String obj){
		this.adviceContent = obj;
	}
	public String getAdviceContent(){
		return this.adviceContent;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setRefundConten(String obj){
		this.refundConten = obj;
	}
	public String getRefundConten(){
		return this.refundConten;
	}
	
	public void setRefundTime(String obj){
		this.refundTime = obj;
	}
	public String getRefundTime(){
		return this.refundTime;
	}
	
	public void setManagerUuid(String obj){
		this.managerUuid = obj;
	}
	public String getManagerUuid(){
		return this.managerUuid;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[customerUuid=" + this.getCustomerUuid() + ",adviceContent=" + this.getAdviceContent() + ",createTime=" + this.getCreateTime() + ",refundConten=" + this.getRefundConten() + ",refundTime=" + this.getRefundTime() + ",managerUuid=" + this.getManagerUuid() + ",]";
	}	
}
