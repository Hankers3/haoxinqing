package com.hxq.mobile.entity.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.wxcommon.repository.AbstractEntity;

@SuppressWarnings("serial")
@Entity(name="content_list")
public class ContentList extends AbstractEntity<String> {
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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
    public String uuid;
	
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
	
	public ContentList() {super();}
	public ContentList(String uuid) {
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
