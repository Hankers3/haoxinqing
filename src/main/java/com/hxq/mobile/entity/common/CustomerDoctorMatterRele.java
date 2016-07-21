package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 患者提问与医生关联关系表
 */
@SuppressWarnings("serial")
@Entity(name="customer_doctor_matter_rele")
public class CustomerDoctorMatterRele extends AbstractEntity<String> {
	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
	@Column(columnDefinition="default 1")
	public Integer status;  //状态 1:添加中 2添加完成(要添加3个医生)
	//患者提问uuid
	public String consultrecordUuid;
	
	/* 医生编号 */
	public String doctorUuid;
	/* 患者编号 */
	public String customerUuid;
	/* 创建时间 */
	public String createTime;
	/* 更新时间 */
	public String updateTime; 

	public CustomerDoctorMatterRele() {super();}
	public CustomerDoctorMatterRele(String id) {
		super();
		this.uuid = id;
 	}
	
	@Override
	public String getId() {
		return null;
	}
	@Override
	public void setId(String arg0) {
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getConsultrecordUuid() {
		return consultrecordUuid;
	}
	public void setConsultrecordUuid(String consultrecordUuid) {
		this.consultrecordUuid = consultrecordUuid;
	}
	public String getDoctorUuid() {
		return doctorUuid;
	}
	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
	}
	public String getCustomerUuid() {
		return customerUuid;
	}
	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return "CustomerDoctorMatterRele [uuid=" + uuid + ", status=" + status + ", consultrecordUuid="
				+ consultrecordUuid + ", doctorUuid=" + doctorUuid + ", customerUuid=" + customerUuid + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}

	
	
}
