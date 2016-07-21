package com.aebiz.b2b2c.cms.platforminfo.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

/**
 * 存在视频信息表 （上传视频信息到百度）
 * 
 * @author xueli
 * 
 */
@Entity
@Table(name = "platform_info")
@Component
public class PlatFormInfoModel extends BaseModel {

	/* 注入文件service */
	@Transient
	private static FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	/* 视频名称 */
	private String videoModel;

	/* 视频简介 */
	private String videoIntroduction;

	/* 视频发布状态 */
	private String state;
	
	/* 视频类型   0:患者端   1：医生端*/
	private String videoType;

	/* 是否为热播视频（推荐） 0:不是 1：是 */
	private String videoHot;

	/* 视频连接地址 */
	private String videoAddress;

	/* 简介图 */
	private String image;

	/* 图片地址 */
	@Transient
	private String imageUrl;

	/* 创建时间 */
	private String createTime;

	/* 类型1代表是预告，否则的话是正在直播*/
	private String type;

	/* 数量 */
	private int number;

	/* 课程的讲课时间 */
	private String startTime;

	private String userId;
	/*主讲人名字*/
	private String userName;
	/* 主讲人介绍 */
	private String userIntroduce;
	
	private String fName;//上传到云视频上的文件名

	public String getVideoHot() {
		return videoHot;
	}

	public void setVideoHot(String videoHot) {
		this.videoHot = videoHot;
	}

	public String getUserIntroduce() {
		return userIntroduce;
	}

	public void setUserIntroduce(String userIntroduce) {
		this.userIntroduce = userIntroduce;
	}

	public String getImageUrl() {
		if (!StringUtil.isEmpty(this.image)) {
			return this.fileService.getOneFileModel(this.image)
					.getRemotePaths();
		}
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setVideoModel(String obj) {
		this.videoModel = obj;
	}

	public String getVideoModel() {
		return this.videoModel;
	}

	public void setVideoIntroduction(String obj) {
		this.videoIntroduction = obj;
	}

	public String getVideoIntroduction() {
		return this.videoIntroduction;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setVideoAddress(String obj) {
		this.videoAddress = obj;
	}

	public String getVideoAddress() {
		return this.videoAddress;
	}

	public void setImage(String obj) {
		this.image = obj;
	}

	public String getImage() {
		return this.image;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setType(String obj) {
		this.type = obj;
	}

	public String getType() {
		return this.type;
	}

	public void setNumber(int obj) {
		this.number = obj;
	}

	public int getNumber() {
		return this.number;
	}

	public void setStartTime(String obj) {
		this.startTime = obj;
	}

	public String getStartTime() {
		return this.startTime;
	}
	

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}
	
	public String getVideoType() {
		return videoType;
	}
	 
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[videoModel=" + this.getVideoModel() + ",videoIntroduction="
				+ this.getVideoIntroduction() + ",state=" + this.getState()+",videoType=" + this.getVideoType()
				+ ",videoAddress=" + this.getVideoAddress() + ",image="
				+ this.getImage() + ",createTime=" + this.getCreateTime()
				+ ",type=" + this.getType() + ",number=" + this.getNumber()
				+ ",startTime=" + this.getStartTime() + ",]";
	}
}
