package com.aebiz.b2b2c.cms.livevideo.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
/**
 * 直播视频管理
 * @author xueli
 *
 */
@Entity
@Table(name="live_video")
@Component
public class LiveVideoModel extends BaseModel{
	/* 注入文件service */
	@Transient
	private static FileService fileService;
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	/* 视频名称 */
	private String videoName;
	/*主讲人名字*/
	private String userName;

	/* 主讲人介绍 */
	private String userIntroduce;
	
	/* 视频类型   0:患者端   1：医生端*/
	private String videoType;
	
	//上传到云视频上的文件名
	private String fName;
	/* 视频连接地址 */
	private String videoAddress;
	/* 视频简介 */
	private String videoIntroduction;
	/* 视频发布状态1:已发布 0：未发布 */
	private String state;
	/* 数量 */
	private int number;
	/* 简介图 */
	private String image;
	/* 课程的讲课时间 */
	private String startTime;
	/*报名结束时间*/
	private String endTime;
	/* 创建时间 */
	private String createTime;
	
	/* 图片地址 */
	@Transient
	private String imageUrl;
	
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
	
	public void setVideoName(String obj){
		this.videoName = obj;
	}
	public String getVideoName(){
		return this.videoName;
	}
	
	public void setUserName(String obj){
		this.userName = obj;
	}
	public String getUserName(){
		return this.userName;
	}
	
	public void setUserIntroduce(String obj){
		this.userIntroduce = obj;
	}
	public String getUserIntroduce(){
		return this.userIntroduce;
	}
	
	public void setVideoType(String obj){
		this.videoType = obj;
	}
	public String getVideoType(){
		return this.videoType;
	}
	
	public void setFName(String obj){
		this.fName = obj;
	}
	public String getFName(){
		return this.fName;
	}
	
	public void setVideoAddress(String obj){
		this.videoAddress = obj;
	}
	public String getVideoAddress(){
		return this.videoAddress;
	}
	
	public void setVideoIntroduction(String obj){
		this.videoIntroduction = obj;
	}
	public String getVideoIntroduction(){
		return this.videoIntroduction;
	}
	
	public void setState(String obj){
		this.state = obj;
	}
	public String getState(){
		return this.state;
	}
	
	public void setNumber(int obj){
		this.number = obj;
	}
	public int getNumber(){
		return this.number;
	}
	
	public void setImage( String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
	}
	
	public void setStartTime(String obj){
		this.startTime = obj;
	}
	public String getStartTime(){
		return this.startTime;
	}
	
	public void setEndTime(String obj){
		this.endTime = obj;
	}
	public String getEndTime(){
		return this.endTime;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[videoName=" + this.getVideoName() + ",userName=" + this.getUserName() + ",userIntroduce=" + this.getUserIntroduce() + ",videoType=" + this.getVideoType() + ",fName=" + this.getFName() + ",videoAddress=" + this.getVideoAddress() + ",videoIntroduction=" + this.getVideoIntroduction() + ",state=" + this.getState() + ",number=" + this.getNumber() + ",image=" + this.getImage() + ",startTime=" + this.getStartTime() + ",endTime=" + this.getEndTime() + ",createTime=" + this.getCreateTime() + ",]";
	}	
}
