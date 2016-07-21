package com.hxq.mobile.entity.message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 平台消息通知表
 */
@SuppressWarnings("serial")
@Entity(name="inner_message_notice")
public class InnerMessageNotice extends AbstractEntity<String>{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;

	public String delFlag;
	public String opeTime;
	public String oper;

	/* 消息编号 */
	public String messageUuid;

	/* 阅读状态 */
	@Column(columnDefinition="default 0")
	public String readStatus;

	/* 接收人编号 */
	public String userId;

	public InnerMessageNotice() {super();}
	public InnerMessageNotice(String uuid) {
		super();
		this.uuid = uuid;
	}

	@Override
	public String getId() {return uuid;}
	@Override
	public void setId(String id) {this.uuid = id;}

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getMessageUuid() {
		return messageUuid;
	}
	public void setMessageUuid(String messageUuid) {
		this.messageUuid = messageUuid;
	}
	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
}
