package com.aebiz.b2b2c.finance.orderreturnmoneylog.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "order_return_money_log")
public class OrderReturnMoneyLogModel extends BaseModel {

	/* 退款单编号 */
	private String returnMoneyMainUuid;

	/* 处理人 */
	private String personUuid;

	/* 处理人类型 */
	private String operType;

	/* 处理结果 */
	private String result;

	/* 处理时间 */
	private String dealTime;

	/* 处理说明 */
	private String description;

	public void setReturnMoneyMainUuid(String obj) {
		this.returnMoneyMainUuid = obj;
	}

	public String getReturnMoneyMainUuid() {
		return this.returnMoneyMainUuid;
	}

	public void setPersonUuid(String obj) {
		this.personUuid = obj;
	}

	public String getPersonUuid() {
		return this.personUuid;
	}

	public void setOperType(String obj) {
		this.operType = obj;
	}

	public String getOperType() {
		return this.operType;
	}

	public void setResult(String obj) {
		this.result = obj;
	}

	public String getResult() {
		return this.result;
	}

	public void setDealTime(String obj) {
		this.dealTime = obj;
	}

	public String getDealTime() {
		return this.dealTime;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[returnMoneyMainUuid=" + this.getReturnMoneyMainUuid()
				+ ",personUuid=" + this.getPersonUuid() + ",operType="
				+ this.getOperType() + ",result =" + this.getResult()
				+ ",dealTime=" + this.getDealTime() + ",description="
				+ this.getDescription() + ",]";
	}
}
