package com.aebiz.b2b2c.finance.orderreturnmoneymain.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.orderaftersaleservice.orderaftersaleservicemain.vo.OrderAfterMoneyBackTypeEnum;
import com.aebiz.b2b2c.orderaftersaleservice.orderaftersaleservicemain.vo.OrderAfterSaleBackMoneyStatusEnum;

@Entity
@Table(name = "order_return_money_main")
@Component
public class OrderReturnMoneyMainModel extends BaseModel {

	@Transient
	private static CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/* 订单编号 */
	private String orderMainUuid;

	/* 退货单编号 */
	private String afterServiceUuid;

	/* 申请人 */
	private String customerUuid;

	/* 处理人类型 */
	private String personType;

	/* 申请时间 */
	private String applyTime;

	/* 退款金额 */
	private double backMoney;

	/* 退款原因 */
	private String reason;

	/* 状态 */
	private String state;

	/* 退款方式 */
	private String returnType;

	/* 退款方式描述 */
	private String description;

	/*退款备注*/
	private String remarks;

	@Transient
	private String returnTypeName;

	@Transient
	private String customerName;

	@Transient
	private String stateName;

	public void setOrderMainUuid(String obj) {
		this.orderMainUuid = obj;
	}

	public String getOrderMainUuid() {
		return this.orderMainUuid;
	}

	public void setAfterServiceUuid(String obj) {
		this.afterServiceUuid = obj;
	}

	public String getAfterServiceUuid() {
		return this.afterServiceUuid;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setApplyTime(String obj) {
		this.applyTime = obj;
	}

	public String getApplyTime() {
		return this.applyTime;
	}

	public void setBackMoney(double obj) {
		this.backMoney = obj;
	}

	public double getBackMoney() {
		return this.backMoney;
	}

	public void setReason(String obj) {
		this.reason = obj;
	}

	public String getReason() {
		return this.reason;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setReturnType(String obj) {
		this.returnType = obj;
	}

	public String getReturnType() {
		return this.returnType;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 获取退款类型的类型名称
	 * @return
	 */
	public String getReturnTypeName() {
		return OrderAfterMoneyBackTypeEnum.getNameByKey(this.getReturnType());
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * 通过会员Uuid获取会员名
	 * @return
	 */
	public String getCustomerName() {
		return customerService.getCustomerNameByCustomerUuid(this
				.getCustomerUuid());
	}
	
	/**
	 * 获取退款但状态名
	 * @return
	 */
	public String getStateName() {
		return OrderAfterSaleBackMoneyStatusEnum.getNameByKey(this.getState());
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[orderMainUuid=" + this.getOrderMainUuid()
				+ ",afterServiceUuid=" + this.getAfterServiceUuid()
				+ ",customerUuid=" + this.getCustomerUuid() + ",applyTime ="
				+ this.getApplyTime() + ",backMoney=" + this.getBackMoney()
				+ ",reason=" + this.getReason() + ",state=" + this.getState()
				+ ",returnType=" + this.getReturnType() + ",description="
				+ this.getDescription() + ",]";
	}
}
