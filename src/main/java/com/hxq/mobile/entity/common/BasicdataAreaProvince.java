package com.hxq.mobile.entity.common;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 基础数据省
 */
@SuppressWarnings("serial")
@Entity(name="basicdata_area_province")
public class BasicdataAreaProvince extends AbstractEntity<String> {
	@Id
	public String uuid;
	public String oper;
	public String opeTime;
	public String delFlag;
	public String provinceName;
	public String state;

	public BasicdataAreaProvince() {super();}
	public BasicdataAreaProvince(String uuid) {
		super();
		this.uuid = uuid;
	}
	@Override
	public String getId() {
		return uuid;
	}
	@Override
	public void setId(String id) {
		this.uuid = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}