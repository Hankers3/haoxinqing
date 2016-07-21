package com.hxq.mobile.entity.visit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxcommon.repository.AbstractEntity;

/**
 * 随访方案
 */
@SuppressWarnings("serial")
@Entity(name = "visit_precept")
public class VisitPrecept extends AbstractEntity<String> {
    /* 编号 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    public String uuid;//随访方案id
    public String oper;
    public String opeTime;
    @Column(columnDefinition="default 1")
    public String delFlag;
    /**
     * 医生编号
     */
    public String serviceStaffUuid;
    /**
     * 方案名称
     */
    public String preceptName;
    /**
     * 药物不良反应处理
     */
    public String drugTherapy;
    /**
     * 其他治疗
     */
    public String sideEffects;
    public String dietGuide;
    public String sport;
    /**
     * 睡眠指导
     */
    public String sleep;
    /**
     * 随访周期
     */
    @Column(columnDefinition = "default 0")
    public Integer period;
    /**
     * 心电图检查周期
     */
    @Column(columnDefinition = "default 0")
    public Integer electrocardiogram;
    /**
     * 肾功能周期
     */
    public String renal;
    /**
     * 体重周期
     */
    @Column(columnDefinition = "default 0")
    public Integer weight;
    /**
     * 血常规周期
     */
    @Column(columnDefinition = "default 0")
    public Integer bloodRoutine;
    /**
     * 肝功能周期
     */
    @Column(columnDefinition = "default 0")
    public Integer hepatic;
    /**
     * 随访方案ID
     */
    public String visitUuid;
    /**
     * 创建方案时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="now")
    public Date createTime;
    /**
     * 随访医院uuid
     */
    public String hospitalUuid;
    /**
     * 是否推荐 0:医生自己的方案,1:系统推荐的方案,2:患者的方案
     */
    public String recommend;   
    /**
     * 自评量表
     */
    public String selfTest;
    /**
     * 医评量表
     */
    public String doctorTest;
    /**
     * 自评周期
     */
    @Column(columnDefinition = "default 0")
    public Integer selfPeriod;
    /**
     * 医评周期
     */
    @Column(columnDefinition = "default 0")
    public Integer doctorPeriod;
    /**
     * 患者id
     */
    public String customerUuid;

    public VisitPrecept() {super();}
    public VisitPrecept(String id) {
    	super();
        this.uuid = id;
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
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getServiceStaffUuid() {
		return serviceStaffUuid;
	}
	public void setServiceStaffUuid(String serviceStaffUuid) {
		this.serviceStaffUuid = serviceStaffUuid;
	}
	public String getPreceptName() {
		return preceptName;
	}
	public void setPreceptName(String preceptName) {
		this.preceptName = preceptName;
	}
	public String getDrugTherapy() {
		return drugTherapy;
	}
	public void setDrugTherapy(String drugTherapy) {
		this.drugTherapy = drugTherapy;
	}
	public String getSideEffects() {
		return sideEffects;
	}
	public void setSideEffects(String sideEffects) {
		this.sideEffects = sideEffects;
	}
	public String getDietGuide() {
		return dietGuide;
	}
	public void setDietGuide(String dietGuide) {
		this.dietGuide = dietGuide;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public String getSleep() {
		return sleep;
	}
	public void setSleep(String sleep) {
		this.sleep = sleep;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Integer getElectrocardiogram() {
		return electrocardiogram;
	}
	public void setElectrocardiogram(Integer electrocardiogram) {
		this.electrocardiogram = electrocardiogram;
	}
	public String getRenal() {
		return renal;
	}
	public void setRenal(String renal) {
		this.renal = renal;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getBloodRoutine() {
		return bloodRoutine;
	}
	public void setBloodRoutine(Integer bloodRoutine) {
		this.bloodRoutine = bloodRoutine;
	}
	public Integer getHepatic() {
		return hepatic;
	}
	public void setHepatic(Integer hepatic) {
		this.hepatic = hepatic;
	}
	public String getVisitUuid() {
		return visitUuid;
	}
	public void setVisitUuid(String visitUuid) {
		this.visitUuid = visitUuid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getHospitalUuid() {
		return hospitalUuid;
	}
	public void setHospitalUuid(String hospitalUuid) {
		this.hospitalUuid = hospitalUuid;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getSelfTest() {
		return selfTest;
	}
	public void setSelfTest(String selfTest) {
		this.selfTest = selfTest;
	}
	public String getDoctorTest() {
		return doctorTest;
	}
	public void setDoctorTest(String doctorTest) {
		this.doctorTest = doctorTest;
	}
	public Integer getSelfPeriod() {
		return selfPeriod;
	}
	public void setSelfPeriod(Integer selfPeriod) {
		this.selfPeriod = selfPeriod;
	}
	public Integer getDoctorPeriod() {
		return doctorPeriod;
	}
	public void setDoctorPeriod(Integer doctorPeriod) {
		this.doctorPeriod = doctorPeriod;
	}
	public String getCustomerUuid() {
		return customerUuid;
	}
	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
}
