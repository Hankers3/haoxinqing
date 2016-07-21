package com.aebiz.b2b2c.cms.message.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 在线咨询消息表 12/31
 * 
 * @author szr
 * 
 */
@Entity
@Table(name = "message")
public class MessageModel extends BaseModel {

	/* 消息内容 */
	private String sendContent;

	/* 消息来源 1：患者 2：医生 */
	private String sourceId;

	/* 发送状态  1：已发送 2：未发送  */
	private String status;

	/* 发送时间 */
	private String sendTime;

	/* 医生id */
	private String doctorUuid;

	/* 患者id */
	private String customerUuid;

	/* 在线咨询id */
	private String consultRecordUuid;

	/* 图片 */
	private String image;

	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
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

	public String getConsultRecordUuid() {
		return consultRecordUuid;
	}

	public void setConsultRecordUuid(String consultRecordUuid) {
		this.consultRecordUuid = consultRecordUuid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "MessageModel [sendContent=" + sendContent + ", sourceId="
				+ sourceId + ", status=" + status + ", sendTime=" + sendTime
				+ ", doctorUuid=" + doctorUuid + ", customerUuid="
				+ customerUuid + ", consultRecordUuid=" + consultRecordUuid
				+ ", image=" + image + "]";
	}

}
