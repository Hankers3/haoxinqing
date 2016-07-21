package com.aebiz.b2b2c.visitprecept.visitprecept.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordModel;

/**
 * 随访方案
 *
 * @author szr
 */
@Entity
@Table(name = "visit_precept")
@Component
public class VisitPreceptModel extends BaseModel {

    /* 注入医生service */
    @Transient
    private static ServicestaffService staffService;

    @Autowired
    public void setStaffService(ServicestaffService staffService) {
        this.staffService = staffService;
    }

    /* 注入随访方案service */
    @Transient
    private static VisitRecordService visitRecordService;

    @Autowired
    public void setVisitRecordService(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }

    /**
     * 患者id
     */
    @Transient
    private String customerUuid;

    public String getCustomerUuid() {
        if (!StringUtil.isEmpty(visitUuid)) {
            VisitRecordModel vm = visitRecordService.getByUuid(visitUuid);
            if (null != vm) {
                return vm.getCustomerUuid();
            }
        }
        return customerUuid;
    }

    public void setCustomerUuid(String customerUuid) {
        this.customerUuid = customerUuid;
    }

    /**
     * 医生名
     */
    @Transient
    private String doctorName;
    /**
     * 医生电话
     */
    @Transient
    private String mobile;
    /**
     * 医生编号
     */
    private String serviceStaffUuid;
    /**
     * 方案名称
     */
    private String preceptName;
    /**
     * 药物不良反应处理
     */
    private String drugTherapy;
    /**
     * 其他治疗
     */
    private String sideEffects;

    /**
     * 随访周期
     */
    private String period;
    /**
     * 心电图检查周期
     */
    private String electrocardiogram;
    /**
     * 血常规周期
     */
    private String bloodRoutine;
    /**
     * 肝功能周期
     */
    private String hepatic;

    /**
     * 肾功能周期
     */
    private String renal;
    /**
     * 体重周期
     */
    private String weight;

    /**
     * 随访方案ID
     */
    private String visitUuid;
    /**
     * 创建方案时间
     */
    private String createTime;

    /** 相关量表 */
    private String selfTest;
    /** 量表结果 */
    private String doctorTest;
    /* 睡眠指导 */
    private String sleep;

    /**
     *  是否推荐
     */
    private  String recommend;

    /**
     * 随访医院uuid
     */
    private String hospitalUuid;

    public String getMobile() {
        if (!StringUtil.isEmpty(this.serviceStaffUuid)) {
            return staffService.getMobileByUuid(serviceStaffUuid);
        }
        return mobile;
    }

    public String getDoctorName() {
        if (!StringUtil.isEmpty(this.serviceStaffUuid)) {
            return staffService.getRealNameByUuid(this.serviceStaffUuid);

        }
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHospitalUuid() {
        return hospitalUuid;
    }

    public void setHospitalUuid(String hospitalUuid) {
        this.hospitalUuid = hospitalUuid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getVisitUuid() {
        return visitUuid;
    }

    public void setVisitUuid(String visitUuid) {
        this.visitUuid = visitUuid;
    }

    public void setServiceStaffUuid(String obj) {
        this.serviceStaffUuid = obj;
    }

    public String getServiceStaffUuid() {
        return this.serviceStaffUuid;
    }

    public void setPreceptName(String obj) {
        this.preceptName = obj;
    }

    public String getPreceptName() {
        return this.preceptName;
    }

    public void setDrugTherapy(String obj) {
        this.drugTherapy = obj;
    }

    public String getDrugTherapy() {
        return this.drugTherapy;
    }

    public void setSideEffects(String obj) {
        this.sideEffects = obj;
    }

    public String getSideEffects() {
        return this.sideEffects;
    }

    public void setSelfTest(String obj) {
        this.selfTest = obj;
    }

    public String getSelfTest() {
        return this.selfTest;
    }

    public void setDoctorTest(String obj) {
        this.doctorTest = obj;
    }

    public String getDoctorTest() {
        return this.doctorTest;
    }

    public void setSleep(String obj) {
        this.sleep = obj;
    }

    public String getSleep() {
        return this.sleep;
    }

    public void setPeriod(String obj) {
        this.period = obj;
    }

    public String getPeriod() {
        return this.period;
    }

    public String getRenal() {
        return renal;
    }

    public void setRenal(String renal) {
        this.renal = renal;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setElectrocardiogram(String obj) {
        this.electrocardiogram = obj;
    }

    public String getElectrocardiogram() {
        return this.electrocardiogram;
    }

    public void setBloodRoutine(String obj) {
        this.bloodRoutine = obj;
    }

    public String getBloodRoutine() {
        return this.bloodRoutine;
    }

    public void setHepatic(String obj) {
        this.hepatic = obj;
    }

    public String getHepatic() {
        return this.hepatic;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String toString() {
        return super.toString() + " , Model" + this.getClass().getName() + "[serviceStaffUuid="
                + this.getServiceStaffUuid() + ",preceptName=" + this.getPreceptName() + ",drugTherapy="
                + this.getDrugTherapy() + ",sideEffects=" + this.getSideEffects() + ",subjectId=" + this.getSelfTest()
                + ",doctorTest=" + this.getDoctorTest() + ",sleep=" + this.getSleep() + ",period=" + this.getPeriod()
                + ",electrocardiogram=" + this.getElectrocardiogram() + ",bloodRoutine=" + this.getBloodRoutine()
                + ",hepatic=" + this.getHepatic() +",recommend=" + this.getRecommend() + ",]";
    }
}
