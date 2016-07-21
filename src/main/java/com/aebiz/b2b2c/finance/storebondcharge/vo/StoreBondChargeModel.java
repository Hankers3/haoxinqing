package com.aebiz.b2b2c.finance.storebondcharge.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.store.storeaccount.service.StoreAccountService;
import com.aebiz.b2b2c.store.storeaccount.vo.StoreAccountModel;
import com.aebiz.b2b2c.store.storecontract.service.StoreContractService;
import com.aebiz.b2b2c.store.storecontract.vo.StoreContractModel;

/**
 * 商户保证金<br>
 * 
 * 保证金来源分为三种<br>
 * 1.合同生成的时候生成<br>
 * 2.违约的时候缴款<br>
 * 3.违约金充值
 *
 * @author tangyunkai
 *
 * @date 2014年12月5日 上午10:09:32
 *
 */
@Entity
@Component
@Table(name = "store_bond_charge")
public class StoreBondChargeModel extends BaseModel {

	@Transient
	// 注入商户的账户信息service,以便获取商户的编号和商户的名称
	private static StoreAccountService accountService;

	@Autowired
	public void setAccountService(StoreAccountService accountService) {
		this.accountService = accountService;
	}

	@Transient
	// 注入商户的合同service,以便获取合同编号
	private static StoreContractService storeContractService;

	@Autowired
	public void setStoreContractService(
			StoreContractService storeContractService) {
		this.storeContractService = storeContractService;
	}

	/**
	 * 增加保证金
	 */
	public static final String ADD = "0";

	/**
	 * 扣减保证金
	 */
	public static final String MINUS = "1";

	/**
	 * 来源 0:从合同生成的增加保证金
	 */
	public static final String FROM_CONTRACT = "0";

	/**
	 * 来源 1:违约处罚，扣减保证金
	 */
	public static final String BREAK_MINUS = "1";

	/**
	 * 来源 2:商户普通充值
	 */
	public static final String NORMAL_CHARGE = "2";

	/**
	 * 操作状态 0:待支付
	 */
	public static final String UNDER_PAY = "0";

	/**
	 * 操作状态 1:支付成功
	 */
	public static final String PAY_SUCCESS = "1";

	/**
	 * 操作状态 2:支付失败
	 */
	public static final String PAY_FAIL = "2";

	/**
	 * 支付方式 1: 线上方式
	 */
	public static final String ONLINE = "1";

	/**
	 * 支付方式 2:线下方式
	 */
	public static final String OFFLINE = "2";

	/* 合同编号 */
	private String contractUuid = "";

	/* 单据编号 */
	private String bondUuid = "";

	/* 操作金额 */
	private float operAmount;

	/* 操作类型 0:增加 1:扣减 */
	private String operType = "";

	/* 操作状态 0:待支付 1:支付成功 2:支付失败 */
	private String operState = "";

	/* 来源 0:合同 1:违约处罚，扣减保证金 2:违约金充值 3:商户普通充值 */
	private String fromSource = "";

	/* 备注 */
	private String note = "";

	/* 商户的Uuid */
	private String accountUuid = "";

	/* 保证金余额 */
	private float bondBalance;

	/* 生成时间 */
	private String createTime = "";

	/* 支付方式 */
	private String payType = "";

	/* 商户名称 */
	@Transient
	private String storeName = "";

	/* 商户编号 */
	@Transient
	private String storeNo = "";

	/* 商户合同编号 */
	@Transient
	private String contractNo = "";

	// 获取商户名称
	public String getStoreName() {
		// 获取商户账户对象
		StoreAccountModel accountModel = accountService.getByUuid(accountUuid);
		if (accountModel != null) {
			this.storeNo = accountModel.getStoreUuid();
			return accountModel.getStoreName();
		}
		return "";
	}

	/* 获取商户合同编号 */
	public String getContractNo() {
		if (!StringUtil.isEmpty(this.contractUuid)) {
			// 获取商户合同对象
			StoreContractModel contractModel = storeContractService
					.getByUuid(this.contractUuid);
			if (contractModel != null) {
				return contractModel.getContractUuid();
			}
		}
		return "";
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public void setContractUuid(String obj) {
		this.contractUuid = obj;
	}
	public String getContractUuid() {
		return this.contractUuid;
	}

	public void setBondUuid(String obj) {
		this.bondUuid = obj;
	}
	public String getBondUuid() {
		return this.bondUuid;
	}

	public void setOperAmount(float obj) {
		this.operAmount = obj;
	}
	public float getOperAmount() {
		return this.operAmount;
	}

	public void setOperType(String obj) {
		this.operType = obj;
	}
	public String getOperType() {
		return this.operType;
	}

	public void setOperState(String obj) {
		this.operState = obj;
	}
	public String getOperState() {
		return this.operState;
	}

	public void setFromSource(String obj) {
		this.fromSource = obj;
	}
	public String getFromSource() {
		return this.fromSource;
	}

	public void setNote(String obj) {
		this.note = obj;
	}
	public String getNote() {
		return this.note;
	}

	public void setAccountUuid(String obj) {
		this.accountUuid = obj;
	}
	public String getAccountUuid() {
		return this.accountUuid;
	}

	public void setBondBalance(float obj) {
		this.bondBalance = obj;
	}
	public float getBondBalance() {
		return this.bondBalance;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}
	public String getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[contractUuid=" + this.getContractUuid() + ",bondUuid="
				+ this.getBondUuid() + ",operAmount=" + this.getOperAmount()
				+ ",operType=" + this.getOperType() + ",operState="
				+ this.getOperState() + ",fromSource=" + this.getFromSource()
				+ ",note=" + this.getNote() + ",accountUuid="
				+ this.getAccountUuid() + ",bondBalance="
				+ this.getBondBalance() + ",createTime=" + this.getCreateTime()
				+ ",]";
	}
}
