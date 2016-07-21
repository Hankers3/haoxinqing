package com.aebiz.b2b2c.servicestaff.bankrelation.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 提现卡关联表
 * 
 * @author wangbingning
 */
@Entity
@Table(name = "bank_relation")
public class BankRelationModel extends BaseModel {
	/* 医生编号 */
	private String doctorUuid;
	/* 银行卡卡号 */
	private String bankCode;
	/* 所属银行 */
	private String bankName;
	/* 持卡人姓名 */
	private String realName;
	/* 创建时间 */
	private String createTime;
	/* 持卡人身份证 */
	private String ID;
	/* 备注 */
	private String note;
	/* 银行卡的Uuid */
	private String bankUuid;// CCB代表建设银行 CMB招商银行 ICBC工商银行
	/* 银行卡的类型 */
	private String cardType;// 1代表是信用卡 ；2代表是储蓄卡

	public String getBankUuid() {
		return bankUuid;
	}

	public void setBankUuid(String bankUuid) {
		this.bankUuid = bankUuid;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setDoctorUuid(String obj) {
		this.doctorUuid = obj;
	}

	public String getDoctorUuid() {
		return this.doctorUuid;
	}

	public void setBankCode(String obj) {
		this.bankCode = obj;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankName(String obj) {
		this.bankName = obj;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setRealName(String obj) {
		this.realName = obj;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[doctorUuid=" + this.getDoctorUuid() + ",bankCode="
				+ this.getBankCode() + ",bankName=" + this.getBankName()
				+ ",realName=" + this.getRealName() + ",createTime="
				+ this.getCreateTime() + ",note=" + this.getNote() + ",]";
	}
}
