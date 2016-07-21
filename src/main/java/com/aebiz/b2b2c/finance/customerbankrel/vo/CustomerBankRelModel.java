package com.aebiz.b2b2c.finance.customerbankrel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;

/**
 * 会员和银行卡绑定<br>
 * 
 * 绑定时需要输入身份证,上传身份证图片等信息,手机验证码通过后提交申请,<br>
 * 
 * 平台后台往这个银行账户打入一定金额,把这个金额保存到数据库,会员在会员中心通过输入这个金额来验证
 *
 * @author tangyunkai
 *
 * @date 2014年12月8日 下午8:15:07
 *
 */
@Entity
@Component
@Table(name = "customer_bank_rel")
public class CustomerBankRelModel extends BaseModel {
	
	//注入会员接口,为了获取会员编号和名称
	@Transient
	private static CustomerInteractive customerInteractive;
	
	@Autowired
	public void setCustomerInteractive(
			CustomerInteractive customerInteractive) {
		CustomerBankRelModel.customerInteractive = customerInteractive;
		
	}

	/**
	 * 验证状态 未验证
	 */
	public static final String NO_CHECK = "0";

	/**
	 * 验证状态 待验证
	 */
	public static final String UNDER_CHECK = "1";

	/**
	 * 验证状态 验证通过
	 */
	public static final String CHECK_PASS = "2";

	/**
	 * 验证状态 验证不通过
	 */
	public static final String CHECK_FAIL = "3";

	/* 用户uuid */
	private String customerUuid = "";
	
	/* 获取用户编号 */
	private String customerNo = "";

	/* 银行卡号 */
	private String cardNo = "";

	/* 开户行 */
	private String openAccountBank = "";

	/* 开户名 */
	private String openAccountName = "";

	/* 验证状态 */
	private String cardValidateState = "";

	/* 手机号 */
	private String mobile = "";

	/* 验证码 */
	private String vilidateCode = "";

	/* 验证金额 */
	private float vilidateMount;

	/* 手机验证状态 */
	private String mobileVilidateState = "";

	/* 校验失败次数 */
	private int failTimes;

	/* 证件类型名称 */
	private String cardTypeName = "";

	/* 证件图片1 */
	private String idCard1 = "";

	/* 证件图片2 */
	private String idCard2 = "";

	/* 身份证号 */
	private String idcardNo = "";

	/* 申请时间 */
	private String createTime;

	public String getCustomerNo() {
		CustomerModel customerModel = customerInteractive.getCustomerModelByCondition(getCustomerUuid());
		if(customerModel != null){
			return customerModel.getCustomerId();
		}
		return customerNo;
	}

//	public void setCustomerNo(String customerNo) {
//		this.customerNo = customerNo;
//	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getOpenAccountBank() {
		return openAccountBank;
	}

	public void setOpenAccountBank(String openAccountBank) {
		this.openAccountBank = openAccountBank;
	}

	public String getOpenAccountName() {
		return openAccountName;
	}

	public void setOpenAccountName(String openAccountName) {
		this.openAccountName = openAccountName;
	}

	public String getCardValidateState() {
		return cardValidateState;
	}

	public void setCardValidateState(String cardValidateState) {
		this.cardValidateState = cardValidateState;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVilidateCode() {
		return vilidateCode;
	}

	public void setVilidateCode(String vilidateCode) {
		this.vilidateCode = vilidateCode;
	}

	public float getVilidateMount() {
		return vilidateMount;
	}

	public void setVilidateMount(float vilidateMount) {
		this.vilidateMount = vilidateMount;
	}

	public String getMobileVilidateState() {
		return mobileVilidateState;
	}

	public void setMobileVilidateState(String mobileVilidateState) {
		this.mobileVilidateState = mobileVilidateState;
	}

	public int getFailTimes() {
		return failTimes;
	}

	public void setFailTimes(int failTimes) {
		this.failTimes = failTimes;
	}

	public String getIdCard1() {
		return idCard1;
	}

	public void setIdCard1(String idCard1) {
		this.idCard1 = idCard1;
	}

	public String getIdCard2() {
		return idCard2;
	}

	public void setIdCard2(String idCard2) {
		this.idCard2 = idCard2;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CustomerBankRelModel [customerUuid=" + customerUuid
				+ ", cardNo=" + cardNo + ", openAccountBank=" + openAccountBank
				+ ", openAccountName=" + openAccountName
				+ ", cardValidateState=" + cardValidateState + ", mobile="
				+ mobile + ", vilidateCode=" + vilidateCode
				+ ", vilidateMount=" + vilidateMount + ", mobileVilidateState="
				+ mobileVilidateState + ", failTimes=" + failTimes
				+ ", cardTypeName=" + cardTypeName + ", idCard1=" + idCard1
				+ ", idCard2=" + idCard2 + ", idcardNo=" + idcardNo
				+ ", createTime=" + createTime + "]";
	}

}
