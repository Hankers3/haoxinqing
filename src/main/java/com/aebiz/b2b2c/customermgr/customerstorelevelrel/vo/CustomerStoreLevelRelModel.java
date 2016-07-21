package com.aebiz.b2b2c.customermgr.customerstorelevelrel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "customer_store_level_rel")
public class CustomerStoreLevelRelModel extends BaseModel {

	/* 商户编号 */
	private String storeUuid = "";

	/* 会员编号 */
	private String customerUuid = "";

	/* 商户等级编号 */
	private String storeLevelUuid = "";

	public void setStoreUuid(String obj) {
		this.storeUuid = obj;
	}

	public String getStoreUuid() {
		return this.storeUuid;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setStoreLevelUuid(String obj) {
		this.storeLevelUuid = obj;
	}

	public String getStoreLevelUuid() {
		return this.storeLevelUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[storeUuid=" + this.getStoreUuid() + ",customerUuid="
				+ this.getCustomerUuid() + ",storeLevelUuid="
				+ this.getStoreLevelUuid() + ",]";
	}
}
