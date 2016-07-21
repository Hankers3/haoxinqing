package com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 商户虚拟账户的充值单
 * 
 * 充值的业务流：<br />
 * 
 * 1.商户提交充值，生成单据,待缴费状态<br />
 * 2.支付成功，状态已充值成功，此时需要累加虚拟账户金额<br />
 * 
 * @author tangyunkai
 * 
 * @date 2014年12月29日 下午2:57:49
 * 
 */
@Entity
@Table(name = "virtual_account_store_charge")
public class VirtualAccountStoreChargeModel extends BaseModel {

	/**
	 * 充值状态 待支付
	 */
	public static final String UNDER_PAY = "0";

	/**
	 * 充值状态 充值成功
	 */
	public static final String PAY_SUCCESS = "1";

	/**
	 * 充值状态 充值失败
	 */
	public static final String PAY_FAIL = "2";

	/* 商户uuid */
	private String storeUuid = "";

	/* 充值单号 */
	private String chargeNo = "";

	/* 充值金额 */
	private double operAmount;

	/* 备注 */
	private String note = "";

	/* 充值状态 */
	private String chargeState = "";

	/* 充值时间 */
	private String createTime = "";

	public String getStoreUuid() {
		return storeUuid;
	}

	public void setStoreUuid(String storeUuid) {
		this.storeUuid = storeUuid;
	}

	public void setChargeNo(String obj) {
		this.chargeNo = obj;
	}

	public String getChargeNo() {
		return this.chargeNo;
	}

	public void setOperAmount(double obj) {
		this.operAmount = obj;
	}

	public double getOperAmount() {
		return this.operAmount;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public void setChargeState(String obj) {
		this.chargeState = obj;
	}

	public String getChargeState() {
		return this.chargeState;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[storeUuid=" + this.getStoreUuid() + ",chargeNo="
				+ this.getChargeNo() + ",operAmount=" + this.getOperAmount()
				+ ",note=" + this.getNote() + ",chargeState="
				+ this.getChargeState() + ",createTime=" + this.getCreateTime()
				+ ",]";
	}
}
