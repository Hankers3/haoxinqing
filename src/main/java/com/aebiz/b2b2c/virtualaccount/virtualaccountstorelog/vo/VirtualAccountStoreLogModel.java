package com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 
 * 商户的虚拟账户日志
 * 
 * 1.生成提现申请，需要记录日志<br />
 * 2.提现成功，需要记录日志<br />
 * 3.充值申请,不需要记录日志(金额没有发生变动)<br />
 * 4.充值缴费成功，记录日志<br />
 * 
 * @author duandeyi
 * 
 */
@Entity
@Table(name = "virtual_account_store_log")
public class VirtualAccountStoreLogModel extends BaseModel {
	/**
	 * 收支类型 收入
	 */
	public static final String IN = "0";

	/**
	 * 收支类型 支出
	 */
	public static final String OUT = "1";

	/**
	 * 状态-冻结
	 */
	public static final String FREEZE = "0";

	/**
	 * 状态-正常
	 */
	public static final String NORMAL = "1";

	/**
	 * 类型-商户虚拟账户
	 */
	public static final String ACCOUNT = "1";

	/**
	 * 类型-商户保证金
	 */
	public static final String BOND = "2";

	/* 商户uuid */
	private String storeUuid;

	/* 收支类型 */
	private String operType;

	/* 操作金额 */
	private double operAmount;

	/* 当前余额 */
	private double nowBalance;

	/* 单据编号 */
	private String documentNo;

	/* 备注 */
	private String description;

	/* 冻结状态 */
	private String frozenState;

	/* 类型 */
	private String rechargeType;

	public void setStoreUuid(String obj) {
		this.storeUuid = obj;
	}

	public String getStoreUuid() {
		return this.storeUuid;
	}

	public void setOperType(String obj) {
		this.operType = obj;
	}

	public String getOperType() {
		return this.operType;
	}

	public void setOperAmount(double obj) {
		this.operAmount = obj;
	}
	public double getOperAmount() {
		return this.operAmount;
	}

	public void setNowBalance(double obj) {
		this.nowBalance = obj;
	}
	public double getNowBalance() {

		return this.nowBalance;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public void setFrozenState(String obj) {
		this.frozenState = obj;
	}

	public String getFrozenState() {
		return this.frozenState;
	}
}
