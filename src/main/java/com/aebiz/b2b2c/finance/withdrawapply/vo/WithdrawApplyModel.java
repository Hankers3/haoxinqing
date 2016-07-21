package com.aebiz.b2b2c.finance.withdrawapply.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.service.HospitalInfoService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;

/**
 * 
 * @author xueli 20150323 update
 * 
 */
@Entity
@Table(name = "withdraw_apply")
@Component
public class WithdrawApplyModel extends BaseModel {

	/* 注解医生service */
	@Transient
	private static ServicestaffService servicestaffService;


	@Autowired
	public void setServicestaffService(ServicestaffService servicestaffService) {
		this.servicestaffService = servicestaffService;
	}

	/* 注解医院service */
	@Transient
	private static HospitalInfoService hospitalInfoService;

	@Autowired
	public void setHospitalInfoService(HospitalInfoService hospitalInfoService) {
		this.hospitalInfoService = hospitalInfoService;
	}

	/* 用户类型-会员用户 */
	public static final String USERTYPE_CUSTOMER = "1";

	/* 用户类型-家政员用户 */
	public static final String USERTYPE_STAFF = "2";

	/* 0：表示未处理 */
	public static final String APPLY_STATE_UNHANDLE = "0";

	/* 1：表示已转账（提现成功） */
	public static final String APPLY_STATE_SUCCESS = "1";

	/* 2：表示驳回（提现失败 */
	public static final String APPLY_STATE_FAIL = "2";

	/* 1.表示家政员余额 */
	public static final String STAFF_BALANCE = "1";

	/* 2.表示会员余额 */
	public static final String COUSTOMER = "2";

	/* 3.表示商户保证金 */
	public static final String STORE_MARGIN = "3";

	/* 最近一周 */
	public static final String LAST_ONEWEEK = "0";

	/* 最近一个月 */
	public static final String LAST_ONEMONTH = "1";

	/* 最近三个月 */
	public static final String LAST_THREEMONTH = "2";

	/* 最近半年 */
	public static final String LAST_HALFYEAR = "3";

	/**
	 * 1表示患者类型 2表示医生类型
	 */
	/* 用户类型 */
	private String userType;

	/* 关联的银行卡id */
	private String bankRelationUuid;

	/* 用户uuid */
	private String userUuid;

	/* 提现资金类型 */
	/**
	 * 1表示医生余额 2表示患者余额 3表示商户保证金
	 */
	private String moneyType;

	/* 提现金额 */
	private double applyMoney;

	/* 申请时间 */
	private String applyTime;

	/**
	 * 当前状态 state 的值：<br>
	 * 0：表示未处理<br>
	 * 1：表示已转账（提现成功）<br>
	 * 2：表示驳回（提现失败）<br>
	 */
	private String state;

	/* 备注 */
	private String note;

	/* 失败原因 */
	private String failReason;

	/* 提现时间 */
	private String successTime;

	/* 用户名 */
	private String userName;

	/* 用户编号 */
	private String userNo;

	/* 医生所在医院id */
	private String hospitalUuid;

	/* 医生当前余额 */
	private double accountAmount;

	/* 医生手机号码 */
	@Transient
	private String doctorMobile;

	/* 医生姓名 */
	@Transient
	private String doctorName;

	/* 医生所在医院 */
	@Transient
	private String doctorHospital;
	

	
	
	
	
	public double getApplyMoney() {
        return applyMoney;
	}

	public void setApplyMoney(double applyMoney) {
        this.applyMoney = applyMoney;
	}

	public String getBankRelationUuid() {
		return bankRelationUuid;
	}

	public void setBankRelationUuid(String bankRelationUuid) {
		this.bankRelationUuid = bankRelationUuid;
	}

	public double getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(double accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getDoctorMobile() {
		if (!StringUtil.isEmpty(this.userUuid)) {
			return servicestaffService.getMobileByUuid(userUuid);
		}
		return this.doctorMobile;
	}

	public void setDoctorMobile(String doctorMobile) {
		this.doctorMobile = doctorMobile;
	}

	public String getDoctorName() {
		if (!StringUtil.isEmpty(this.userUuid)) {
			return servicestaffService.getServiceStaffNameByUuid(userUuid);
		}
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorHospital() {
		if (!StringUtil.isEmpty(this.hospitalUuid)) {
			return hospitalInfoService.getHospitalNameByUuid(hospitalUuid);
		}
		return this.doctorHospital;
	}

	public void setDoctorHospital(String doctorHospital) {
		this.doctorHospital = doctorHospital;
	}

	public void setUserType(String obj) {
		this.userType = obj;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserUuid(String obj) {
		this.userUuid = obj;
	}

	public String getUserUuid() {
		return this.userUuid;
	}

	public void setMoneyType(String obj) {
		this.moneyType = obj;
	}

	public String getMoneyType() {
		return this.moneyType;
	}

	

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getHospitalUuid() {
		return hospitalUuid;
	}

	public void setHospitalUuid(String hospitalUuid) {
		this.hospitalUuid = hospitalUuid;
	}

	@Override
	public String toString() {
		return "WithdrawApplyModel [userType=" + userType
				+ ", bankRelationUuid=" + bankRelationUuid + ", userUuid="
				+ userUuid + ", moneyType=" + moneyType + ", applyMoney="
				+ applyMoney + ", applyTime=" + applyTime + ", state=" + state
				+ ", note=" + note + ", failReason=" + failReason
				+ ", successTime=" + successTime + ", userName=" + userName
				+ ", userNo=" + userNo + ", hospitalUuid=" + hospitalUuid
				+ ", accountAmount=" + accountAmount + ", doctorMobile="
				+ doctorMobile + ", doctorName=" + doctorName
				+ ", doctorHospital=" + doctorHospital + "]";
	}

}
