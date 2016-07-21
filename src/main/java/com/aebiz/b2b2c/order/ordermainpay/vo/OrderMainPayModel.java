package com.aebiz.b2b2c.order.ordermainpay.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 
 * 订单生成后，需要生成支付记录，支付后回填支付状态等信息
 * 
 * 对于预售类的订单，可能会有多个支付记录，首款和尾款都需要支付
 * 
 * @author duandeyi
 * 
 */

@Entity
@Table(name = "order_main_pay")
public class OrderMainPayModel extends BaseModel {
	/* 支付方式    1:支付宝,2：微信,3：网银支付 */
	private String payType;

	/* 支付网关 */
	private String payGate;

	/* 实付金额 */
	private double payMoney;

	/* 付款时间 */
	private String payTime;

	/* 收款方式 */
	private String cashType;

	/* 支付流水号 */
	private String payNo;

	/* 订单号 */
	private String orderMainUuid;

	/* 支付状态 */
	private String payState;

	/* 支付顺序 ，比如有尾款或者多次支付的，可以设置顺序 */
	private int payPosition = 0;

	public void setPayType(String obj) {
		this.payType = obj;
	}

	public String getPayType() {
		return this.payType;
	}

	public void setPayGate(String obj) {
		this.payGate = obj;
	}

	public String getPayGate() {
		return this.payGate;
	}

	public void setPayMoney(double obj) {
		this.payMoney = obj;
	}

	public double getPayMoney() {
		return this.payMoney;
	}

	public void setPayTime(String obj) {
		this.payTime = obj;
	}

	public String getPayTime() {
		return this.payTime;
	}

	public void setCashType(String obj) {
		this.cashType = obj;
	}

	public String getCashType() {
		return this.cashType;
	}

	public void setPayNo(String obj) {
		this.payNo = obj;
	}

	public String getPayNo() {
		return this.payNo;
	}

	public void setOrderMainUuid(String obj) {
		this.orderMainUuid = obj;
	}

	public String getOrderMainUuid() {
		return this.orderMainUuid;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public int getPayPosition() {
		return payPosition;
	}

	public void setPayPosition(int payPosition) {
		this.payPosition = payPosition;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[payType=" + this.getPayType() + ",payGate="
				+ this.getPayGate() + ",payMoney=" + this.getPayMoney()
				+ ",payTime=" + this.getPayTime() + ",cashType="
				+ this.getCashType() + ",payNo=" + this.getPayNo()
				+ ",orderMainUuid=" + this.getOrderMainUuid() + ",]";
	}
}
