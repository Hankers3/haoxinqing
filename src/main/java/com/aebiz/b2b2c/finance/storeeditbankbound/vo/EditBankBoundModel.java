package com.aebiz.b2b2c.finance.storeeditbankbound.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 
 * 商户银行卡绑定信息对象
 * 
 * @author XiaoY
 *
 */
@Entity
@Table(name = "store_bank_rel")
public class EditBankBoundModel extends BaseModel {
	
	/**
	 * 0表示未验证
	 */
	public static String CARD_VALIDATE_STATE_UNTREATED = "0";
	
	/**
	 * 1表示验证成功
	 */
	public static String CARD_VALIDATE_STATE_SUCCESS = "1";
	
	/**
	 * 2表示验证失败
	 */
	public static String CARD_VALIDATE_STATE_FAIL = "2";
	
	/**
	 * 3表示待验证
	 */
	public static String CARD_VALIDATE_STATE_WAIT = "3";
	
	/**
	 * 表示验证的最大次数为3次
	 */
	public static Integer VALIDATION_NUM = 3;
	
	/* 商户编号 */
	private String storeUuid;

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
	
	/*省*/
	private String province = "";
	
	/*市*/
	private String city = "";
	
	/*县*/
	private String region= "";

	/* 验证状态 */
	private String cardValidateState;

	/* 手机号 */
	private String mobile;

	/* 验证码 */
	private String vilidateCode;

	/* 验证金额 */
	private float vilidateMount;

	/* 手机验证状态 */
	private String mobileVilidateState;

	/* 校验失败次数 */
	private String failTimes;

	/* 申请时间 */
	private String applyTime;

	/* 验证时间 */
	private String vilidateTime;

	/* 备注 */
	private String description;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setStoreUuid(String obj) {
		this.storeUuid = obj;
	}

	public String getStoreUuid() {
		return this.storeUuid;
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

	public void setCardValidateState(String obj) {
		this.cardValidateState = obj;
	}

	public String getCardValidateState() {
		return this.cardValidateState;
	}

	public void setMobile(String obj) {
		this.mobile = obj;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setVilidateCode(String obj) {
		this.vilidateCode = obj;
	}

	public String getVilidateCode() {
		return this.vilidateCode;
	}

	public void setVilidateMount(float obj) {
		this.vilidateMount = obj;
	}

	public float getVilidateMount() {
		return this.vilidateMount;
	}

	public void setMobileVilidateState(String obj) {
		this.mobileVilidateState = obj;
	}

	public String getMobileVilidateState() {
		return this.mobileVilidateState;
	}

	public void setFailTimes(String obj) {
		this.failTimes = obj;
	}

	public String getFailTimes() {
		return this.failTimes;
	}

	public void setApplyTime(String obj) {
		this.applyTime = obj;
	}

	public String getApplyTime() {
		return this.applyTime;
	}

	public void setVilidateTime(String obj) {
		this.vilidateTime = obj;
	}

	public String getVilidateTime() {
		return this.vilidateTime;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[storeUuid=" + this.getStoreUuid() + ",openAccountName="
				+ this.getOpenAccountName() + ",companyAccountNo="
				+ this.getCompanyAccountNo() + ",bankNo=" + this.getBankNo()
				+ ",openAccountBankName=" + this.getOpenAccountBankName()
				+ ",bankArea=" + this.getBankArea() + ",cardValidateState="
				+ this.getCardValidateState() + ",mobile=" + this.getMobile()
				+ ",vilidateCode=" + this.getVilidateCode() + ",vilidateMount="
				+ this.getVilidateMount() + ",mobileVilidateState="
				+ this.getMobileVilidateState() + ",failTimes="
				+ this.getFailTimes() + ",applyTime=" + this.getApplyTime()
				+ ",vilidateTime=" + this.getVilidateTime() + ",description="
				+ this.getDescription() + ",]";
	}
}
