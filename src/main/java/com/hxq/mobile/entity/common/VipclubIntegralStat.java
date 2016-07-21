package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 用户积分统计表
 */
@SuppressWarnings("serial")
@Entity(name="vipclub_Integral_stat")
public class VipclubIntegralStat extends AbstractEntity<String> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
	@Column(columnDefinition="default 1")
	public String delFlag;
	public String opeTime;
	public String oper;
	/* 会员Uuid */
	public String customerUuid;
	/* 积分总数 */
	@Column(columnDefinition="default 0")
	public Integer intergralCount;
	/*
	 * 积分类型 1：可用积分 2：已用积分 3：累计积分 4：购物获得积分 5：行为获得积分 6：奖励获得积分
	 */
	public String intergralType;
	public String orderUuid;
	/*用户类型 1：会员 2：医生*/
	public String userType;
	/* 会员手机号 */
	public String cmobile;

	public VipclubIntegralStat() {super();}
	public VipclubIntegralStat(String uuid) {
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
	public String getCustomerUuid() {
		return customerUuid;
	}
	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
	public int getIntergralCount() {
		return intergralCount;
	}
	public void setIntergralCount(int intergralCount) {
		this.intergralCount = intergralCount;
	}
	public String getIntergralType() {
		return intergralType;
	}
	public void setIntergralType(String intergralType) {
		this.intergralType = intergralType;
	}
	public String getOrderUuid() {
		return orderUuid;
	}
	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getCmobile() {
		return cmobile;
	}
	public void setCmobile(String cmobile) {
		this.cmobile = cmobile;
	}
}
