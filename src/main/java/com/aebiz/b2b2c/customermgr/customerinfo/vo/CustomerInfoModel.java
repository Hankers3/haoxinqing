package com.aebiz.b2b2c.customermgr.customerinfo.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;

@Entity
@Table(name = "customer_info")
@Component
public class CustomerInfoModel extends BaseModel {

    @Transient
    private static CustomerInfoService myService;

    @Transient
    private static FileService fileService;

    /* 会员编号 */
    private String customerUuid = "";

    /* 性别 */
    private String sex = "";// 1是男；2是女。

    /* 生日 */
    private String birthday = "";
    /* 年龄 */
    private String age = "";

    /* 兴趣爱好 */
    private String hobby = "";

    /* 真实姓名 */
    private String realName = "";

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private String nickName = "";

    /* 病情描述 */
    private String illnessDescription = "";

    /* 省 */
    private String province = "";

    /* 市 */
    private String city = "";

    /* 区 */
    private String region = "";

    /* 详细地址 */
    private String address = "";
    /* 介绍人 */
    private String introduceName = "";
    /* 头像 */
    private String image = "";

    /* 婚姻状况 */
    private String marryState = "";

    /* 月收入区间 */
    private String income = "";

    /* 证件号码 */
    private String certCode = "";

    /* 教育程度 */
    private String education = "";

    /* 所在行业 */
    private String industry = "";

    /* 邮编 */
    private String zipCode = "";

    /* 证件类型 */
    private String certType = "";

    /* 备选人 */
    private String alternativeName = "";

    /* 备选人电话 */
    private String alternativePhone = "";


    /* 病程 */
    private String diseaseTime = "";

    /* 首次就诊时间 */
    private Date firstDiagnosis;

    /* 是否首发 */
    private String ifStart = "";

    /* 复发次数 */
    private Integer seizureTimes;

    /* 身高 */
    private Float height;

    /* 体重 */
    private Float weight;

    /* 近3个月使用药物 */
    private String nearlyDrugs = "";

    /* 证件类型展示名称 */
    @Transient
    private String certTypeShowName = "";

    /* 月收入区间展示数据 */
    @Transient
    private String incomeShowName = "";

    /* 得到图片路径 */
    @Transient
    private String imgUrl = "";

    /* 所在行业展示数据 */
    @Transient
    private String industryShowName = "";

    /* 教育程度展示数据 */
    @Transient
    private String educationShowName = "";

    @Autowired
    public void setMyService(CustomerInfoService myService) {
        this.myService = myService;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCustomerUuid(String obj) {
        this.customerUuid = obj;
    }

    public String getCustomerUuid() {
        return this.customerUuid;
    }

    public void setSex(String obj) {
        this.sex = obj;
    }

    public String getSex() {
        return this.sex;
    }

    public void setBirthday(String obj) {
        this.birthday = obj;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setHobby(String obj) {
        this.hobby = obj;
    }

    public String getHobby() {
        return this.hobby;
    }

    public void setRealName(String obj) {
        this.realName = obj;
    }

    public String getRealName() {
        return this.realName;
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

    public void setAddress(String obj) {
        this.address = obj;
    }

    public String getAddress() {
        return this.address;
    }

    public void setImage(String obj) {
        this.image = obj;
    }

    public String getImage() {
        return this.image;
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

    public String getImgUrl() {
        if (StringUtil.isEmpty(this.image)) {
            return "";
        }
        FileModel file = fileService.getOneFileModel(this.image);
        if (file != null && !StringUtil.isEmpty(file.getRemotePaths())) {
            return file.getRemotePaths();
        } else {
            return this.getImage();
        }
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setMarryState(String obj) {
        this.marryState = obj;
    }

    public String getMarryState() {
        return this.marryState;
    }

    public void setIncome(String obj) {
        this.income = obj;
    }

    public String getIncome() {
        return this.income;
    }

    public String getIncomeShowName() {
        return IncomeRangeEnum.getValue(this.getIncome());
    }

    public void setIncomeShowName(String incomeShowName) {
        this.incomeShowName = incomeShowName;
    }

    public void setCertCode(String obj) {
        this.certCode = obj;
    }

    public String getCertCode() {
        return this.certCode;
    }

    public void setEducation(String obj) {
        this.education = obj;
    }

    public String getEducation() {
        return this.education;
    }

    public String getEducationShowName() {
        return EducationDegreeEnum.getValue(this.getEducation());
    }

    public void setEducationShowName(String educationShowName) {
        this.educationShowName = educationShowName;
    }

    public void setIndustry(String obj) {
        this.industry = obj;
    }

    public String getIndustry() {
        return this.industry;
    }

    public String getIndustryShowName() {
        return IndustryEnum.getValue(this.getIndustry());
    }

    public void setIndustryShowName(String industryShowName) {
        this.industryShowName = industryShowName;
    }

    public void setZipCode(String obj) {
        this.zipCode = obj;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setCertType(String obj) {
        this.certType = obj;
    }

    public String getCertType() {
        return this.certType;
    }

    public String getCertTypeShowName() {
        return CertTypeEnum.getValue(this.getCertType());
    }

    public void setCertTypeShowName(String certTypeShowName) {
        this.certTypeShowName = certTypeShowName;
    }

    public String getDiseaseTime() {return diseaseTime; }

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

    public String toString() {
        return super.toString() + " , Model" + this.getClass().getName()
                + "[customerUuid=" + this.getCustomerUuid() + ",sex="
                + this.getSex() + ",birthday=" + this.getBirthday() + ",hobby="
                + this.getHobby() + ",realName=" + this.getRealName()
                + ",province=" + this.getProvince() + ",city=" + this.getCity()
                + ",region=" + this.getRegion() + ",address="
                + this.getAddress() + ",image=" + this.getImage()
                + ",marryState=" + this.getMarryState() + ",income="
                + this.getIncome() + ",certCode=" + this.getCertCode()
                + ",education=" + this.getEducation() + ",industry="
                + this.getIndustry() + ",zipCode=" + this.getZipCode()
                + ",alternativeName=" + this.getAlternativeName() + "]";
    }
}
