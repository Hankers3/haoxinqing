package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.wxcommon.repository.AbstractEntity;

/**
 * 用户积分日志实体类
 */
@SuppressWarnings("serial")
@Entity(name="vipclub_integral_log")
public class VipclubIntegralLog extends AbstractEntity<String> {
	/* 患者类型 */
	@Transient
	public static final String VIPCLUB_USERTYPE_CUS = "1";
	/* 医生类型 */
	@Transient
	public static final String VIPCLUB_USERTYPE_DOC = "2";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
	@Column(columnDefinition="default 1")
	public String delFlag;
	public String opeTime;
	public String oper;
	/* 用户Uuid */
	public String customerUuid;
	/* 备注 */
	public String description;
	/* 积分数量 */
	@Column(columnDefinition="default 0")
	public Integer intergralCount;
	/*
	 * 积分类型 11：订单获取积分 12：注册获取积分 13：评论获得积分 21：支付消费积分 22：抽奖消费积分
	 */
	public String intergralType;
	/* 当前积分余额 */
	@Column(columnDefinition="default 0")
	public Integer nowIntegral;
	/* 订单号 */
	public String orderMainUuid;
	/* 过期时间 */
	public String overdueTime;
	/* 会员积分日志创建时间 */
	public String createTime;
	/* 会员积分日志商品编号 */
	public String productUuid;
	public String status;
	public String note;
	/* 用户类型 1：患者 2：医生 */
	public String userType;
	/* 查询用字段 */
	public String searchType;
	/* 会员积分日志的操作类型 1 代表 每天登陆操作 2 代表开通电话咨询服务 3代表开通预约加号服务 4代表首次完善个人资料 5 代表是其他 */
	public String operType;

	/* 积分类型名称 */
	@Transient
	public String typeName;
	/* 获取积分类型名称 */
	public String getTypeName() {
		return IntegralType.getNameByKey(this.intergralType);
	}
	public VipclubIntegralLog() {super();}
	public VipclubIntegralLog(String uuid) {
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIntergralCount() {
		return intergralCount;
	}
	public void setIntergralCount(Integer intergralCount) {
		this.intergralCount = intergralCount;
	}
	public String getIntergralType() {
		return intergralType;
	}
	public void setIntergralType(String intergralType) {
		this.intergralType = intergralType;
	}
	public Integer getNowIntegral() {
		return nowIntegral;
	}
	public void setNowIntegral(Integer nowIntegral) {
		this.nowIntegral = nowIntegral;
	}
	public String getOrderMainUuid() {
		return orderMainUuid;
	}
	public void setOrderMainUuid(String orderMainUuid) {
		this.orderMainUuid = orderMainUuid;
	}
	public String getOverdueTime() {
		return overdueTime;
	}
	public void setOverdueTime(String overdueTime) {
		this.overdueTime = overdueTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getProductUuid() {
		return productUuid;
	}
	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
}
