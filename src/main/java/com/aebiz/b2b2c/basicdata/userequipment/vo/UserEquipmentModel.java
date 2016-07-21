package com.aebiz.b2b2c.basicdata.userequipment.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 用户设备表 16/01/12
 * 
 * @author szr
 */
@Entity
@Table(name = "user_equipment")
public class UserEquipmentModel extends BaseModel {
	/* 用户Uuid */
	private String userUuid;
	/* 用户类型 0:患者 1：医生 */
	private String userType;
	/* 手机类型 0:ios 1：安卓 */
	private String mobileType;
	/* 设备号 */
	private String deviceNumber;
	/* 创建时间 */
	private String createTime;

	public void setUserUuid(String obj) {
		this.userUuid = obj;
	}

	public String getUserUuid() {
		return this.userUuid;
	}

	public void setUserType(String obj) {
		this.userType = obj;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setMobileType(String obj) {
		this.mobileType = obj;
	}

	public String getMobileType() {
		return this.mobileType;
	}

	public void setDeviceNumber(String obj) {
		this.deviceNumber = obj;
	}

	public String getDeviceNumber() {
		return this.deviceNumber;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName() + "[userUuid=" + this.getUserUuid()
				+ ",userType=" + this.getUserType() + ",mobileType=" + this.getMobileType() + ",deviceNumber="
				+ this.getDeviceNumber() + ",createTime=" + this.getCreateTime() + ",]";
	}
}
