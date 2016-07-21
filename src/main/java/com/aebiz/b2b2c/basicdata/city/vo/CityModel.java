package com.aebiz.b2b2c.basicdata.city.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.basicdata.province.service.ProvinceService;

@Entity
@Table(name = "basicdata_area_city")
public class CityModel extends BaseModel {
	
	/*启用状态——启用*/
	public static final String INITITATE_EBLE = "1";
	
	/*启用状态——不启用 */
	public static final String INITITATE_UNEBLE = "0";

	/* 所属省份 */
	private String provinceUuid;

	/* 城市名称 */
	private String cityName;
	
	/* 启用状态 */
	private String state;
	
	/* 经度 */
	private double longitude;
	
	/* 纬度 */
	private double latitude;
	
	
	@Transient
	private static ProvinceService provinceService;
	
	@Autowired
	public void setProvinceService(
			ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	
	@Transient
	private String provinceName = "";
	
	/* 商品的编号 */
	@Transient
	private String productUuid;
	


	public String getProductUuid() {
		return productUuid;
	}

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}

	

	
	public void setProvinceUuid(String obj) {
		this.provinceUuid = obj;
	}

	public String getProvinceUuid() {
		return this.provinceUuid;
	}

	public void setCityName(String obj) {
		this.cityName = obj;
	}

	public String getCityName() {
		return this.cityName;
	}

	public String getProvinceName() {
		return provinceService.getProvinceNameById(this.provinceUuid);
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
	
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[provinceUuid=" + this.getProvinceUuid() +
				",cityName="+ this.getCityName() +
				",state="+ this.getState() +
				",]";
	}
}
