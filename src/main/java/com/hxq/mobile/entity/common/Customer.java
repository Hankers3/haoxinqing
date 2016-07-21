package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.wxcommon.repository.AbstractEntity;

/**
 * 患者信息
 */
@SuppressWarnings("serial")
@Entity(name="customer")
public class Customer extends AbstractEntity<String> {
	/* 会员冻结状态，1表示冻结 */
	@Transient
	public static final String CUSTOMER_FROZENSTATE_FROZEN = "1";
	/* 会员冻结状态，0表示未冻结 */
	@Transient
	public static final String CUSTOMER_FROZENSTATE_UNFROZEN = "0";
	/* 会员激活状态，1表示审批通过 */
	@Transient
	public static final String CUSTOMER_ACTIVESTATE_ACTIVATE = "1";
	/* 会员激活状态，0表示未审核 */
	@Transient
	public static final String CUSTOMER_ACTIVESTATE_UNACTIVATE = "0";
	/* 会员激活状态，2示 审核不通过 */
	@Transient
	public static final String CUSTOMER_ACTIVESTATE_NOACTIVATE = "2";
	/* 会员实名认证状态，1表示已认证 */
	@Transient
	public static final String CUSTOMER_AUTHSTATE_AUTHED = "1";
	/* 会员实名认证状态，0表示未认证 */
	@Transient
	public static final String CUSTOMER_AUTHSTATE_UNAUTHED = "0";

	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
    public String uuid;
	@Column(columnDefinition="default 1")
	public String delFlag;
	public String opeTime;
	public String oper;
	/* 登录密码 */
	public String password;
	/* 激活码 */
	public String activeCode;
	/* 是否激活 */
	public String activeState;
	/* 实名认证状态 */
	public String authState;
	/* 创建时间 */
	public String createTime;
	/* 会员平台等级编号 */
	public String customerShopLevelUuid;
	/* 用户名 */
	public String customerName;
	/* 邮箱 */
	public String email;
	/* 冻结状态 */
	public String frozenState;
	/* 冻结类型 */
	public String frozenType;
	/* 最后错误登录时间 */
	public String lastWrongLoginTime;
	/* 登录错误次数 */
	@Column(columnDefinition="VARCHAR default 0")
	public Integer loginErrorTimes;
	/* 手机号 */
	public String mobile;
	/* 会员编号 */
	public String customerId;
	/* QQ */
	public String qq;
	public String lastLoginTime;
	public String lastLoginTimef;
	public String pwdStrength;
	public String tipUser;
	/* 患者分类 */
	public String categoryUuid;
	/* 分组编号 */
	public String groupUuid;
	/* 账户剩余金额 */
	@Column(columnDefinition="default 0")
	public Double accountAmount;
	/* 注册状态 ，默认为1，0：未成功，1：成功 */
	@Column(columnDefinition="default 1")
	public String regState;
	/* 支付密码 */
	public String payPassword;

	/* 冻结类型 */
	@Transient
	public String frozenTypeShowName;	

	public Customer() {super();}
	public Customer(String uuid) {
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
	/* 得到冻结类型 */
	public String getFrozenTypeShowName() {
		if (StringUtil.isEmpty(this.frozenType)) {
			return MessageHelper.getMessage("customer.m.unfrozened");
		} else {
			return com.aebiz.b2b2c.customermgr.customer.vo.FrozenTypeEnum.getValue(this.frozenType);
		}
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	public String getActiveState() {
		return activeState;
	}
	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}
	public String getAuthState() {
		return authState;
	}
	public void setAuthState(String authState) {
		this.authState = authState;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCustomerShopLevelUuid() {
		return customerShopLevelUuid;
	}
	public void setCustomerShopLevelUuid(String customerShopLevelUuid) {
		this.customerShopLevelUuid = customerShopLevelUuid;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFrozenState() {
		return frozenState;
	}
	public void setFrozenState(String frozenState) {
		this.frozenState = frozenState;
	}
	public String getFrozenType() {
		return frozenType;
	}
	public void setFrozenType(String frozenType) {
		this.frozenType = frozenType;
	}
	public String getLastWrongLoginTime() {
		return lastWrongLoginTime;
	}
	public void setLastWrongLoginTime(String lastWrongLoginTime) {
		this.lastWrongLoginTime = lastWrongLoginTime;
	}
	public Integer getLoginErrorTimes() {
		return loginErrorTimes;
	}
	public void setLoginErrorTimes(Integer loginErrorTimes) {
		this.loginErrorTimes = loginErrorTimes;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginTimef() {
		return lastLoginTimef;
	}
	public void setLastLoginTimef(String lastLoginTimef) {
		this.lastLoginTimef = lastLoginTimef;
	}
	public String getPwdStrength() {
		return pwdStrength;
	}
	public void setPwdStrength(String pwdStrength) {
		this.pwdStrength = pwdStrength;
	}
	public String getTipUser() {
		return tipUser;
	}
	public void setTipUser(String tipUser) {
		this.tipUser = tipUser;
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
	public Double getAccountAmount() {
		return accountAmount;
	}
	public void setAccountAmount(Double accountAmount) {
		this.accountAmount = accountAmount;
	}
	public String getRegState() {
		return regState;
	}
	public void setRegState(String regState) {
		this.regState = regState;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
}
