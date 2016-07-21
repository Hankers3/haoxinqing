package com.aebiz.b2b2c.servicestaff.hospitalinfo.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.basicdata.province.service.ProvinceService;
import com.aebiz.b2b2c.basicdata.region.service.RegionService;

/**
 * 醫院信息表 用來管理醫院信息數據
 * 
 * @author xueli
 * 
 */
@Entity
@Table(name = "hospital_info")
@Component
public class HospitalInfoModel extends BaseModel {
	/* 注入城市service */
	@Transient
	private static CityService cityService;

	@Autowired
	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	/* 注入省份service */
	@Transient
	private static ProvinceService provinceService;

	@Autowired
	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}

	/* 注入县service */
	@Transient
	private static RegionService regionService;

	@Autowired
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}

	/* 医院名 */
	private String hospitalName;

	/* 医院类型 1：综合 2：精神专科 */
	private String hospitalClassify;

	/* 医院级别 详见枚举 */
	private String hospitalLevel;

	/* 省 */
	private String province;

	/* 市 */
	private String city;

	/* 区县 */
	private String region;

	/* 创建时间 */
	private String createTime;

	/* 备注 */
	private String note;

	/* 详细地址 */
	private String address;

	/* 省份名 */
	@Transient
	private String provinceName;

	/* 城市名 */
	@Transient
	private String cityName;

	/* 区县名 */
	@Transient
	private String regionName;

	public String getHospitalLevel() {
		return hospitalLevel;
	}

	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}

	public String getProvinceName() {
		if (!StringUtil.isEmpty(this.province)) {
			return provinceService.getProvinceNameById(province);
		}
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		if (!StringUtil.isEmpty(this.city)) {
			return cityService.getCityNameById(city);
		}
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRegionName() {
		if (!StringUtil.isEmpty(this.region)) {
			 regionName =regionService.getWholeAreameById(region);
			 if(!StringUtil.isEmpty(regionName)){
				 return regionService.getWholeAreameById(region);
			 }else{
				 return this.region;
			 }
		}
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public void setHospitalName(String obj) {
		this.hospitalName = obj;
	}

	public String getHospitalName() {
		return this.hospitalName;
	}

	public void setHospitalClassify(String obj) {
		this.hospitalClassify = obj;
	}

	public String getHospitalClassify() {
		return this.hospitalClassify;
	}

	public void setProvince(String obj) {
		this.province = obj;
	}

	public String getProvince() {
		return this.province;
	}

	public void setCity(String obj) {
		this.city = obj;
	}

	public String getCity() {
		return this.city;
	}

	public void setRegion(String obj) {
		this.region = obj;
	}

	public String getRegion() {
		return this.region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
				+ "[hospitalName=" + this.getHospitalName()
				+ ",hospitalClassify=" + this.getHospitalClassify()
				+ ",province=" + this.getProvince() + ",city=" + this.getCity()
				+ ",region=" + this.getRegion() + ",createTime="
				+ this.getCreateTime() + ",note=" + this.getNote() + ",]";
	}
}
