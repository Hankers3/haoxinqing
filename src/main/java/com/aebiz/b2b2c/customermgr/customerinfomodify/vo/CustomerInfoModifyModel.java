package com.aebiz.b2b2c.customermgr.customerinfomodify.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "customer_info_modify")
public class CustomerInfoModifyModel extends BaseModel {

	/* 手机号 */
	private String mobile = "";

	/* 邮箱 */
	private String email = "";

	/* 验证码 */
	private String validateCode = "";

	/* 会员编号 */
	private String customerUuid = "";

	public void setMobile(String obj) {
		this.mobile = obj;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setEmail(String obj) {
		this.email = obj;
	}

	public String getEmail() {
		return this.email;
	}

	public void setValidateCode(String obj) {
		this.validateCode = obj;
	}

	public String getValidateCode() {
		return this.validateCode;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[mobile=" + this.getMobile() + ",email=" + this.getEmail()
				+ ",validateCode=" + this.getValidateCode() + ",customerUuid="
				+ this.getCustomerUuid() + ",]";
	}
}
