package com.aebiz.b2b2c.customermgr.customersource.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "customer_source")
public class CustomerSourceModel extends BaseModel {

	/* 客户端来源(移动端、PC端) */
	private String clientType = "";

	/* 来源站点 来源分为本站注册、第三方外站注册、会员导入 */
	private String siteType = "";

	/* 第三方类型 比如联合登录的QQ登录或者微博等方式 */
	private String thirdPlatType = "";

	/* 第三方类型展示数据 */
	@Transient
	private String thirdPlatShowName = "";

	/* 来源地址 网盟来源或者搜索引擎来源地址 */
	private String formUrl = "";

	/* 推荐人 会员推荐记录的推荐人 */
	private String introducer = "";

	/* 会员编号 */
	private String customerUuid = "";
	
	/* 推荐人邀请码 */
	private String inviteCode = "";
	
	/* 邀请码 */
	private String myInviteCode = "";

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getMyInviteCode() {
		return myInviteCode;
	}

	public void setMyInviteCode(String myInviteCode) {
		this.myInviteCode = myInviteCode;
	}

	public void setClientType(String obj) {
		this.clientType = obj;
	}

	public String getClientType() {
		return this.clientType;
	}

	public void setSiteType(String obj) {
		this.siteType = obj;
	}

	public String getSiteType() {
		return this.siteType;
	}

	public void setThirdPlatType(String obj) {
		this.thirdPlatType = obj;
	}

	public String getThirdPlatType() {
		return this.thirdPlatType;
	}

	public String getThirdPlatShowName() {
		return ThirdPlatTypeEnum.getValue(this.thirdPlatType);
	}

	public void setThirdPlatShowName(String thirdPlatShowName) {
		this.thirdPlatShowName = thirdPlatShowName;
	}

	public void setFormUrl(String obj) {
		this.formUrl = obj;
	}

	public String getFormUrl() {
		return this.formUrl;
	}

	public void setIntroducer(String obj) {
		this.introducer = obj;
	}

	public String getIntroducer() {
		return this.introducer;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[clientType=" + this.getClientType() + ",siteType="
				+ this.getSiteType() + ",thirdPlatType="
				+ this.getThirdPlatType() + ",formUrl=" + this.getFormUrl()
				+ ",introducer=" + this.getIntroducer() + ",customerUuid="
				+ this.getCustomerUuid() + ",]";
	}
}
