
package com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.basicdata.province.service.ProvinceService;
import com.aebiz.b2b2c.basicdata.region.service.RegionService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
/**
 * 医生基础信息表
 * @author wangbingning
 *
 */
@Entity
@Table(name="service_staff_info")
@Component
public class ServicestaffinfoModel extends BaseModel{
	/*注入城市service*/
	@Transient
	private static CityService cityService;
	@Autowired
	public  void setCityService(CityService cityService) {
		this.cityService=cityService;
	}
	
	/*注入省份service*/
	@Transient
	private static ProvinceService provinceService;
	@Autowired
	public  void setProvinceService(ProvinceService provinceService) {
		this.provinceService=provinceService;
	}
	
	/*注入县service*/
	@Transient
	private static RegionService regionService;
	@Autowired
	public  void setRegionService(RegionService regionService) {
		this.regionService=regionService;
	}
	
	/*注解文件service*/
	@Transient
	private static FileService fileService;
	
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	/*注解家政员主表信息service*/
	@Transient
	private static ServicestaffService staffService;
	
	@Autowired
	public void setStaffService(ServicestaffService staffService) {
		this.staffService = staffService;
	}
	

	/*家政员编号*/
	private String serviceStaffUuid;
	
	/*昵称*/
	private String nickName;

	/*性别 1:男，2：女 3：保密*/
	private String sex;
	
	/*生日*/
	private String birthday;
	
	/*兴趣爱好*/
	private String hobby;
	
	/*所在地省*/
	private String province;
	@Transient
	private String provinceName;
	
	/*所在地市*/
	private String city;
	
	@Transient
	private String cityName;
	
	/*所在地区*/
	private String region;
	
	@Transient
	private String regionName;
	/*具体地址*/
	private String address;
	
	/*头像*/
	private String image;
	
	/*证件号码*/
	private String certCode;
	
	/*教育程度*/
	private String education;
	
	/*所在行业*/
	private String industry;
	
	/*邮编*/
	private String zipCode;
	
	/*证件类型*/
	private String certType;
	
	/*证件照*/
	private String certImage;
	
	/*简介*/
	private String synopsis;
	
	/*从业年限*/
	private String ageLimit;
	
	/*岗位 1：家政员  2：月嫂*/
	private String serviceStaffJob;
	
	/*年龄*/
	private String age;
	
	/*工作经验*/
	private String experience;
	
	/*银行账户*/
	private String bankAccount;
	
	/*银行名称*/
	private String bankName;
	
	/*支付宝账户*/
	private String alipayAccount;
	
	/*婚姻状况*/
	private String maritalStatus;
	
	/*健康状况*/
	private String healthCondition;
	
	/*是否有精神病/职业病/传染病*/
	private String industrialDsease;
	
	/*籍贯*/
	private String nativePlace;
	
	/*医生分类*/
	private  String doctorClassify;
	
	/*职称*/
	private String professional;
	
	/*擅长领域*/
	private String territory;
	
	/*二维码*/
	private String twoCode;
	
	/*证件照1*/
	private String photoOne;
	
	/*证件照2*/
	private String photoTwo;
	
	/*证件照3*/
	private String photoThree;
	
	/*证件照4*/
	private String photoFour;
	
	/*证件照5*/
	private String photoFive;
	/*手机号*/
	@Transient
	private String moblie;

	
	/*关联的订单编号 ，用来查询订单和家政员之前距离换算用*/
	@Transient
	private String  orderMainUuid;
	
	/*图片上传路径*/
	@Transient
	private String imgUrl = "";
	
	/*图片上传路径*/
	@Transient
	private String twoCodeUrl = "";
	
	/*图片上传路径*/
	@Transient
	private String imgUrlOne = "";

	/*图片上传路径*/
	@Transient
	private String imgUrlTwo = "";
	/*图片上传路径*/
	@Transient
	private String imgUrlThree = "";
	/*图片上传路径*/
	@Transient
	private String imgUrlFour = "";
	/*图片上传路径*/
	@Transient
	private String imgUrlFive = "";
	

	public String getProvinceName() {
		if(!StringUtil.isEmpty(this.province)){
			return provinceService.getProvinceNameById(province);
		}
		return provinceName;
	}
	
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	public String getCityName() {
		if(!StringUtil.isEmpty(this.city)){
			return cityService.getCityNameById(city);
		}
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getRegionName() {
		if(!StringUtil.isEmpty(this.region)){
			return regionService.getWholeAreameById(region);
		}
		return regionName;
	}
	
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}


	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public String getHealthCondition() {
		return healthCondition;
	}


	public void setHealthCondition(String healthCondition) {
		this.healthCondition = healthCondition;
	}


	public String getIndustrialDsease() {
		return industrialDsease;
	}


	public void setIndustrialDsease(String industrialDsease) {
		this.industrialDsease = industrialDsease;
	}


	public String getNativePlace() {
		return nativePlace;
	}


	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}


	public String getBankAccount() {
		return bankAccount;
	}


	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getAlipayAccount() {
		return alipayAccount;
	}


	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}


	public String getExperience() {
		return experience;
	}


	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	public String getImgUrl() {
		if (StringUtil.isEmpty(this.image)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.image);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getImage();
		}
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getTwoCodeUrl() {
		if (StringUtil.isEmpty(this.twoCode)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.twoCode);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getTwoCode();
		}
	}

	public void setTwoCodeUrl(String twoCodeUrl) {
		this.twoCodeUrl = twoCodeUrl;
	}

	public String getImgUrlOne() {
		if (StringUtil.isEmpty(this.photoOne)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.photoOne);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getPhotoOne();
		}
	}

	public void setImgUrlOne(String imgUrlOne) {
		this.imgUrlOne = imgUrlOne;
	}

	public String getImgUrlTwo() {
		if (StringUtil.isEmpty(this.photoTwo)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.photoTwo);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getPhotoOne();
		}
	}

	public void setImgUrlTwo(String imgUrlTwo) {
		this.imgUrlTwo = imgUrlTwo;
	}

	public String getImgUrlThree() {
		if (StringUtil.isEmpty(this.photoThree)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.photoThree);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getPhotoOne();
		}
		
	}

	public void setImgUrlThree(String imgUrlThree) {
		this.imgUrlThree = imgUrlThree;
	}

	public String getImgUrlFour() {
		if (StringUtil.isEmpty(this.photoFour)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.photoFour);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getPhotoOne();
		}
	}

	public void setImgUrlFour(String imgUrlFour) {
		this.imgUrlFour = imgUrlFour;
	}

	public String getImgUrlFive() {
		if (StringUtil.isEmpty(this.photoFive)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.photoFive);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getPhotoOne();
		}
	}

	public void setImgUrlFive(String imgUrlFive) {
		this.imgUrlFive = imgUrlFive;
	}

	public String getOrderMainUuid() {
		return orderMainUuid;
	}

	public void setOrderMainUuid(String orderMainUuid) {
		this.orderMainUuid = orderMainUuid;
	}


	public String getMoblie() {
		return staffService.getMobileByUuid(this.serviceStaffUuid);
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	public void setServiceStaffUuid(String obj){
		this.serviceStaffUuid = obj;
	}
	public String getServiceStaffUuid(){
		return this.serviceStaffUuid;
	}
	
	public void setNickName(String obj){
		this.nickName = obj;
	}
	public String getNickName(){
		return this.nickName;
	}
	
	public void setSex(String obj){
		this.sex = obj;
	}
	public String getSex(){
		return this.sex;
	}
	
	public void setBirthday(String obj){
		this.birthday = obj;
	}
	public String getBirthday(){
		return this.birthday;
	}
	
	public void setHobby(String obj){
		this.hobby = obj;
	}
	public String getHobby(){
		return this.hobby;
	}
	

	
	public void setProvince(String obj){
		this.province = obj;
	}
	public String getProvince(){
		return this.province;
	}
	
	public void setCity(String obj){
		this.city = obj;
	}
	public String getCity(){
		return this.city;
	}
	
	public void setRegion(String obj){
		this.region = obj;
	}
	public String getRegion(){
		return this.region;
	}
	
	public void setAddress(String obj){
		this.address = obj;
	}
	public String getAddress(){
		return this.address;
	}
	
	public void setImage(String obj){
		this.image = obj;
	}
	public String getImage(){
		return this.image;
	}
	
	public void setCertCode(String obj){
		this.certCode = obj;
	}
	public String getCertCode(){
		return this.certCode;
	}
	
	public void setEducation(String obj){
		this.education = obj;
	}
	public String getEducation(){
		return this.education;
	}
	
	public void setIndustry(String obj){
		this.industry = obj;
	}
	public String getIndustry(){
		return this.industry;
	}
	
	public void setZipCode(String obj){
		this.zipCode = obj;
	}
	public String getZipCode(){
		return this.zipCode;
	}
	
	public void setCertType(String obj){
		this.certType = obj;
	}
	public String getCertType(){
		return this.certType;
	}
	
	public void setCertImage(String obj){
		this.certImage = obj;
	}
	public String getCertImage(){
		return this.certImage;
	}
	
	public void setSynopsis(String obj){
		this.synopsis = obj;
	}
	public String getSynopsis(){
		return this.synopsis;
	}
	
	public void setAgeLimit(String obj){
		this.ageLimit = obj;
	}
	public String getAgeLimit(){
		return this.ageLimit;
	}

	public String getServiceStaffJob() {
		return serviceStaffJob;
	}

	public void setServiceStaffJob(String serviceStaffJob) {
		this.serviceStaffJob = serviceStaffJob;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}

	


	public String getDoctorClassify() {
		return doctorClassify;
	}


	public void setDoctorClassify(String doctorClassify) {
		this.doctorClassify = doctorClassify;
	}


	public String getProfessional() {
		return professional;
	}


	public void setProfessional(String professional) {
		this.professional = professional;
	}


	public String getTerritory() {
		return territory;
	}


	public void setTerritory(String territory) {
		this.territory = territory;
	}


	public String getTwoCode() {
		return twoCode;
	}


	public void setTwoCode(String twoCode) {
		this.twoCode = twoCode;
	}


	public String getPhotoOne() {
		return photoOne;
	}


	public void setPhotoOne(String photoOne) {
		this.photoOne = photoOne;
	}


	public String getPhotoTwo() {
		return photoTwo;
	}


	public void setPhotoTwo(String photoTwo) {
		this.photoTwo = photoTwo;
	}


	public String getPhotoThree() {
		return photoThree;
	}


	public void setPhotoThree(String photoThree) {
		this.photoThree = photoThree;
	}


	public String getPhotoFour() {
		return photoFour;
	}


	public void setPhotoFour(String photoFour) {
		this.photoFour = photoFour;
	}


	public String getPhotoFive() {
		return photoFive;
	}


	public void setPhotoFive(String photoFive) {
		this.photoFive = photoFive;
	}


	@Override
	public String toString() {
		return "ServicestaffinfoModel [serviceStaffUuid=" + serviceStaffUuid
				+ ", nickName=" + nickName + ", sex=" + sex + ", birthday="
				+ birthday + ", hobby=" + hobby + ", province=" + province
				+ ", provinceName=" + provinceName + ", city=" + city
				+ ", cityName=" + cityName + ", region=" + region
				+ ", regionName=" + regionName + ", address=" + address
				+ ", image=" + image + ", certCode=" + certCode
				+ ", education=" + education + ", industry=" + industry
				+ ", zipCode=" + zipCode + ", certType=" + certType
				+ ", certImage=" + certImage + ", synopsis=" + synopsis
				+ ", ageLimit=" + ageLimit  + ", serviceStaffJob="
				+ serviceStaffJob + ", age=" + age + ", experience="
				+ experience + ", bankAccount=" + bankAccount + ", bankName="
				+ bankName + ", alipayAccount=" + alipayAccount
				+ ", maritalStatus=" + maritalStatus + ", healthCondition="
				+ healthCondition + ", industrialDsease=" + industrialDsease
				+ ", nativePlace=" + nativePlace + ", doctorClassify="
				+ doctorClassify + ", professional=" + professional
				+ ", territory=" + territory + ", twoCode=" + twoCode
				+ ", photoOne=" + photoOne + ", photoTwo=" + photoTwo
				+ ", photoThree=" + photoThree + ", photoFour=" + photoFour
				+ ", photoFive=" + photoFive + ", moblie=" + moblie + ", orderMainUuid=" + orderMainUuid + "]";
	}


		
}

