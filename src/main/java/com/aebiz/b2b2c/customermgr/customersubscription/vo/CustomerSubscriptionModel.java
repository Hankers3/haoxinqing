package com.aebiz.b2b2c.customermgr.customersubscription.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.service.CustomerSubscribeContentService;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentModel;

@Entity
@Table(name = "customer_subscription")
@Component
public class CustomerSubscriptionModel extends BaseModel {
	/* 会员订阅状态 ：1表示已订阅,0表示未订阅 */
	public static final String CUSTOMERSUBSCRIPTION_SUBSCRIPTIONSTATE_SUBSCRIBED = "1";

	public static final String CUSTOMERSUBSCRIPTION_SUBSCRIPTIONSTATE_UNSUBSCRIBED = "0";

	/* 是否发送营销信：1表示已发送，0表示未发送 */
	public static final String CUSTOMERSUBSCRIPTION_OPERTIONSEND_SEND = "1";

	public static final String CUSTOMERSUBSCRIPTION_OPERTIONSEND_NOSEND = "0";

	@Transient
	private static CustomerSubscribeContentService customerSubscribeContentService;

	/* 订阅内容model */
	@Transient
	private CustomerSubscribeContentModel customerSubscribeContentModel;

	/* 会员编号 */
	private String customerUuid = "";

	/* 订阅编号 */
	private String subscriptionUuid = "";

	/* 手机号码 */
	private String mobile = "";

	/* 邮箱 */
	private String email = "";

	/* 订阅状态 */
	private String subscriptionState = "";

	/* 是否发送营销信 */
	private String opertionSend = "";

	/* 会员名称 */
	@Transient
	private String customerName = "";

	public CustomerSubscribeContentModel getCustomerSubscribeContentModel() {
		CustomerSubscribeContentModel customerSubscribeContentModel = customerSubscribeContentService
				.getByUuid(this.getSubscriptionUuid());
		return customerSubscribeContentModel;
	}

	public void setCustomerSubscribeContentModel(
			CustomerSubscribeContentModel customerSubscribeContentModel) {
		this.customerSubscribeContentModel = customerSubscribeContentModel;
	}

	@Autowired
	public void setCustomerSubscribeContentService(
			CustomerSubscribeContentService customerSubscribeContentService) {
		this.customerSubscribeContentService = customerSubscribeContentService;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setSubscriptionUuid(String obj) {
		this.subscriptionUuid = obj;
	}

	public String getSubscriptionUuid() {
		return this.subscriptionUuid;
	}

	public void setMobile(String obj) {
		this.mobile = obj;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setEmail(String obj) {
		this.email = obj;
	}

	public String getEmail() {
		return this.email;
	}

	public String getSubscriptionState() {
		return subscriptionState;
	}

	public void setSubscriptionState(String subscriptionState) {
		this.subscriptionState = subscriptionState;
	}

	public void setOpertionSend(String obj) {
		this.opertionSend = obj;
	}

	public String getOpertionSend() {
		return this.opertionSend;
	}

	@Override
	public String toString() {
		return "CustomerSubscriptionModel [customerUuid=" + customerUuid
				+ ", subscriptionUuid=" + subscriptionUuid + ", mobile="
				+ mobile + ", email=" + email + ", subscriptionState="
				+ subscriptionState + ", opertionSend=" + opertionSend
				+ ", customerName=" + customerName + "]";
	}
}
