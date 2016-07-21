package com.aebiz.b2b2c.servicestaff.groupfriends.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 医圈好友表
 * @author wangbingning
 *
 */
@Entity
@Table(name="group_friends")
public class GroupFriendsModel extends BaseModel{
	/*医圈编号*/
	private String groupUuid;
	/*好友名称*/
	private String friendName;
	/*手机号*/
	private String mobile;
	/*创建时间*/
	private String createTime;
	/*备注*/
	private String note;
	/*医生的id*/
	private String doctorUuid;
	
	public String getDoctorUuid() {
		return doctorUuid;
	}
	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
	}
	public void setGroupUuid(String obj){
		this.groupUuid = obj;
	}
	public String getGroupUuid(){
		return this.groupUuid;
	}
	
	public void setFriendName(String obj){
		this.friendName = obj;
	}
	public String getFriendName(){
		return this.friendName;
	}
	
	public void setMobile(String obj){
		this.mobile = obj;
	}
	public String getMobile(){
		return this.mobile;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setNote(String obj){
		this.note = obj;
	}
	public String getNote(){
		return this.note;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[groupUuid=" + this.getGroupUuid() + ",friendName=" + this.getFriendName() + ",mobile=" + this.getMobile() + ",createTime=" + this.getCreateTime() + ",note=" + this.getNote() + ",]";
	}	
}
