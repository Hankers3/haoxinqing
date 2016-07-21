package com.aebiz.b2b2c.basicdata.region.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "basicdata_area_region")
public class RegionModel extends BaseModel {

	/* 所属城市 */
	private String cityUuid;

	/* 区县名称 */
	private String regionName;

	/* 前台显示省市名称 省>市 */
	@Transient
	private String pcityName = "";

	/* 邮编 */
	private String zipcode = "";

	/* 区号 */
	private String cityCode = "";

	public void setCityUuid(String obj) {
		this.cityUuid = obj;
	}

	public String getCityUuid() {
		return this.cityUuid;
	}

	public void setRegionName(String obj) {
		this.regionName = obj;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public String getPcityName() {
		return pcityName;
	}

	public void setPcityName(String pcityName) {
		this.pcityName = pcityName;
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

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[cityUuid=" + this.getCityUuid() + ",regionName="
				+ this.getRegionName() + ",]";
	}
}
