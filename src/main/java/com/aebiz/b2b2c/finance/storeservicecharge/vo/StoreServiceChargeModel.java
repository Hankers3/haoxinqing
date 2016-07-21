package com.aebiz.b2b2c.finance.storeservicecharge.vo;

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
 * 商户服务费<br>
 * 
 * 生成合同后,后台会生成一条缴纳服务费的单据,在商户后台商户就可以支付该条单据<br>
 * 
 * 服务费是由平台发起的
 *
 * @author tangyunkai
 *
 * @date 2014年12月8日 上午10:20:13
 *
 */
@Entity
@Component
@Table(name = "store_service_charge")
public class StoreServiceChargeModel extends BaseModel {

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
	 * 支付状态 0:待支付
	 */
	public static final String UNDER_PAY = "0";

	/**
	 * 支付状态 1:支付成功
	 */
	public static final String PAY_SUCCESS = "1";

	/**
	 * 支付状态 2:支付失败
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

	/* 合同的uuid */
	private String contractUuid = "";

	/* 单据编号 */
	private String bondUuid = "";

	/* 缴纳金额 */
	private float payAmount;

	/* 缴费方式 线上或线下转款 */
	private String payType = "";

	/* 支付状态 0:待支付 1:支付成功 2:支付失败 */
	private String payStatus = "";

	/* 缴费时间 */
	private String payTime = "";

	/* 商户的Uuid */
	private String accountUuid = "";

	/* 单据生成时间 */
	private String createTime = "";
	
	/* 商户名称 */
	@Transient
	private String storeName = "";

	/* 商户编号 */
	@Transient
	private String storeNo = "";

	/* 商户合同编号 */
	@Transient
	private String contractNo = "";
	
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

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
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

	public void setPayAmount(float obj) {
		this.payAmount = obj;
	}
	public float getPayAmount() {
		return this.payAmount;
	}

	public void setPayType(String obj) {
		this.payType = obj;
	}
	public String getPayType() {
		return this.payType;
	}

	public void setPayStatus(String obj) {
		this.payStatus = obj;
	}
	public String getPayStatus() {
		return this.payStatus;
	}

	public void setPayTime(String obj) {
		this.payTime = obj;
	}
	public String getPayTime() {
		return this.payTime;
	}

	public void setAccountUuid(String obj) {
		this.accountUuid = obj;
	}
	public String getAccountUuid() {
		return this.accountUuid;
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
				+ this.getBondUuid() + ",payAmount=" + this.getPayAmount()
				+ ",payType=" + this.getPayType() + ",payStatus="
				+ this.getPayStatus() + ",payTime=" + this.getPayTime()
				+ ",accountUuid=" + this.getAccountUuid() + ",createTime="
				+ this.getCreateTime() + ",]";
	}
}
