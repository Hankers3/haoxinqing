package com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;

/**
 * 用户积分日志实体类
 * 
 * @author huyingying
 * 
 */
@Entity
@Table(name = "vipclub_Integral_log")
@Component
public class VipclubIntegralLogModel extends BaseModel {

	/* 患者类型 */
	public static final String VIPCLUB_USERTYPE_CUS = "1";
	/* 医生类型 */
	public static final String VIPCLUB_USERTYPE_DOC = "2";

	/* 会员系统service */
	@Transient
	private static CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/* 医生系统系统service */
	@Transient
	private static ServicestaffService servicestaffService;

	@Autowired
	public void setServicestaffService(ServicestaffService servicestaffService) {
		this.servicestaffService = servicestaffService;
	}

	/* 用户名 */
	@Transient
	private String customerName;

	/* 查询用字段 */
	private String searchType;

	/* 用户Uuid */
	private String customerUuid;

	/* 用户类型 1：患者 2：医生 */
	private String userType;

	/*
	 * 积分类型 11：订单获取积分 12：注册获取积分 13：评论获得积分 21：支付消费积分 22：抽奖消费积分
	 */
	private String intergralType;

	/* 积分类型名称 */
	@Transient
	private String typeName;

	/* 积分数量 */
	private int intergralCount;

	/* 当前积分余额 */
	private int nowIntegral;

	/* 订单号 */
	private String orderMainUuid;

	/* 备注 */
	private String description;

	/* 过期时间 */
	private String overdueTime;

	/* 会员积分日志创建时间 */
	private String createTime;

	/* 会员积分日志商品编号 */
	private String productUuid;

	/* 会员积分日志的操作类型 1 代表 每天登陆操作 2 代表开通电话咨询服务 3代表开通预约加号服务 4代表首次完善个人资料 5 代表是其他 */
	private String operType;

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	/* 获取积分类型名称 */
	public String getTypeName() {
		return IntegralType.getNameByKey(this.intergralType);
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	/* 通过接口获取用户名 */
	public String getCustomerName() {
		// 用户类型如果是2 才代表是医生
		if (!StringUtil.isEmpty(this.userType) && "2".equals(userType)) {
			return servicestaffService.getServiceStaffNameByUuid(this.getCustomerUuid());
		} else {
			return customerService.getCustomerNameByCustomerUuid(this.getCustomerUuid());
		}

	}
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getProductUuid() {
		return productUuid;
	}

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setIntergralType(String obj) {
		this.intergralType = obj;
	}

	public String getIntergralType() {
		return this.intergralType;
	}

	public void setIntergralCount(int obj) {
		this.intergralCount = obj;
	}

	public int getIntergralCount() {
		return this.intergralCount;
	}

	public void setNowIntegral(int obj) {
		this.nowIntegral = obj;
	}

	public int getNowIntegral() {
		return this.nowIntegral;
	}

	public void setOrderMainUuid(String obj) {
		this.orderMainUuid = obj;
	}

	public String getOrderMainUuid() {
		return this.orderMainUuid;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public void setOverdueTime(String obj) {
		this.overdueTime = obj;
	}

	public String getOverdueTime() {
		return this.overdueTime;
	}

	/**
	 * toString方法
	 */
	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName() + "[customerUuid=" + this.getCustomerUuid()
				+ ",intergralType=" + this.getIntergralType() + ",intergralCount=" + this.getIntergralCount()
				+ ",nowIntegral=" + this.getNowIntegral() + ",orderMainUuid=" + this.getOrderMainUuid()
				+ ",description=" + this.getDescription() + ",overdueTime=" + this.getOverdueTime() + ",]";
	}
}
