package com.aebiz.b2b2c.order.ordermainaddress.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.basicdata.city.service.CityService;

@Entity
@Table(name = "order_main_address")
@Component
public class OrderMainAddressModel extends BaseModel {
	/*訂單關聯家政員信息service*/
	@Transient
	private static CityService  cityService;

	@Autowired
	public void setCityService(CityService  cityService) {
		this.cityService = cityService;
	}
	
	
	/* 收货人 */
	private String name;

	/* 邮编 */
	private String postCode;

	/* 地址 */
	private String address;

	/* 手机 */
	private String mobile;

	/* 电话 */
	private String tel;

	/* 省 */
	private String province;

	/* 市 */
	private String city;

	/* 区县 */
	private String region;

	/* 省市区名称，保存的时候将中文名保存进来，减少对基础数据库的调用 */
	private String regionName;

	/* 订单编号 */
	private String orderMainUuid;

	/* 服务时间 */
	private String  serviceTime;
	
	/* 备选人 */
	private String alternative;
	
	/* 备选人电话 */
	private String alternativePhone;
	
	/* 备注*/
	private String note;
	
	/* 经度*/
	private double longitude;
	
	/* 纬度*/
	private double latitude;
	
	/*提交时间*/
	private String commitTime;
	
	/*抢单开始时间*/
	private String startTime;
	
	/*确认时间*/
	private String affirmTime;
	
	/*上门时间*/
	private String dropInTime;
	
	/*丈量时间*/
	private String measureTime;
	
	/*工作时间*/
	private String workTime;
	
	/*完成时间*/
	private String completeTime;
	
	/*代付时间*/
	private String agentTime;
	
	/*上门完成时间*/
	private String doorinEndtime;
	
	/*小区名称*/
	private String community;


	/*地址详情*/
	private String addressDetail;
	
	/*地址編號*/
	private String addressId;
	
	
	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getDoorinEndtime() {
		return doorinEndtime;
	}

	public void setDoorinEndtime(String doorinEndtime) {
		this.doorinEndtime = doorinEndtime;
	}


	@Transient
	private String cityName;
	
	
	
	public String getCityName() {
		return cityService.getCityNameById(this.city);
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getAffirmTime() {
		return affirmTime;
	}

	public void setAffirmTime(String affirmTime) {
		this.affirmTime = affirmTime;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getAgentTime() {
		return agentTime;
	}

	public void setAgentTime(String agentTime) {
		this.agentTime = agentTime;
	}

	public String getDropInTime() {
		return dropInTime;
	}

	public void setDropInTime(String dropInTime) {
		this.dropInTime = dropInTime;
	}

	public String getMeasureTime() {
		return measureTime;
	}

	public void setMeasureTime(String measureTime) {
		this.measureTime = measureTime;
	}

	public String getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getAlternative() {
		return alternative;
	}

	public void setAlternative(String alternative) {
		this.alternative = alternative;
	}

	public String getAlternativePhone() {
		return alternativePhone;
	}

	public void setAlternativePhone(String alternativePhone) {
		this.alternativePhone = alternativePhone;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setName(String obj) {
		this.name = obj;
	}

	public String getName() {
		return this.name;
	}

	public void setPostCode(String obj) {
		this.postCode = obj;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setAddress(String obj) {
		this.address = obj;
	}

	public String getAddress() {
		return this.address;
	}

	public void setMobile(String obj) {
		this.mobile = obj;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setTel(String obj) {
		this.tel = obj;
	}

	public String getTel() {
		return this.tel;
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

	public String getOrderMainUuid() {
		return orderMainUuid;
	}

	public void setOrderMainUuid(String orderMainUuid) {
		this.orderMainUuid = orderMainUuid;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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
				+ "[name=" + this.getName() + ",postCode=" + this.getPostCode()
				+ ",address=" + this.getAddress() + ",mobile="
				+ this.getMobile() + ",tel=" + this.getTel() + ",province="
				+ this.getProvince() + ",city=" + this.getCity() 
				+ ",region="+ this.getRegion() 
				+ ",serviceTime="+ this.getServiceTime() 
				+ ",alternative="+ this.getAlternative()
				+ ",alternativePhone="+ this.getAlternativePhone()
				+ ",]";
	}
}
