package com.aebiz.b2b2c.customermgr.customersubscribecontent.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "customer_subscribe_content")
public class CustomerSubscribeContentModel extends BaseModel {

	/* 订阅名称 */
	private String subscribeName = "";

	/* 订阅编号 */
	private String subscribeNo = "";

	/* 订阅类型 */
	private String subscribeType = "";

	/* 发布状态 */
	private String state = "";

	/* 订阅描述 */
	private String description = "";

	public void setSubscribeName(String obj) {
		this.subscribeName = obj;
	}

	public String getSubscribeName() {
		return this.subscribeName;
	}

	public void setSubscribeNo(String obj) {
		this.subscribeNo = obj;
	}

	public String getSubscribeNo() {
		return this.subscribeNo;
	}

	public void setSubscribeType(String obj) {
		this.subscribeType = obj;
	}

	public String getSubscribeType() {
		return this.subscribeType;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[subscribeName=" + this.getSubscribeName() + ",subscribeNo="
				+ this.getSubscribeNo() + ",subscribeType="
				+ this.getSubscribeType() + ",state=" + this.getState()
				+ ",description=" + this.getDescription() + ",]";
	}
}
