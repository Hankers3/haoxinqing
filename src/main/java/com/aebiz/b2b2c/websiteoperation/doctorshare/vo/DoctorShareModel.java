package com.aebiz.b2b2c.websiteoperation.doctorshare.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 分享资源表
 * 
 * @author XP
 *
 */
@Entity
@Table(name = "doctor_share")
@Component
public class DoctorShareModel extends BaseModel {
	/* 患者类型 */
	public static final String SHARE_USERTYPE_CUS = "1";
	/* 医生类型 */
	public static final String SHARE_USERTYPE_DOC = "2";

	/* 用户的id */
	private String userUuid;
	/* 资讯的id */
	private String contentUuid;
	/* 视频的id */
	private String videoUuid;
	/* 用户类型 1：患者 2：医生 */
	private String userType;

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setContentUuid(String obj) {
		this.contentUuid = obj;
	}

	public String getContentUuid() {
		return this.contentUuid;
	}

	public void setVideoUuid(String obj) {
		this.videoUuid = obj;
	}

	public String getVideoUuid() {
		return this.videoUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName() + ",contentUuid=" + this.getContentUuid()
				+ ",videoUuid=" + this.getVideoUuid() + ",]";
	}
}
