package com.hxq.mobile.entity.visit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 随访申请
 */
@SuppressWarnings("serial")
@Entity(name = "visit_apply")
public class VisitApply extends AbstractEntity<String> {
    /* 编号 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    public String uuid;//申请医生随访id
    public String oper;
    /* 操作时间 */
    public String opeTime;
    @Column(columnDefinition="default 1")
    public String delFlag;
    /* 患者编号 */
    public String customerUuid;
    /* 医生编号 */
    public String serviceStaffUuid;
    /* 创建时间 */
    public String createTime;
    /* 随访方案编号 */
    public String visitPreceptUuid;
    /* 拒绝理由 */
    public String refuseReason;
    /* 随访申请状态  0:未查看 1：允许 2：拒绝*/
    @Column(columnDefinition = "default 0")
    public String applyState;
    /* 随访表单编号 */
    public String visitRecordUuid;
    /*症状表现和既往治疗经过*/
    public String symptoms;
    /* 申请图像 */
    public String images;

    public VisitApply() {}
    public VisitApply(String id) {
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
	public String getCustomerUuid() {
		return customerUuid;
	}
	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
	public String getServiceStaffUuid() {
		return serviceStaffUuid;
	}
	public void setServiceStaffUuid(String serviceStaffUuid) {
		this.serviceStaffUuid = serviceStaffUuid;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getVisitPreceptUuid() {
		return visitPreceptUuid;
	}
	public void setVisitPreceptUuid(String visitPreceptUuid) {
		this.visitPreceptUuid = visitPreceptUuid;
	}
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	public String getApplyState() {
		return applyState;
	}
	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}
	public String getVisitRecordUuid() {
		return visitRecordUuid;
	}
	public void setVisitRecordUuid(String visitRecordUuid) {
		this.visitRecordUuid = visitRecordUuid;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
