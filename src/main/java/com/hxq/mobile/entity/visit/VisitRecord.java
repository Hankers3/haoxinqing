package com.hxq.mobile.entity.visit;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.wxcommon.repository.AbstractEntity;

/**
 * 随访记录
 */
@SuppressWarnings("serial")
@Entity(name = "visit_record")
public class VisitRecord extends AbstractEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    public String uuid;
    @Column(columnDefinition="default 1")
    public String delflag;
    public String opetime;
    public String oper;
    /**
     * 创建时间
     */
    @GeneratedValue(strategy=GenerationType.AUTO, generator="now")
    public Date createTime;
    /**
     * 患者id
     */
    public String customerUuid;
    /**
     * 随访方案id
     */
    public String preceptUuid;
    /**
     * 医生id
     */
    public String serviceStaffUuid;
    public String visitUuid;
    public String refuseReason;
    /**
     * 医生查看状态：0未读，1已读
     */
    @Column(columnDefinition = "default 0")
    public String applyState;
    public String visitType;
    /**
     * 填写状态
     */
    @Column(columnDefinition = "default 0")
    public String visitState;

    /**
     * 病情变化
     */
    @Transient
    public Map<String, Object> illnessRecord = null;
    /**
     * 服药记录
     */
    @Transient
    public List<Map<String, Object>> doctorAdvice = null;
    /**
     * 药物不良反应
     */
    @Transient
    public Map<String, Object> drugReaction = null;
    /**
     * 随眠情况
     */
    @Transient
    public Map<String, Object> sleep = null;
    /**
     * 进食情况
     */
    @Transient
    public Map<String, Object> eat = null;
    /**
     * 其他情况
     */
    @Transient
    public Map<String, Object> other = null;
    /**
     * 其他的检查结果
     */
    @Transient
    public List<Map<String, Object>> checkResult = null;
    /**
     * 体重记录
     */
    @Transient
    public Map<String, Object> weight = null;

    public VisitRecord() {
        super();
    }

    public VisitRecord(String uuid) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCustomerUuid() {
        return customerUuid;
    }

    public void setCustomerUuid(String customerUuid) {
        this.customerUuid = customerUuid;
    }

    public String getPreceptUuid() {
        return preceptUuid;
    }

    public void setPreceptUuid(String preceptUuid) {
        this.preceptUuid = preceptUuid;
    }

    public String getServiceStaffUuid() {
        return serviceStaffUuid;
    }

    public void setServiceStaffUuid(String serviceStaffUuid) {
        this.serviceStaffUuid = serviceStaffUuid;
    }

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    public String getVisitState() {
        return visitState;
    }

    public void setVisitState(String visitState) {
        this.visitState = visitState;
    }

    public Map<String, Object> getIllnessRecord() {
        return illnessRecord;
    }

    public void setIllnessRecord(Map<String, Object> illnessRecord) {
        this.illnessRecord = illnessRecord;
    }

    public Map<String, Object> getDrugReaction() {
        return drugReaction;
    }

    public void setDrugReaction(Map<String, Object> drugReaction) {
        this.drugReaction = drugReaction;
    }

    public List<Map<String, Object>> getDoctorAdvice() {
        return doctorAdvice;
    }

    public void setDoctorAdvice(List<Map<String, Object>> doctorAdvice) {
        this.doctorAdvice = doctorAdvice;
    }

    public Map<String, Object> getSleep() {
        return sleep;
    }

    public void setSleep(Map<String, Object> sleep) {
        this.sleep = sleep;
    }

    public Map<String, Object> getEat() {
        return eat;
    }

    public void setEat(Map<String, Object> eat) {
        this.eat = eat;
    }

    public Map<String, Object> getOther() {
        return other;
    }

    public void setOther(Map<String, Object> other) {
        this.other = other;
    }

    public List<Map<String, Object>> getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(List<Map<String, Object>> checkResult) {
        this.checkResult = checkResult;
    }

    public Map<String, Object> getWeight() {
        return weight;
    }

    public void setWeight(Map<String, Object> weight) {
        this.weight = weight;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getOpetime() {
        return opetime;
    }

    public void setOpetime(String opetime) {
        this.opetime = opetime;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getVisitUuid() {
        return visitUuid;
    }

    public void setVisitUuid(String visitUuid) {
        this.visitUuid = visitUuid;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }
}
