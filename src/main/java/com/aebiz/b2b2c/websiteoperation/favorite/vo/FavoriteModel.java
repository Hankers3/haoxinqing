package com.aebiz.b2b2c.websiteoperation.favorite.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
/**
 * 会员和医生收藏信息表
 * @author xueli
 *
 */
@Entity
@Table(name="favorite")
@Component
public class FavoriteModel extends BaseModel{
	/* 注入会员service */
	@Transient
	private static CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	/* 注入会员service */
	@Transient
	private static ServicestaffService staffService;
	
	@Autowired
	public void setStaffService(ServicestaffService staffService) {
		this.staffService = staffService;
	}
	
	
	
	/* 注入Contentservice */
	@Transient
	private static ContentService contentService;

	@Autowired
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}
	/* 收藏状态，1表示已收藏 */
	public static final String NOTCANCEL = "1";

	/*收藏状态，0表示已取消收藏 */
	public static final String CANCELLED = "0";
	
	/*用户类型 1：医生  */
	public static final String USER_DOCTOR = "1";

	/*用户类型  2：患者 */
	public static final String USER_CUSTOMER = "2";
	
	/*用户编号*/
	private String userId;
	
	/*收藏时间*/
	private String favoriteTime;
	
	/*资讯编号*/
	private String contentUuid;
	
	/*视频编号*/
	private String videoUuid;
	
	/*用户类型 1：医生 2：患者 */
	private String userType;
	
	/*收藏类型 1：资讯 2：视频*/
	private String favoriteType;
	
	/*状态*/
	private String state;
	
	/*标签*/
	private String tags;
	
	/*用户名*/
	@Transient
	private String userName="";
	
	/*用户电话*/
	@Transient
	private String userMobile="";
	
	/*咨询内容*/
	@Transient
	private String contentText="";
	
	
	public String getContentText() {
		if(!StringUtil.isEmpty(contentUuid)){
			ContentModel  c = contentService.getByUuid(contentUuid);
			if(c!=null){
			 return c.getContentTitle();}
		}
		
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	/*通过用户类型获取用户电话*/
	public String getUserMobile() {
		if(!StringUtil.isEmpty(userType)&& USER_DOCTOR.equals(userType)){
			return staffService.getMobileByUuid(userId);
		}else if(!StringUtil.isEmpty(userType)&& USER_CUSTOMER.equals(userType)){
			return customerService.getCustomerMobileByUuid(userId);
		}
		
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	/*用户类型*/
	public String getUserName() {
		if(!StringUtil.isEmpty(userType)&& USER_DOCTOR.equals(userType)){
			return staffService.getServiceStaffNameByUuid(userId);
		}else if(!StringUtil.isEmpty(userType)&& USER_CUSTOMER.equals(userType)){
			return customerService.getCustomerNameByCustomerUuid(userId);
		}
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserId(String obj){
		this.userId = obj;
	}
	public String getUserId(){
		return this.userId;
	}
	
	public void setFavoriteTime(String obj){
		this.favoriteTime = obj;
	}
	public String getFavoriteTime(){
		return this.favoriteTime;
	}
	
	public void setContentUuid(String obj){
		this.contentUuid = obj;
	}
	public String getContentUuid(){
		return this.contentUuid;
	}
	
	public void setVideoUuid(String obj){
		this.videoUuid = obj;
	}
	public String getVideoUuid(){
		return this.videoUuid;
	}
	
	public void setUserType(String obj){
		this.userType = obj;
	}
	public String getUserType(){
		return this.userType;
	}
	
	public void setFavoriteType(String obj){
		this.favoriteType = obj;
	}
	public String getFavoriteType(){
		return this.favoriteType;
	}
	
	public void setState(String obj){
		this.state = obj;
	}
	public String getState(){
		return this.state;
	}
	
	public void setTags(String obj){
		this.tags = obj;
	}
	public String getTags(){
		return this.tags;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[userId=" + this.getUserId() + ",favoriteTime=" + this.getFavoriteTime() + ",contentUuid=" + this.getContentUuid() + ",videoUuid=" + this.getVideoUuid() + ",userType=" + this.getUserType() + ",favoriteType=" + this.getFavoriteType() + ",state=" + this.getState() + ",tags=" + this.getTags() + ",]";
	}	
}
