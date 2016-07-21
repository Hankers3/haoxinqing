package com.hxq.mobile.entity.visit;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.wxcommon.repository.AbstractEntity;

/**
 * 重要医嘱/随访方案药物/
 *
 */
@SuppressWarnings("serial")
@Entity(name="doctor_advice_main")
public class DoctorAdviceMain extends AbstractEntity<String> {
	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
	/* 医生编号 */
	public String serviceStaffUuid;
	/*患者编号*/
	public String customerUuid;
	//药物不良反应处理
	public String drugReaction;
	//随访方案Uuid
	public String visitPreceptUuid;
	/* 创建时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date createTime;
	/* 其它治疗方案 */
	public String cureNote;
	/*医嘱是否过期 0：未过期 1：已过期  */
	@Column(columnDefinition="default 0")
	public String delFlag;
	/* 药物治疗方案 */
	@Transient
	public ArrayList<DoctorAdvice> child = null;

	public DoctorAdviceMain() {super();}
	public DoctorAdviceMain(String uuid) {
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
	public String getServiceStaffUuid() {
		return serviceStaffUuid;
	}
	public void setServiceStaffUuid(String serviceStaffUuid) {
		this.serviceStaffUuid = serviceStaffUuid;
	}
	public String getCustomerUuid() {
		return customerUuid;
	}
	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
	public String getDrugReaction() {
		return drugReaction;
	}
	public void setDrugReaction(String drugReaction) {
		this.drugReaction = drugReaction;
	}
	public String getVisitPreceptUuid() {
		return visitPreceptUuid;
	}
	public void setVisitPreceptUuid(String visitPreceptUuid) {
		this.visitPreceptUuid = visitPreceptUuid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCureNote() {
		return cureNote;
	}
	public void setCureNote(String cureNote) {
		this.cureNote = cureNote;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public ArrayList<DoctorAdvice> getChild() {
		return child;
	}
	public void setChild(ArrayList<DoctorAdvice> child) {
		this.child = child;
	}
}
