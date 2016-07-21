package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.wxcommon.repository.AbstractEntity;
/**
 * 会员和医生收藏信息表
 * @author 
 *
 */
@SuppressWarnings("serial")
@Entity(name="favorite")
public class Favorite extends AbstractEntity<String> {
	
	/* 收藏状态，1表示已收藏 */
	@Transient
	public static final String NOTCANCEL = "1";

	/*收藏状态，0表示已取消收藏 */
	@Transient
	public static final String CANCELLED = "0";
	
	/*用户类型 1：医生  */
	@Transient
	public static final String USER_DOCTOR = "1";

	/*用户类型  2：患者 */
	@Transient
	public static final String USER_CUSTOMER = "2";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
	/*用户编号*/
	public String userId;
	
	/*收藏时间*/
	public String favoriteTime;
	
	/*资讯编号*/
	public String contentUuid;
	
	/*视频编号*/
	public String videoUuid;
	
	/*用户类型 1：医生 2：患者 */
	@Column(columnDefinition="default 0")
	public String userType;
	
	/*收藏类型 1：资讯 2：视频*/
	@Column(columnDefinition="default 0")
	public String favoriteType;
	
	/*状态*/
	public String state;
	
	/*标签*/
	public String tags;

	public Favorite() {super();}
	public Favorite(String uuid) {
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFavoriteTime() {
		return favoriteTime;
	}
	public void setFavoriteTime(String favoriteTime) {
		this.favoriteTime = favoriteTime;
	}
	public String getContentUuid() {
		return contentUuid;
	}
	public void setContentUuid(String contentUuid) {
		this.contentUuid = contentUuid;
	}
	public String getVideoUuid() {
		return videoUuid;
	}
	public void setVideoUuid(String videoUuid) {
		this.videoUuid = videoUuid;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getFavoriteType() {
		return favoriteType;
	}
	public void setFavoriteType(String favoriteType) {
		this.favoriteType = favoriteType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
}
