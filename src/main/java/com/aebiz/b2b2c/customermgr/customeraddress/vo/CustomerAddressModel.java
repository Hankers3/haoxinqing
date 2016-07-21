package com.aebiz.b2b2c.customermgr.customeraddress.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;

@Entity
@Table(name = "customer_address")
@Component
public class CustomerAddressModel extends BaseModel {
	
	/**
	 * 会员信息
	 */
	@Transient
	public static CustomerService customerService ;
	@Autowired
	public  void setCustomerService(CustomerService cuService) {
		this.customerService = cuService;
	}
	@Transient
	private static CityService cityService;
	@Autowired
	public void setCityService(CityService cService) {
		this.cityService = cService;
	}
	/* 会员收获默认地址：1表示默认，0表示不是默认 */
	@Transient
	public static final String CUSTOMERADDRESS_ISDEFAULT_YES = "1";

	/* 会员收获默认地址：1表示默认，0表示不是默认 */
	@Transient
	public static final String CUSTOMERADDRESS_ISDEFAULT_NO = "0";
	
	/* 会员编号 */
	private String customerUuid = "";

	/* 收货人 */
	private String consignee = "";

	/* 省 */
	private String province = "";

	/* 市 */
	private String city = "";

	/* 区 */
	private String region = "";

	/* 详细地址 */
	private String address = "";

	/* 地址别名 */
	private String alias = "";

	/* 邮政编码 */
	private String zipcode = "";

	/* 手机号码 */
	private String mobile = "";

	/* 邮箱 */
	private String email = "";

	/* 固定电话 */
	private String telephone = "";

	/* 是否默认 */
	private String isDefault = "";

	/* 小区名称 */
	private String community = "";

	/* 经度 */
	private double longitude = 0.0;

	/* 纬度 */
	private double latitude = 0.0;
	
	/* 联系人姓名 */
	private String linkName = "";
	
	/*会员姓名*/
	@Transient
	private String customerName = "" ;
	/*市姓名*/
	@Transient
	private String cityName = "" ;
	
	
	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getCityName() {
		return cityService.getCityNameById(this.city);
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCustomerName() {
		return customerService.getCustomerNameByCustomerUuid(this.customerUuid);
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
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

	@Override
	public String toString() {
		return "CustomerAddressModel [customerUuid=" + customerUuid
				+ ", consignee=" + consignee + ", province=" + province
				+ ", city=" + city + ", region=" + region + ", address="
				+ address + ", alias=" + alias + ", zipcode=" + zipcode
				+ ", mobile=" + mobile + ", email=" + email + ", telephone="
				+ telephone + ", community=" + community + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}

}
