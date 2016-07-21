package com.aebiz.b2b2c.order.ordermainfrom.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderFromTypeEnum;

@Entity
@Table(name = "order_main_from")
public class OrderMainFromModel extends BaseModel {

	/* 客户端类型 */
	private String clientType;

	/* 来源类型 */
	private String fromType;

	/* 来源地址 */
	private String fromAddress;

	/* 来源地址 */
	private String orderMainUuid;

	/* 订单来源名称 */
	@Transient
	private String fromTypeName;

	public void setClientType(String obj) {
		this.clientType = obj;
	}

	public String getClientType() {
		return this.clientType;
	}

	public void setFromType(String obj) {
		this.fromType = obj;
	}

	public String getFromType() {
		return this.fromType;
	}

	public void setFromAddress(String obj) {
		this.fromAddress = obj;
	}

	public String getFromAddress() {
		return this.fromAddress;
	}

	public String getOrderMainUuid() {
		return orderMainUuid;
	}

	public void setOrderMainUuid(String orderMainUuid) {
		this.orderMainUuid = orderMainUuid;
	}

	public String getFromTypeName() {
		return OrderFromTypeEnum.getNameByKey(this.getFromType());
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[clientType=" + this.getClientType() + ",fromType="
				+ this.getFromType() + ",fromAddress=" + this.getFromAddress()
				+ ",]";
	}
}
