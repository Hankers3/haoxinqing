package com.aebiz.b2b2c.customermgr.customeraudit.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Entity
@Table(name = "customer_auth")
@Component
public class CustomerAuthModel extends BaseModel {

	/**
	 * 会员实名认证状态，1表示已认证
	 */
	public static final String CUSTOMERAUTH_AUTHSTATE_AUTHED = "1";
	/**
	 * 会员实名认证状态，0表示未认证
	 */
	public static final String CUSTOMERAUTH_AUTHSTATE_UNAUTHED = "0";

	@Transient
	private static FileService fileService;

	/* 会员编号 */
	private String customerUuid = "";

	/* 真实姓名 */
	private String realName = "";

	/* 证件号码 */
	private String certCode = "";

	/* 证件照片正面 */
	private String certImage1 = "";

	/* 证件照片反面 */
	private String certImage2 = "";

	/* 证件照片1地址 */
	@Transient
	private String certImage1Url = "";

	/* 证件照片2地址 */
	@Transient
	private String certImage2Url = "";

	/* 认证申请时间 */
	private String authApplyTime = "";

	/* 认证状态 */
	private String authState = "0";

	/* 证件类型 */
	private String certType = "";

	/* 认证审核原因 */
	private String auditReason = "";

	/* 审核时间 */
	private String auditTime = "";

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setRealName(String obj) {
		this.realName = obj;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setCertCode(String obj) {
		this.certCode = obj;
	}

	public String getCertCode() {
		return this.certCode;
	}

	public String getCertImage1() {
		return certImage1;
	}

	public void setCertImage1(String certImage1) {
		this.certImage1 = certImage1;
	}

	public String getCertImage2() {
		return certImage2;
	}

	public void setCertImage2(String certImage2) {
		this.certImage2 = certImage2;
	}

	// 得到图片地址
	public String getCertImage1Url() {
		if (StringUtil.isEmpty(this.certImage1)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.certImage1);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getCertImage1();
		}
	}

	public void setCertImage1Url(String certImage1Url) {
		this.certImage1Url = certImage1Url;
	}

	public String getCertImage2Url() {
		if (StringUtil.isEmpty(this.certImage2)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.certImage2);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getCertImage2();
		}
	}

	public void setCertImage2Url(String certImage2Url) {
		this.certImage2Url = certImage2Url;
	}

	public void setAuthApplyTime(String obj) {
		this.authApplyTime = obj;
	}

	public String getAuthApplyTime() {
		return this.authApplyTime;
	}

	public void setAuthState(String obj) {
		this.authState = obj;
	}

	public String getAuthState() {
		return this.authState;
	}

	public void setCertType(String obj) {
		this.certType = obj;
	}

	public String getCertType() {
		return this.certType;
	}

	public String getAuditReason() {
		return auditReason;
	}

	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}

	public void setAuditTime(String obj) {
		this.auditTime = obj;
	}

	public String getAuditTime() {
		return this.auditTime;
	}

	@Override
	public String toString() {
		return "CustomerAuthModel [customerUuid=" + customerUuid
				+ ", realName=" + realName + ", certCode=" + certCode
				+ ", certImage1=" + certImage1 + ", certImage2=" + certImage2
				+ ", certImage1Url=" + certImage1Url + ", certImage2Url="
				+ certImage2Url + ", authApplyTime=" + authApplyTime
				+ ", authState=" + authState + ", certType=" + certType
				+ ", auditReason=" + auditReason + ", auditTime=" + auditTime
				+ "]";
	}

}
