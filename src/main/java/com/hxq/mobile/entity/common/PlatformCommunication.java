package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 评论表
 * @author xp
 *
 */
@SuppressWarnings("serial")
@Entity(name="platform_communication")
public class PlatformCommunication extends AbstractEntity<String> {

	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
    public String uuid;
	/* 视频编号 */
	public String platformUuid;
	/* 视频名称 */
	public String platformrName;
	/* 提问人编号 */
	public String questionerUuid;
	/* 提问人名称 */
	public String questionerName;
	/* 提问人类型  1:医生 2:患者*/
	@Column(columnDefinition="default 1")
	public String userType;
	/* 问题描述 */
	public String problemDescription;
	/* 创建时间 */
	public String createTime;
	/* 审核状态  0:未审核 1：通过 2：不通过*/
	@Column(columnDefinition="default 0")
	public String conftimeState;
	/* 审核人 */
	public String admin;
	/* 回复信息 */
	public String replyMessage;
	/* 回复时间 */
	public String replyTime;
	/* 回复状态 1 ：已回复 ，2：未回复 */
	@Column(columnDefinition="default 1")
	public String replyState;
	/* 审核时间 */
	public String conftime;
	/* 备注 */
	public String remark;
	
	public PlatformCommunication() {super();}
	public PlatformCommunication(String uuid) {
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
	public String getPlatformUuid() {
		return platformUuid;
	}
	public void setPlatformUuid(String platformUuid) {
		this.platformUuid = platformUuid;
	}
	public String getPlatformrName() {
		return platformrName;
	}
	public void setPlatformrName(String platformrName) {
		this.platformrName = platformrName;
	}
	public String getQuestionerUuid() {
		return questionerUuid;
	}
	public void setQuestionerUuid(String questionerUuid) {
		this.questionerUuid = questionerUuid;
	}
	public String getQuestionerName() {
		return questionerName;
	}
	public void setQuestionerName(String questionerName) {
		this.questionerName = questionerName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getProblemDescription() {
		return problemDescription;
	}
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getConftimeState() {
		return conftimeState;
	}
	public void setConftimeState(String conftimeState) {
		this.conftimeState = conftimeState;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getReplyMessage() {
		return replyMessage;
	}
	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyState() {
		return replyState;
	}
	public void setReplyState(String replyState) {
		this.replyState = replyState;
	}
	public String getConftime() {
		return conftime;
	}
	public void setConftime(String conftime) {
		this.conftime = conftime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
