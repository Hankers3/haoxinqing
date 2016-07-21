package com.aebiz.b2b2c.customermgr.customer.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.service.CustomerFrozenLogService;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.service.VipclubIntegralStatService;

@Entity
@Table(name = "customer")
@Component
public class CustomerModel extends BaseModel {

	/* 会员冻结状态，1表示冻结 */
	public static final String CUSTOMER_FROZENSTATE_FROZEN = "1";
	/* 会员冻结状态，0表示未冻结 */
	public static final String CUSTOMER_FROZENSTATE_UNFROZEN = "0";

	/* 会员激活状态，1表示审批通过 */
	public static final String CUSTOMER_ACTIVESTATE_ACTIVATE = "1";
	/* 会员激活状态，0表示未审核 */
	public static final String CUSTOMER_ACTIVESTATE_UNACTIVATE = "0";
	/* 会员激活状态，2示 审核不通过 */
	public static final String CUSTOMER_ACTIVESTATE_NOACTIVATE = "2";
	/* 会员实名认证状态，1表示已认证 */
	public static final String CUSTOMER_AUTHSTATE_AUTHED = "1";
	/* 会员实名认证状态，0表示未认证 */
	public static final String CUSTOMER_AUTHSTATE_UNAUTHED = "0";

	@Transient
	private static CustomerFrozenLogService customerFrozenLogService;

	@Transient
	private static VipclubIntegralStatService vipclubService;
	/* 注入患者service */
	@Transient
	private static CustomerInfoService customerInfoService;

	@Autowired
	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}

	/* 支付密码 */
	private String payPassword = "";

	/* 注册状态 ，默认为0，0：未成功，1：成功 */
	private String regState = "";

	/* 性别 */
	@Transient
	private String sex = "";

	/* 出生日期 */
	@Transient
	private String birthday = "";

	/* 会员编号 */
	private String customerId = "";

	/* 昵称 */
	private String customerName = "";

	/* 手机号 */
	private String mobile = "";

	/* QQ */
	private String qq = "";

	/* 邮箱 */
	private String email = "";

	/* 密码 */
	private String password = "";

	/* 冻结状态 */
	private String frozenState = "";

	/* 冻结类型 */
	private String frozenType = "";

	/* 是否激活 */
	private String activeState = "";

	/* 实名认证状态 */
	private String authState = "";

	/* 最后错误登录时间 */
	private String lastWrongLoginTime = "";

	/* 激活码 */
	private String activeCode = "";

	/* 登录错误次数 */
	private int loginErrorTimes = 0;

	/* 创建时间 */
	private String createTime = "";

	/* 患者分类 */
	private String categoryUuid = "";

	/* 分组编号 */
	private String groupUuid = "";

	/* 会员平台等级编号 */
	private String customerShopLevelUuid = "";

	/* 冻结类型 */
	@Transient
	private String frozenTypeShowName = "";

	/* 获得当前会员的累计积分 */
	@Transient
	private int intergralCount;

	/* 获得当前会员的可用积分 */
	@Transient
	private int availableIntegral;
	/* 患者用户名 */
	@Transient
	private String realName;

	/* 账户剩余金额 */
	private double accountAmount = 0;

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getRegState() {
		return regState;
	}

	public void setRegState(String regState) {
		this.regState = regState;
	}

	public double getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(double accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getRealName() {
		if (!StringUtil.isEmpty(this.getUuid())) {
			return customerInfoService.getRealNameByUuid(this.getUuid());
		}

		return realName;
	}

	public void setRealName(String realName) {

		this.realName = realName;
	}

	public String getSex() {
		if (!StringUtil.isEmpty(this.getUuid())) {
			CustomerInfoModel ci = customerInfoService.getCustomerInfoModelByCustomerUuid(this.getUuid());
			String sex = ci.getSex();
			return sex;//customerInfoService.getSexByUuid(this.getUuid());
		}
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		if (!StringUtil.isEmpty(this.getUuid())) {
			return customerInfoService.getBirthdayByUuid(this.getUuid());
		}
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/* 获得当前会员的可用积分 */
	public int getAvailableIntegral() {
		return vipclubService.getVipclubIntegralCount(this.getUuid(), "1");
	}

	public void setAvailableIntegral(int availableIntegral) {
		this.availableIntegral = availableIntegral;
	}

	@Autowired
	public void setCustomerFrozenLogService(CustomerFrozenLogService customerFrozenLogService) {
		this.customerFrozenLogService = customerFrozenLogService;
	}

	@Autowired
	public void setVipclubService(VipclubIntegralStatService vipclubService) {
		this.vipclubService = vipclubService;
	}

	/* 获得会员的累计积分 */
	public int getIntergralCount() {
		return vipclubService.getVipclubIntegralCount(this.getUuid(), "3");
	}

	public void setIntergralCount(int intergralCount) {
		this.intergralCount = intergralCount;
	}

	public int getLoginErrorTimes() {
		return loginErrorTimes;
	}

	public void setLoginErrorTimes(int loginErrorTimes) {
		this.loginErrorTimes = loginErrorTimes;
	}

	public String getCategoryUuid() {
		return categoryUuid;
	}

	public void setCategoryUuid(String categoryUuid) {
		this.categoryUuid = categoryUuid;
	}

	public String getGroupUuid() {
		return groupUuid;
	}

	public void setGroupUuid(String groupUuid) {
		this.groupUuid = groupUuid;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setCustomerName(String obj) {
		this.customerName = obj;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setMobile(String obj) {
		this.mobile = obj;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setEmail(String obj) {
		this.email = obj;
	}

	public String getEmail() {
		return this.email;
	}

	public void setPassword(String obj) {
		this.password = obj;
	}

	public String getPassword() {
		return this.password;
	}

	public void setFrozenState(String obj) {
		this.frozenState = obj;
	}

	public String getFrozenState() {
		return this.frozenState;
	}

	public void setFrozenType(String obj) {
		this.frozenType = obj;
	}

	public String getFrozenType() {
		return this.frozenType;
	}

	public void setActiveState(String obj) {
		this.activeState = obj;
	}

	public String getActiveState() {
		return this.activeState;
	}

	public void setAuthState(String obj) {
		this.authState = obj;
	}

	public String getAuthState() {
		return this.authState;
	}

	public void setLastWrongLoginTime(String obj) {
		this.lastWrongLoginTime = obj;
	}

	public String getLastWrongLoginTime() {
		return this.lastWrongLoginTime;
	}

	public void setActiveCode(String obj) {
		this.activeCode = obj;
	}

	public String getActiveCode() {
		return this.activeCode;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String getCustomerShopLevelUuid() {
		return customerShopLevelUuid;
	}

	public void setCustomerShopLevelUuid(String customerShopLevelUuid) {
		this.customerShopLevelUuid = customerShopLevelUuid;
	}

	/* 得到冻结类型 */
	public String getFrozenTypeShowName() {
		if (StringUtil.isEmpty(this.getFrozenType())) {
			return MessageHelper.getMessage("customer.m.unfrozened");
		} else {
			return FrozenTypeEnum.getValue(this.getFrozenType());
		}
	}

	public void setFrozenTypeShowName(String frozenTypeShowName) {
		this.frozenTypeShowName = frozenTypeShowName;
	}

	@Override
	public String toString() {
		return "CustomerModel [customerId=" + customerId + ", customerName=" + customerName + ", mobile=" + mobile
				+ ", email=" + email + ", password=" + password + ", frozenState=" + frozenState + ", frozenType="
				+ frozenType + ", activeState=" + activeState + ", authState=" + authState + ", lastWrongLoginTime="
				+ lastWrongLoginTime + ", activeCode=" + activeCode + ", loginErrorTimes=" + loginErrorTimes
				+ ", createTime=" + createTime + ", customerShopLevelUuid=" + customerShopLevelUuid
				+ ", frozenTypeShowName=" + frozenTypeShowName + "]";
	}

}
