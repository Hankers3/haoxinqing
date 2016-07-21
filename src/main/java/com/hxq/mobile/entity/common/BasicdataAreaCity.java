package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 基础数据市
 */
@SuppressWarnings("serial")
@Entity(name="basicdata_area_city")
public class BasicdataAreaCity extends AbstractEntity<String> {
	@Id
    public String uuid;
    public String oper;
    public String opeTime;
    public String delFlag;
    public String provinceUuid;
    public String cityName;
    @Column(columnDefinition="default 0")
    public Double latitude;
    @Column(columnDefinition="default 0")
    public Double longitude;
    public String state;

	public BasicdataAreaCity() {super();}
	public BasicdataAreaCity(String uuid) {
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
	public String getProvinceUuid() {
		return provinceUuid;
	}
	public void setProvinceUuid(String provinceUuid) {
		this.provinceUuid = provinceUuid;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}