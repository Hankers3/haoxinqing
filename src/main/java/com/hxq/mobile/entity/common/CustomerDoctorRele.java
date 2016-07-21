package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 患者和医生关联关系表
 */
@SuppressWarnings("serial")
@Entity(name="customer_doctor_rele")
public class CustomerDoctorRele extends AbstractEntity<String> {
	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
	@Column(columnDefinition="default 1")
	public String delFlag;
	public String opeTime;
	public String oper;
	/* 分组编号  0 :默认,1:临时*/
	public String groupUuid; 
	/* 医生编号 */
	public String doctorUuid;
	/* 患者编号 */
	public String customerUuid;
	/* 创建时间 */
	public String createTime;
	
	/*聊天组id*/
	public String objectId;

	public CustomerDoctorRele() {super();}
	public CustomerDoctorRele(String id) {
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
	public String getGroupUuid() {
		return groupUuid;
	}
	public void setGroupUuid(String groupUuid) {
		this.groupUuid = groupUuid;
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
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	
}
