package com.aebiz.b2b2c.cms.platformcommunication.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.SysManagerService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

/**
 * 记录视频互动的信息的表
 * @author xp
 *
 */
@Entity
@Table(name="platform_communication")
@Component
public class PlatformCommunicationModel extends BaseModel{
	/*添加系统管理的service来获取管理员的姓名*/
	@Transient
	private static SysManagerService smService; 
	
	@Autowired
	public void setSmService(SysManagerService smService){
		this.smService = smService;
	}
	
	/* 视频编号 */
	private String platformUuid;
	/* 视频名称 */
	private String platformrName;
	/* 提问人编号 */
	private String questionerUuid;
	/* 提问人名称 */
	private String questionerName;
	/* 提问人类型  1:医生 2:患者*/
	private String userType;
	/* 问题描述 */
	private String problemDescription;
	/* 创建时间 */
	private String createTime;
	
	/* 审核状态  0:未审核 1：通过 2：不通过*/
	private String conftimeState;
	/* 审核人 */
	private String admin;
	/* 回复信息 */
	private String replyMessage;
	/* 回复时间 */
	private String replyTime;
	/* 回复状态 1 ：已回复 ，2：未回复 */
	private String replyState;
	/* 审核时间 */
	private String conftime;
	/* 备注 */
	private String remark;
	
	@Transient
	private String managerName;
	
	
	public String getManagerName() {
		if(!StringUtil.isEmpty(admin)){
			SysManagerModel sm = smService.getByUuid(admin);
			return sm.getName();
		}
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public void setPlatformUuid(String obj){
		this.platformUuid = obj;
	}
	public String getReplyState() {
		return replyState;
	}
	public void setReplyState(String replyState) {
		this.replyState = replyState;
	}
	public String getPlatformUuid(){
		return this.platformUuid;
	}
	
	public void setPlatformrName(String obj){
		this.platformrName = obj;
	}
	public String getPlatformrName(){
		return this.platformrName;
	}
	
	public void setQuestionerUuid(String obj){
		this.questionerUuid = obj;
	}
	public String getQuestionerUuid(){
		return this.questionerUuid;
	}
	
	public void setQuestionerName(String obj){
		this.questionerName = obj;
	}
	public String getQuestionerName(){
		return this.questionerName;
	}
	
	public void setUserType(String obj){
		this.userType = obj;
	}
	public String getUserType(){
		return this.userType;
	}
	
	public void setProblemDescription(String obj){
		this.problemDescription = obj;
	}
	public String getProblemDescription(){
		return this.problemDescription;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setConftimeState(String obj){
		this.conftimeState = obj;
	}
	public String getConftimeState(){
		return this.conftimeState;
	}
	
	public void setAdmin(String obj){
		this.admin = obj;
	}
	public String getAdmin(){
		return this.admin;
	}
	
	public void setReplyMessage(String obj){
		this.replyMessage = obj;
	}
	public String getReplyMessage(){
		return this.replyMessage;
	}
	
	public void setReplyTime(String obj){
		this.replyTime = obj;
	}
	public String getReplyTime(){
		return this.replyTime;
	}
	
	public void setConftime(String obj){
		this.conftime = obj;
	}
	public String getConftime(){
		return this.conftime;
	}
	
	public void setRemark(String obj){
		this.remark = obj;
	}
	public String getRemark(){
		return this.remark;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[platformUuid=" + this.getPlatformUuid() + ",platformrName=" + this.getPlatformrName() + ",questionerUuid=" + this.getQuestionerUuid() + ",questionerName=" + this.getQuestionerName() + ",userType=" + this.getUserType() + ",problemDescription=" + this.getProblemDescription() + ",createTime=" + this.getCreateTime() + ",conftimeState=" + this.getConftimeState() + ",admin=" + this.getAdmin() + ",replyMessage=" + this.getReplyMessage() + ",replyTime=" + this.getReplyTime() + ",conftime=" + this.getConftime() + ",remark=" + this.getRemark() + ",]";
	}	
}
