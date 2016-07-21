package com.aebiz.b2b2c.basicdata.city.vo;

import org.springframework.stereotype.Component;

@Component
public class CityQueryModel extends CityModel {
	private String createTime;

	private String productUuid ;
	
	/*//查询省份
	private String  provinceUuid1;

	public String getProvinceUuid1() {
		return provinceUuid1;
	}

	public void setProvinceUuid1(String provinceUuid1) {
		this.provinceUuid1 = provinceUuid1;
	}
*/
	public String getProductUuid() {
		return productUuid;
	}

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[createTime=" + this.getCreateTime()
				+ " ,[productUuid=" + this.getProductUuid() +
				",]";
	}
	
	
}
