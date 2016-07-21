package com.aebiz.b2b2c.finance.customeraccount.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 记录会员的账户信息<br>
 * 
 * 积分信息、虚拟账户信息、礼品卡信息等
 * 
 * @author tangyunkai
 * 
 * @date 2014年12月8日 下午7:50:10
 * 
 */
@Entity
@Table(name = "customer_account")
public class CustomerAccountModel extends BaseModel {

	/* 用户编号 */
	private String customerUuid = "";

	/* 账户余额 */
	private float accountBalance;

	/* 礼品卡可用金额 */
	private float giftCardBalance;

	/* 礼品卡绑定数量 */
	private int giftCardCount;

	/* 可用积分数 */
	private int availableIntegral;

	/* 总积分数 */
	private int totalIntegral;

	/* 虚拟账户支付密码 */
	private String payPasswd;

	/* 积分账户状态 */
	private String integralState = "";

	/* 礼品卡账户状态 */
	private String giftCardState = "";

	/* 虚拟账户状态 */
	private String virtualFrezonState = "";

	/* 虚拟账户冻结金额 */
	private float virtualFrezonMount;

	/* 银行卡绑定数量 */
	private int bankCardBindCount;

	/* 银行卡绑定申请次数 */
	private String bankBindTimes = "";

	/* 银行卡号 */
	private String bankNo = "";

	/* 账户名 */
	private String customerName = "";

	/* 开户行 */
	private String bankName = "";

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setGiftCardBalance(float obj) {
		this.giftCardBalance = obj;
	}

	public float getGiftCardBalance() {
		return this.giftCardBalance;
	}

	public void setAvailableIntegral(int obj) {
		this.availableIntegral = obj;
	}

	public int getAvailableIntegral() {
		return this.availableIntegral;
	}

	public void setTotalIntegral(int obj) {
		this.totalIntegral = obj;
	}

	public int getTotalIntegral() {
		return this.totalIntegral;
	}

	public void setIntegralState(String obj) {
		this.integralState = obj;
	}

	public String getIntegralState() {
		return this.integralState;
	}

	public void setGiftCardState(String obj) {
		this.giftCardState = obj;
	}

	public String getGiftCardState() {
		return this.giftCardState;
	}

	public void setVirtualFrezonState(String obj) {
		this.virtualFrezonState = obj;
	}

	public String getVirtualFrezonState() {
		return this.virtualFrezonState;
	}

	public void setBankBindTimes(String obj) {
		this.bankBindTimes = obj;
	}

	public String getBankBindTimes() {
		return this.bankBindTimes;
	}

	public float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getGiftCardCount() {
		return giftCardCount;
	}

	public void setGiftCardCount(int giftCardCount) {
		this.giftCardCount = giftCardCount;
	}

	public float getVirtualFrezonMount() {
		return virtualFrezonMount;
	}

	public void setVirtualFrezonMount(float virtualFrezonMount) {
		this.virtualFrezonMount = virtualFrezonMount;
	}

	public int getBankCardBindCount() {
		return bankCardBindCount;
	}

	public void setBankCardBindCount(int bankCardBindCount) {
		this.bankCardBindCount = bankCardBindCount;
	}

	public String getPayPasswd() {
		return payPasswd;
	}

	public void setPayPasswd(String payPasswd) {
		this.payPasswd = payPasswd;
	}

}
