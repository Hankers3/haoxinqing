
package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.util.ObjectUtils;

/**
 * 医生基础信息表
 */
@SuppressWarnings("serial")
@Entity(name = "service_staff_info")
public class ServiceStaffInfo extends AbstractEntity<String> {
    /* 编号 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    public String uuid;
    @Column(columnDefinition="default 1")
    public String delFlag;
    public String opeTime;
    public String oper;
    /*具体地址*/
    public String address;
    /*年龄*/
    public String age;
    /*从业年限*/
    public String ageLimit;
    /*生日*/
    public String birthday;
    /*证件号码*/
    public String certCode;
    /*证件照*/
    public String certImage;
    /*证件类型*/
    public String certType;
    /*所在地市*/
    public String city;
    /*所在地市名字*/
    @Transient
    public String cityName;
    /*教育程度*/
    public String education;
    /*兴趣爱好*/
    public String hobby;
    /*头像*/
    public String image;
    /*所在行业*/
    public String industry;
    /*昵称*/
    public String nickName;
    /*所在地省*/
    public String province;
    /*所在省的名字*/
    @Transient
    public String provinceName;
    /*所在地区*/
    public String region;
    /*所在地区名字*/
    @Transient
    public String regionName;
    /*岗位 1：家政员  2：月嫂*/
    public String serviceStaffJob;
    /*医生编号*/
    public String serviceStaffUuid;
    /*性别 1:男，2：女 3：保密*/
    public String sex;
    /*简介*/
    public String synopsis;
    /*邮编*/
    public String zipCode;
    /*工作经验*/
    public String experience;
    /*医生分类*/
    public String doctorClassify;
    /*职称*/
    public String professional;
    /*擅长领域*/
    public String territory;
    /*二维码*/
    public String twoCode;
    /*证件照1*/
    public String photoOne;
    /*证件照2*/
    public String photoTwo;
    /*证件照3*/
    public String photoThree;
    /*证件照4*/
    public String photoFour;
    /*证件照5*/
    public String photoFive;
    /*支付宝账户*/
    public String alipayAccount;
    /*银行账户*/
    public String bankAccount;
    /*银行名称*/
    public String bankName;
    /*健康状况*/
    public String healthCondition;
    /*是否有精神病/职业病/传染病*/
    public String industrialDsease;
    /*婚姻状况*/
    public String maritalStatus;
    /*籍贯*/
    public String nativePlace;

    public ServiceStaffInfo() {super();}
    public ServiceStaffInfo(String uuid) {
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
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAgeLimit() {
		return ageLimit;
	}
	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCertCode() {
		return certCode;
	}
	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}
	public String getCertImage() {
		return certImage;
	}
	public void setCertImage(String certImage) {
		this.certImage = certImage;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getServiceStaffJob() {
		return serviceStaffJob;
	}
	public void setServiceStaffJob(String serviceStaffJob) {
		this.serviceStaffJob = serviceStaffJob;
	}
	public String getServiceStaffUuid() {
		return serviceStaffUuid;
	}
	public void setServiceStaffUuid(String serviceStaffUuid) {
		this.serviceStaffUuid = serviceStaffUuid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getDoctorClassify() {
		return doctorClassify;
	}
	public void setDoctorClassify(String doctorClassify) {
		this.doctorClassify = doctorClassify;
	}

	public String getProfessional() {
		if(!ObjectUtils.isEmpty(professional)){
			if(professional.equals("1")){
				professional = "主任医师";
			}else if(professional.equals("2")){
				professional = "副主任医师";
			}else if(professional.equals("3")){
				professional = "主治医师";
			}else if(professional.equals("4")){
				professional = "住院医师";
			}else if(professional.equals("5")){
				professional = "助理医师";
			}else if(professional.equals("6")){
				professional = "实习医师";
			}else if(professional.equals("7")){
				professional = "护师（士）";
	  		}else if(professional.equals("8")){
	  			professional = "心理咨询师";
	  		}else if(professional.equals("9")){
	  			professional = "社工师";
	  		}else if(professional.equals("10")){
	  			professional = "药剂师";
	  		}
		}
	
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
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
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
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	
}