package com.aebiz.b2b2c.cms.contentlist.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;

@Entity
@Table(name = "content_list")
@Component
public class ContentListModel extends BaseModel {
	// 注入医生的service
	@Transient
	private static ServicestaffService servicestaffService;

	@Autowired
	public void setServicestaffService(ServicestaffService servicestaffService) {
		this.servicestaffService = servicestaffService;
	}

	// 注入资讯的service
	@Transient
	private static ContentService contentService;

	@Autowired
	public void ContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	/* 医生的uuid */
	private String doctorUuid = "";
	/* 医生的手机号 */
	private String doctorMobile = "";
	/* 咨询内容的uuid */
	private String contentUuid = "";
	/* 详细说明 */
	private String detail = "";
	/* 索取时间 */
	private String createTime = "";
	/* 索取人邮箱 */
	private String email = "";

	/* 索取类别 */
	private String contentCategoryUuid = "";

	/* 发送状态 */
	private String state = "";// 0表示未发送；1表示已发送

	/* 医生姓名 */
	@Transient
	private String doctorRealName = "";

	/* 文献名称 */
	@Transient
	private String contentTitle = "";
	

	public String getDoctorUuid() {
		return doctorUuid;
	}

	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
	}

	public String getDoctorMobile() {
		return doctorMobile;
	}

	public void setDoctorMobile(String doctorMobile) {
		this.doctorMobile = doctorMobile;
	}

	public String getContentUuid() {
		return contentUuid;
	}

	public void setContentUuid(String contentUuid) {
		this.contentUuid = contentUuid;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContentCategoryUuid() {
		return contentCategoryUuid;
	}

	public void setContentCategoryUuid(String contentCategoryUuid) {
		this.contentCategoryUuid = contentCategoryUuid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setDoctorRealName(String doctorRealName) {
		this.doctorRealName = doctorRealName;
	}

	public String getContentTitle() {
		if (!StringUtil.isEmpty(contentUuid)) {
			ContentModel c = contentService.getByUuid(contentUuid);
			if (c != null) {
				return c.getContentTitle();
			}
		}

		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getDoctorRealName() {

		if (!StringUtil.isEmpty(doctorUuid)) {
			ServicestaffModel s = servicestaffService.getByUuid(doctorUuid);
			if (s != null) {
				return s.getRealName();
			}
		}
		return doctorRealName;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[doctorUuid=" + this.getDoctorUuid() + ",doctorMobile="
				+ this.getDoctorMobile() + ",contentUuid="
				+ this.getContentUuid() + ",detail=" + this.getDetail()
				+ ",createTime=" + this.getCreateTime() + ",email="
				+ this.getEmail() + ",]";
	}
}
