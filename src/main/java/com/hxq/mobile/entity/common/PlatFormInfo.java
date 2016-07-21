package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 讲堂（视频）表
 * 
 * @author 
 * 
 */
@SuppressWarnings("serial")
@Entity(name="platform_info")
public class PlatFormInfo extends AbstractEntity<String> {

	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
    public String uuid;
	/* 视频名称 */
	public String videoModel;

	/* 视频简介 */
	public String videoIntroduction;

	/* 视频发布状态 */
	public String state;
	
	/* 视频类型   0:患者端   1：医生端*/
	@Column(columnDefinition="default 0")
	public String videoType;

	/* 是否为热播视频（推荐） 0:不是 1：是 */
	@Column(columnDefinition="default 0")
	public String videoHot;

	/* 视频连接地址 */
	public String videoAddress;

	/* 简介图 */
	public String image;

	/* 创建时间 */
	public String createTime;

	/* 类型1代表是预告，否则的话是正在直播*/
	@Column(columnDefinition="default 1")
	public String type;

	/* 数量 */
	@Column(columnDefinition="default 0")
	public Integer number;

	/* 课程的讲课时间 */
	public String startTime;

	public String userId;
	/*主讲人名字*/
	public String userName;
	/* 主讲人介绍 */
	public String userIntroduce;
	
	public String fName;//上传到云视频上的文件名

	public PlatFormInfo() {super();}
	public PlatFormInfo(String uuid) {
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
	public String getVideoModel() {
		return videoModel;
	}
	public void setVideoModel(String videoModel) {
		this.videoModel = videoModel;
	}
	public String getVideoIntroduction() {
		return videoIntroduction;
	}
	public void setVideoIntroduction(String videoIntroduction) {
		this.videoIntroduction = videoIntroduction;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getVideoType() {
		return videoType;
	}
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}
	public String getVideoHot() {
		return videoHot;
	}
	public void setVideoHot(String videoHot) {
		this.videoHot = videoHot;
	}
	public String getVideoAddress() {
		return videoAddress;
	}
	public void setVideoAddress(String videoAddress) {
		this.videoAddress = videoAddress;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserIntroduce() {
		return userIntroduce;
	}
	public void setUserIntroduce(String userIntroduce) {
		this.userIntroduce = userIntroduce;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
}
