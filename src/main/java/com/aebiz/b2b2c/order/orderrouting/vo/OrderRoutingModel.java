package com.aebiz.b2b2c.order.orderrouting.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.orderrouting.service.OrderRoutingService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;

/**
 * 订单的分账信息
 * 
 *  xueli
 * 
 */
@Entity
@Table(name = "order_routing")
@Component
public class OrderRoutingModel extends BaseModel {
	
	/* 分账类型 0：医生 1：平台 */
	public static final String routType_doctor = "0";
	public static final String routType_sys = "0";
	
	/* 收入类型  0：是电话咨询 ；1：是私人医生   2:加号  3:图文咨询*/
	public static final String incomeType_tel = "0";
	public static final String incomeType_pes = "0";

	/* 获取家政员相关信息 */
	@Transient
	private static ServicestaffService servicestaffService;

	@Autowired
	public void setServicestaffService(ServicestaffService staff) {
		this.servicestaffService = staff;
	}

	/* 获取订单相关信息 */
	@Transient
	private static OrderMainService orderService;

	@Autowired
	public void setOrderMainService(OrderMainService os) {
		this.orderService = os;
	}

	/* 获取分账相关信息 */
	@Transient
	private static OrderRoutingService orderroutingService;

	@Autowired
	public void setOrderroutingService(OrderRoutingService ors) {
		this.orderroutingService = ors;
	}

	/* 家政员详细信息 */
	@Transient
	private static ServicestaffinfoService servicestaffinfoService;

	@Autowired
	public void setServicestaffinfoService(ServicestaffinfoService infoService) {
		this.servicestaffinfoService = infoService;
	}

	/* 订单编号 */
	private String orderMainUuid;

	/* 家政员编号 */
	private String serviceStaffUuid;

	/* 收入类型  0：是电话咨询 ；1：是私人医生   2:加号  3:图文咨询*/
	private String incomeType;

	/* 分账时间 */
	private String routTime;

	/* 分账金额 */
	private double routPrice;

	/* 分账类型 0：医生 1：平台 */
	private String routType = "0";

	/* 订单编号 */
	@Transient
	private String orderId;

	/* 家政员姓名 */
	@Transient
	private String serviceStaffName = "";

	/* 家政员手机号 */
	@Transient
	private String serviceStaffMobile = "";

	/* 搜索的时间 */
	@Transient
	private String serachTime = "";

	public String getOrderId() {
		return orderService.getOrderIdByUuid(orderMainUuid);
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRoutType() {
		return routType;
	}

	public void setRoutType(String routType) {
		this.routType = routType;
	}

	public String getSerachTime() {
		return serachTime;
	}

	public void setSerachTime(String serachTime) {
		this.serachTime = serachTime;
	}

	public String getServiceStaffMobile() {
		if (!StringUtil.isEmpty(this.serviceStaffUuid)) {
			String staffMobile = servicestaffService
					.getMobileByUuid(this.serviceStaffUuid);
			if (!StringUtil.isEmpty(staffMobile)) {
				return staffMobile;
			}
		}
		return serviceStaffMobile;
	}

	public void setServiceStaffMobile(String serviceStaffMobile) {
		this.serviceStaffMobile = serviceStaffMobile;
	}

	public String getServiceStaffName() {
		String staffName = servicestaffService
				.getServiceStaffNameByUuid(this.serviceStaffUuid);
		if (!StringUtil.isEmpty(staffName)) {
			return staffName;
		}
		return serviceStaffName;
	}

	public void setServiceStaffName(String serviceStaffName) {
		this.serviceStaffName = serviceStaffName;
	}

	public void setOrderMainUuid(String obj) {
		this.orderMainUuid = obj;
	}

	public String getOrderMainUuid() {
		return this.orderMainUuid;
	}

	public void setServiceStaffUuid(String obj) {
		this.serviceStaffUuid = obj;
	}

	public String getServiceStaffUuid() {
		return this.serviceStaffUuid;
	}

	public void setRoutTime(String obj) {
		this.routTime = obj;
	}

	public String getRoutTime() {
		return this.routTime;
	}

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	public double getRoutPrice() {
		return routPrice;
	}

	public void setRoutPrice(double routPrice) {
		this.routPrice = routPrice;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[orderMainUuid=" + this.getOrderMainUuid()
				+ ",serviceStaffUuid=" + this.getServiceStaffUuid()
				+ ",routTime=" + this.getRoutTime() + ",routPrice="
				+ this.getRoutPrice() + ",]";
	}
}
