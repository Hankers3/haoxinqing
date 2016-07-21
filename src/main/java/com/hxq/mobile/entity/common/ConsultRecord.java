package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.wxcommon.repository.AbstractEntity;

/**
 * 咨询记录表 记录咨询记录的信息
 * 
 * 
 */
@SuppressWarnings("serial")
@Entity(name="consult_record")
public class ConsultRecord extends AbstractEntity<String>  {
	/* 咨询类型 在线咨询即图文咨询 1电话咨询 2预约加号 */
	@Transient
	public static final String TYPE_ONLINE = "0";
	@Transient
	public static final String TYPE_TEL = "1";
	@Transient
	public static final String TYPE_ORDER = "2";

	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
    public String uuid;
	/* 在线咨询几次 */
	@Column(columnDefinition="default 0")
	public Integer num;
	/* 患者编号 */
	public String customerUuid;
	/* 医生编号 */
	public String doctorUuid;
	/* 标签的编号 */
	public String tagsUuid;
	/* 内容 */
	public String content;
	/* 图片 */
	public String picture;
	/* 图片 */
	public String picture1;
	/* 图片 */
	public String picture2;
	/* 图片 */
	public String picture3;
	/* 图片 */
	public String picture4;
	/* 咨询时间 */
	public String createTime;
	/* 咨询j*/
	public String endTime;
	/* 类型 */
	public String consultType;
	/* 拒绝理由 */
	public String refuseReason;
	/* 加号管理：医生审核状态   0: 未审核 1:审核 2:审核未通过 */
	@Column(columnDefinition="default 0")
	public String dealState; // 其他： 0：未处理。1：已处理 
	/* 回复 */
	@Column(columnDefinition="default 0")
	public String reply; // 0: 未回复 1：回复
	/*平台审核  审核状态 */
	@Column(columnDefinition="default 0")
	public String state; // 0: 未审核 1：审核 2：审核未通过 加号管理时，作为平台审核
	/* 审核类型 */
	@Column(columnDefinition="default 0")
	public String exam;  // 0：平台审核 1：医生审核
	/* 电话咨询编号 */
	public String teleCounUuid;
	/* 回复内容字段 */
	public String replyContent;
	/* 时间类型1：全天 ,2：上午,3：下午 */
	@Column(columnDefinition="default 0")
	public String timeType;
	/* 加号：就诊时间 */
	public String seeDoctorTime;
	/* 加号：疾病 */
	public String orderIllness;
	/* 加号：预约目的 */
	public String orderReason;
	/* 加号：疾病描述 */
	public String illnessDescription;
	/* 加号：加号说明 */
	public String plusNote;
	/* 是否读取： 0 未读    1 已读  */
	@Column(columnDefinition="default 0")
	public String ifread;
	
	/* 是否是问题： 0历史数据    1新添加的问题  */
	@Column(columnDefinition="default 1")
	public String iquestion;
	
	public ConsultRecord() {super();}
	public ConsultRecord(String uuid) {
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getCustomerUuid() {
		return customerUuid;
	}
	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
	public String getDoctorUuid() {
		return doctorUuid;
	}
	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
	}
	public String getTagsUuid() {
		return tagsUuid;
	}
	public void setTagsUuid(String tagsUuid) {
		this.tagsUuid = tagsUuid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPicture1() {
		return picture1;
	}
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}
	public String getPicture2() {
		return picture2;
	}
	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}
	public String getPicture3() {
		return picture3;
	}
	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}
	public String getPicture4() {
		return picture4;
	}
	public void setPicture4(String picture4) {
		this.picture4 = picture4;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getConsultType() {
		return consultType;
	}
	public void setConsultType(String consultType) {
		this.consultType = consultType;
	}
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	public String getDealState() {
		return dealState;
	}
	public void setDealState(String dealState) {
		this.dealState = dealState;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	public String getTeleCounUuid() {
		return teleCounUuid;
	}
	public void setTeleCounUuid(String teleCounUuid) {
		this.teleCounUuid = teleCounUuid;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getSeeDoctorTime() {
		return seeDoctorTime;
	}
	public void setSeeDoctorTime(String seeDoctorTime) {
		this.seeDoctorTime = seeDoctorTime;
	}
	public String getOrderIllness() {
		return orderIllness;
	}
	public void setOrderIllness(String orderIllness) {
		this.orderIllness = orderIllness;
	}
	public String getOrderReason() {
		return orderReason;
	}
	public void setOrderReason(String orderReason) {
		this.orderReason = orderReason;
	}
	public String getIllnessDescription() {
		return illnessDescription;
	}
	public void setIllnessDescription(String illnessDescription) {
		this.illnessDescription = illnessDescription;
	}
	public String getPlusNote() {
		return plusNote;
	}
	public void setPlusNote(String plusNote) {
		this.plusNote = plusNote;
	}
	public String getIfread() {
		return ifread;
	}
	public void setIfread(String ifread) {
		this.ifread = ifread;
	}
	
	public String getIquestion() {
		return iquestion;
	}
	public void setIquestion(String iquestion) {
		this.iquestion = iquestion;
	}
	
	
}
