package com.hxq.mobile.entity.common;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 基础数据：区县
 */
@SuppressWarnings("serial")
@Entity(name="basicdata_area_region")
public class BasicdataAreaRegion extends AbstractEntity<String> {
	@Id
    public String uuid;
    public String oper;
    public String opeTime;
    public String delFlag;
    public String cityUuid;
    public String regionName;
    public String zipcode;
    public String cityCode;

	public BasicdataAreaRegion() {super();}
	public BasicdataAreaRegion(String uuid) {
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
	public String getCityUuid() {
		return cityUuid;
	}
	public void setCityUuid(String cityUuid) {
		this.cityUuid = cityUuid;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}