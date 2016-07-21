package com.aebiz.b2b2c.finance.storefinanceaccount.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 记录商户的账户信息
 *
 * @author tangyunkai
 *
 * @date 2014年12月9日 上午11:18:20
 *
 */
@Entity
@Table(name = "store_finance_account")
public class StoreFinanceAccountModel extends BaseModel {

	/* 商户uuid */
	private String accountUuid;

	/* 冻结保证金金额 */
	private float freezeBondMoney;

	/* 当前保证金金额 */
	private float bondMoney;

	/* 虚拟账户支付密码 */
	private String virtualPasswd;

	/* 虚拟账户状态 */
	private String virtualState;

	/* 虚拟账户可用金额 */
	private float virtualMoney;

	/* 累计处罚积分 */
	private int punishIntegral;

	/* 银行开户名 */
	private String openAccountName;

	/* 公司银行账户号码 */
	private String companyAccountNo;

	/* 开户行支行联行号 */
	private String bankNo;

	/* 开户行支行名称 */
	private String openAccountBankName;

	/* 开户行所在地 */
	private String bankArea;

	public void setAccountUuid(String obj) {
		this.accountUuid = obj;
	}
	public String getAccountUuid() {
		return this.accountUuid;
	}

	public void setFreezeBondMoney(float obj) {
		this.freezeBondMoney = obj;
	}
	public float getFreezeBondMoney() {
		return this.freezeBondMoney;
	}

	public void setBondMoney(float obj) {
		this.bondMoney = obj;
	}
	public float getBondMoney() {
		return this.bondMoney;
	}

	public void setVirtualPasswd(String obj) {
		this.virtualPasswd = obj;
	}
	public String getVirtualPasswd() {
		return this.virtualPasswd;
	}

	public void setVirtualState(String obj) {
		this.virtualState = obj;
	}
	public String getVirtualState() {
		return this.virtualState;
	}

	public void setVirtualMoney(float obj) {
		this.virtualMoney = obj;
	}
	public float getVirtualMoney() {
		return this.virtualMoney;
	}

	public void setPunishIntegral(int obj) {
		this.punishIntegral = obj;
	}
	public int getPunishIntegral() {
		return this.punishIntegral;
	}

	public void setOpenAccountName(String obj) {
		this.openAccountName = obj;
	}
	public String getOpenAccountName() {
		return this.openAccountName;
	}

	public void setCompanyAccountNo(String obj) {
		this.companyAccountNo = obj;
	}
	public String getCompanyAccountNo() {
		return this.companyAccountNo;
	}

	public void setBankNo(String obj) {
		this.bankNo = obj;
	}
	public String getBankNo() {
		return this.bankNo;
	}

	public void setOpenAccountBankName(String obj) {
		this.openAccountBankName = obj;
	}
	public String getOpenAccountBankName() {
		return this.openAccountBankName;
	}

	public void setBankArea(String obj) {
		this.bankArea = obj;
	}
	public String getBankArea() {
		return this.bankArea;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[accountUuid=" + this.getAccountUuid() + ",freezeBondMoney="
				+ this.getFreezeBondMoney() + ",bondMoney="
				+ this.getBondMoney() + ",virtualPasswd="
				+ this.getVirtualPasswd() + ",virtualState="
				+ this.getVirtualState() + ",virtualMoney="
				+ this.getVirtualMoney() + ",punishIntegral="
				+ this.getPunishIntegral() + ",openAccountName="
				+ this.getOpenAccountName() + ",companyAccountNo="
				+ this.getCompanyAccountNo() + ",bankNo=" + this.getBankNo()
				+ ",openAccountBankName=" + this.getOpenAccountBankName()
				+ ",bankArea=" + this.getBankArea() + ",]";
	}
}
