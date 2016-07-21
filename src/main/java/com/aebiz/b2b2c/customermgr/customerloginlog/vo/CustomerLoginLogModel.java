package com.aebiz.b2b2c.customermgr.customerloginlog.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "customer_login_log")
public class CustomerLoginLogModel extends BaseModel {
	/* 会员编号 */
	private String custoemrUuid = "";

	/* 登录时间 */
	private String loginTime = "";

	/* ip */
	private String IPName = "";

	/* mac */
	private String macName = "";

	/* 登录端 */
	private String clientType = "";

	/* pcName */
	private String pcName = "";

	/* 操作描述 */
	private String description = "";

	public void setCustoemrUuid(String obj) {
		this.custoemrUuid = obj;
	}

	public String getCustoemrUuid() {
		return this.custoemrUuid;
	}

	public void setLoginTime(String obj) {
		this.loginTime = obj;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	public void setIPName(String obj) {
		this.IPName = obj;
	}

	public String getIPName() {
		return this.IPName;
	}

	public void setMacName(String obj) {
		this.macName = obj;
	}

	public String getMacName() {
		return this.macName;
	}

	public void setClientType(String obj) {
		this.clientType = obj;
	}

	public String getClientType() {
		return this.clientType;
	}

	public void setPcName(String obj) {
		this.pcName = obj;
	}

	public String getPcName() {
		return this.pcName;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[custoemrUuid=" + this.getCustoemrUuid() + ",loginTime="
				+ this.getLoginTime() + ",IPName=" + this.getIPName()
				+ ",macName=" + this.getMacName() + ",clientType="
				+ this.getClientType() + ",pcName=" + this.getPcName()
				+ ",description=" + this.getDescription() + ",]";
	}
}
