package com.aebiz.b2b2c.customermgr.customerfrozenlog.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "customer_frozen_log")
public class CustomerFrozenLogModel extends BaseModel {

	/* 1表示冻结，0表示解冻 */
	public static final String FROZEN_OPER_FREZON = "1";
	public static final String FROZEN_OPER_UNFREZON = "0";

	/* 会员编号 */
	private String CustomerUuid = "";

	/* 操作类型分为解冻操作和冻结操作 */
	private String operType = "";

	/* 冻结原因：例如是账户被盗冻结或者其他原因 */
	private String frozenType = "";

	/* 备注 */
	private String note = "";

	public void setCustomerUuid(String obj) {
		this.CustomerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.CustomerUuid;
	}

	public void setOperType(String obj) {
		this.operType = obj;
	}

	public String getOperType() {
		return this.operType;
	}

	public void setFrozenType(String obj) {
		this.frozenType = obj;
	}

	public String getFrozenType() {
		return this.frozenType;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[CustomerUuid=" + this.getCustomerUuid() + ",operType="
				+ this.getOperType() + ",frozenType=" + this.getFrozenType()
				+ ",note=" + this.getNote() + ",]";
	}
}
