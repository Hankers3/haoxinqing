package com.aebiz.b2b2c.basicdata.region.vo;

public class RegionQueryModel extends RegionModel {

	/* 通过省份ID进行查询 */
	private String provinceUuid;

	public String getProvinceUuid() {
		return provinceUuid;
	}

	public void setProvinceUuid(String provinceUuid) {
		this.provinceUuid = provinceUuid;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString();
	}
}
