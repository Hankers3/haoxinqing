package com.hxq.mobile.entity.message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.hxq.mobile.util.Image4App;
import com.wxcommon.repository.AbstractEntity;

/**
 * 平台消息表
 */
@SuppressWarnings("serial")
@Entity(name="inner_message")
public class InnerMessage extends AbstractEntity<String> {
	/* 消息类型 0 关注收藏的类型  1随访消息  2系统的消息   */
	@Transient
	public static final String MESSAGE_TYPE_PROMOTION = "0";
	@Transient
	public static final String MESSAGE_TYPE_ORDER = "1";
	@Transient
	public static final String MESSAGE_TYPE_SYSTEM = "2";
	/* 收件人类别 1医生  2患者 */
	@Transient
	public static final String ACCOUNT_TYPE_STORE= "1";
	@Transient
	public static final String ACCOUNT_TYPE_CUSTOMER = "2";
	/* 读取状态 0未读 1已读 */
	@Transient
	public static final String READ_STATUS_UNREAD = "0";
	@Transient
	public static final String READ_STATUS_READ = "1";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;

	public String oper;
	public String opeTime;
	public String delFlag;

	/* 信息标题 */
	public String title;

	/* 信息内容 */
	public String content;

	/* 发件人 */
	public String sendUser;

	/* 收件人 */
	public String receiveUser;

	/* 发送时间 */
	public String sendTime;

	/* 发件人删除状态 */
	@Column(columnDefinition="default 0")
	public String fjDelStatus;

	/* 读取状态 0未读 1已读 */
	@Column(columnDefinition="default 0")
	public String readStatus;

	/* 收件人删除状态 */
	@Column(columnDefinition="default 0")
	public String sjDelStatus;

	/* 消息类型 0系统消息 1随访消息 2在线咨询消息 3最近收藏我的人 */
	public String messageType;

	/* 收信人类别 0:平台 1:医生 2：患者 */
	public String accountType;

	/* 发件人头像 */
	public String userImage;

	/* 图片 */
	public String image;

	@Transient
	public Image4App[] imageUrls;

	/* 关联的订单编号 */
	public String orderUuid;

	/* 站内发送状态 0：未发送 1：已发送 */
	public String sendStatus;

	/* 消息打开类型 */
	public String messageOpenType;

	/* 发信人类别 0:平台 1:医生 2：患者 */
	public String sendType;

	/* 随访id */
	public String visitRecordUuid;

	/* 通用的type */
	public String universalType;

	/* 通用的Uuid */
	public String universalUuid;

	public InnerMessage() {super();}
	public InnerMessage(String uuid) {
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendUser() {
		return sendUser;
	}
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}
	public String getReceiveUser() {
		return receiveUser;
	}
	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getFjDelStatus() {
		return fjDelStatus;
	}
	public void setFjDelStatus(String fjDelStatus) {
		this.fjDelStatus = fjDelStatus;
	}
	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	public String getSjDelStatus() {
		return sjDelStatus;
	}
	public void setSjDelStatus(String sjDelStatus) {
		this.sjDelStatus = sjDelStatus;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getOrderUuid() {
		return orderUuid;
	}
	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getMessageOpenType() {
		return messageOpenType;
	}
	public void setMessageOpenType(String messageOpenType) {
		this.messageOpenType = messageOpenType;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getVisitRecordUuid() {
		return visitRecordUuid;
	}
	public void setVisitRecordUuid(String visitRecordUuid) {
		this.visitRecordUuid = visitRecordUuid;
	}
	public String getUniversalType() {
		return universalType;
	}
	public void setUniversalType(String universalType) {
		this.universalType = universalType;
	}
	public String getUniversalUuid() {
		return universalUuid;
	}
	public void setUniversalUuid(String universalUuid) {
		this.universalUuid = universalUuid;
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
	public Image4App[] getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(Image4App[] imageUrls) {
		this.imageUrls = imageUrls;
	}
}
