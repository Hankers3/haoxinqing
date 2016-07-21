package com.aebiz.b2b2c.basicdata.logisticscompany.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "bascidata_logistics")
public class LogisticsCompanyModel extends BaseModel {

	/* 公司名称 */
	private String companyName;

	/* 联系人 */
	private String contractMan;

	/* 联系人电话 */
	private String contractMobile;

	/* 备注 */
	private String description;

	public void setCompanyName(String obj) {
		this.companyName = obj;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setContractMan(String obj) {
		this.contractMan = obj;
	}

	public String getContractMan() {
		return this.contractMan;
	}

	public void setContractMobile(String obj) {
		this.contractMobile = obj;
	}

	public String getContractMobile() {
		return this.contractMobile;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[companyName=" + this.getCompanyName() + ",contractMan="
				+ this.getContractMan() + ",contractMobile="
				+ this.getContractMobile() + ",description="
				+ this.getDescription() + ",]";
	}
}
