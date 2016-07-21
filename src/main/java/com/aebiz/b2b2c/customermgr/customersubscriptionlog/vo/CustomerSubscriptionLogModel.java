package com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;

@Entity
@Table(name = "customer_subscription_log")
@Component
public class CustomerSubscriptionLogModel extends BaseModel {
	@Transient
	private static CustomerService customerService;

	/* 会员编号 */
	private String customerUuid = "";

	/* 订阅编号 */
	private String subscriptionUuid = "";

	/* 订阅类容 */
	private String content = "";

	/* 发送时间 */
	private String sendTime = "";

	/* 订阅标题 */
	private String subscriptionTitle = "";

	@Transient
	private String customerName = "";

	/* 通过会员编号获取会员名称 */
	public String getCustomerName() {
		if (!StringUtil.isEmpty(this.getCustomerUuid())) {
			return customerService.getCustomerNameByCustomerUuid(this
					.getCustomerUuid());
		}
		return "";
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public static CustomerService getCustomerService() {
		return customerService;
	}
	
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setSubscriptionUuid(String obj) {
		this.subscriptionUuid = obj;
	}

	public String getSubscriptionUuid() {
		return this.subscriptionUuid;
	}

	public void setContent(String obj) {
		this.content = obj;
	}

	public String getContent() {
		return this.content;
	}

	public void setSendTime(String obj) {
		this.sendTime = obj;
	}

	public String getSendTime() {
		return this.sendTime;
	}

	public void setSubscriptionTitle(String obj) {
		this.subscriptionTitle = obj;
	}

	public String getSubscriptionTitle() {
		return this.subscriptionTitle;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[customerUuid=" + this.getCustomerUuid()
				+ ",subscriptionUuid=" + this.getSubscriptionUuid()
				+ ",content=" + this.getContent() + ",sendTime="
				+ this.getSendTime() + ",subscriptionTitle="
				+ this.getSubscriptionTitle() + ",]";
	}
}
