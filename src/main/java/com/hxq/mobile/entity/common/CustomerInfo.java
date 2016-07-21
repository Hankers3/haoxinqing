package com.hxq.mobile.entity.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;



import com.wxcommon.repository.AbstractEntity;

/**
 * 患者基本信息
 */
@SuppressWarnings("serial")
@Entity(name="customer_info")
public class CustomerInfo extends AbstractEntity<String> {
	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
    public String uuid;
	@Column(columnDefinition="default 1")
	public String delFlag;
	public String opeTime;
	public String oper;
    /* 详细地址 */
    public String address;
    /* 生日 */
    public String birthday;
    /* 证件号码 */
    public String certCode;
    /* 证件类型 */
    public String certType;
    /* 会员编号 */
    public String customerUuid;
    /* 真实姓名 */
    public String realName;
    /* 教育程度 */
    public String education;
    /* 兴趣爱好 */
    public String hobby;
    /* 头像 */
    public String image;
    /* 月收入区间 */
    public String income;
    /* 所在行业 */
    public String industry;
    /* 婚姻状况 */
    public String marryState;
    /* 昵称 */
    public String nickName;
    /* 省 */
    public String province;
    /* 市 */
    public String city;
    /* 区 */
    public String region;
    /* 性别：1是男；2是女 */
    public String sex;
    /* 邮编 */
    public String zipCode;
    /* 备选人 */
    public String alternativeName;
    /* 备选人电话 */
    public String alternativePhone;
    /* 年龄 */
    public String age;
    /* 介绍人 */
    public String introduceName;
    /* 病情描述 */
    public String illnessDescription;
    /* 病程 */
    public String diseaseTime;
    /* 首次就诊时间 */
    public Date firstDiagnosis;
    /* 是否首发 */
    public String ifStart;
    /* 复发次数 */
	@Column(columnDefinition="default 0")
    public Integer seizureTimes;
    /* 身高 */
	@Column(columnDefinition="default 0")
    public Float height;
    /* 体重 */
	@Column(columnDefinition="default 0")
    public Float weight;
    /* 近3个月使用药物 */
    public String nearlyDrugs;

    /* 证件类型展示名称 */
    @Transient
    public String certTypeShowName;

    /* 月收入区间展示数据 */
    @Transient
    public String incomeShowName;

    /* 所在行业展示数据 */
    @Transient
    public String industryShowName;

    /* 教育程度展示数据 */
    @Transient
    public String educationShowName;

	public CustomerInfo() {super();}
	public CustomerInfo(String uuid) {
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

	public String getCertTypeShowName() {
	    return com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.CertTypeEnum.getValue(this.getCertType());
	}
	public String getIncomeShowName() {
	    return com.aebiz.b2b2c.customermgr.customerinfo.vo.IncomeRangeEnum.getValue(this.getIncome());
	}
	public String getIndustryShowName() {
	    return com.aebiz.b2b2c.customermgr.customerinfo.vo.IndustryEnum.getValue(this.getIndustry());
	}
	public String getEducationShowName() {
	    return com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.EducationDegreeEnum.getValue(this.getEducation());
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
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCustomerUuid() {
		return customerUuid;
	}
	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getMarryState() {
		return marryState;
	}
	public void setMarryState(String marryState) {
		this.marryState = marryState;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAlternativeName() {
		return alternativeName;
	}
	public void setAlternativeName(String alternativeName) {
		this.alternativeName = alternativeName;
	}
	public String getAlternativePhone() {
		return alternativePhone;
	}
	public void setAlternativePhone(String alternativePhone) {
		this.alternativePhone = alternativePhone;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getIntroduceName() {
		return introduceName;
	}
	public void setIntroduceName(String introduceName) {
		this.introduceName = introduceName;
	}
	public String getIllnessDescription() {
		return illnessDescription;
	}
	public void setIllnessDescription(String illnessDescription) {
		this.illnessDescription = illnessDescription;
	}
	public String getDiseaseTime() {
		return diseaseTime;
	}
	public void setDiseaseTime(String diseaseTime) {
		this.diseaseTime = diseaseTime;
	}
	public Date getFirstDiagnosis() {
		return firstDiagnosis;
	}
	public void setFirstDiagnosis(Date firstDiagnosis) {
		this.firstDiagnosis = firstDiagnosis;
	}
	public String getIfStart() {
		return ifStart;
	}
	public void setIfStart(String ifStart) {
		this.ifStart = ifStart;
	}
	public Integer getSeizureTimes() {
		return seizureTimes;
	}
	public void setSeizureTimes(Integer seizureTimes) {
		this.seizureTimes = seizureTimes;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public String getNearlyDrugs() {
		return nearlyDrugs;
	}
	public void setNearlyDrugs(String nearlyDrugs) {
		this.nearlyDrugs = nearlyDrugs;
	}
	public void setCertTypeShowName(String certTypeShowName) {
		this.certTypeShowName = certTypeShowName;
	}
	public void setIncomeShowName(String incomeShowName) {
		this.incomeShowName = incomeShowName;
	}
	public void setIndustryShowName(String industryShowName) {
		this.industryShowName = industryShowName;
	}
	public void setEducationShowName(String educationShowName) {
		this.educationShowName = educationShowName;
	}
}