package com.aebiz.b2b2c.order.ordermainlog.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.SysManagerService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;

@Entity
@Table(name = "order_main_log")
@Component
public class OrderMainLogModel extends BaseModel {
	
	/*注入管理员service*/
	@Transient
	private static SysManagerService sysManagerService;

	@Autowired
	public void setSysManagerService(SysManagerService sms) {
		this.sysManagerService = sms;
	}
	
	/*会员service*/
	@Transient
	private static CustomerService customerService ;
	
	@Autowired
	public  void setCustomerService(CustomerService cs) {
		this.customerService = cs;
	}
	
	/*家政员service*/
	@Transient
	private static ServicestaffService servicestaffService ;
	
	@Autowired
	public  void setServicestaffService(ServicestaffService servicestaffService) {
		this.servicestaffService = servicestaffService;
	}
	
	/* 订单编号 */
	private String orderMainUuid;

	/* 操作人 */
	private String orderOper;

	/* 操作人类型  1:客服 2：家政员 3：会员  4:系统操作*/
	private String orderOperType;

	/* 操作时间 */
	private String orderOpeTime;

	/* 操作类型   */
	private String opeType;

	/* 操作前内容 */
	private String beforeContent;

	/* 操作后内容 */
	private String afterContent;

	/* 电脑的IP */
	private String ip;

	/* 电脑的名称 */
	private String computerName;

	/* mac地址 */
	private String computerMac;

	/* 取消原因 */
	private String note;
	
	/*订单的操作类型  0客服紧急处理 1提交订单  2取消订单   3支付完成      4接单完成     5上门 
 		6已到达   7家政员丈量   8开始工作    9工作完成   10家政员代付完成    11会员确认完成   
 		12无效订单      13:客服添加订单记录 14:客服推送订单 15：客服删除队长 16：客服下单   
 		17：申请退款  18：审核退款  19：退款   20：订单互评  21：会员评价 22:添加投诉 23 投诉已处理 24 回访成功
 	*/
	private String operateType;
	
	/*订单处理状态：1：已有人处理 */
	private String ugencyState;
	
	/*操作员姓名*/
	@Transient
	private String orderOperName= "" ;
	/*订单状态*/
	private String orderState;
	
	/*支付方式*/
	private String payType;
	
	
	/*操作人类型 名称*/
	@Transient
	private String operTypeName="";
	
	public String getOperTypeName() {
		operTypeName = "";
		if("1".equals(orderOperType)){
			SysManagerModel sm = sysManagerService.getByUuid(this.orderOper);
			if(sm !=null){
				operTypeName = sm.getDeptName();
			}
		}else if("2".equals(orderOperType)){
			operTypeName="家政员";
		}else if("3".equals(orderOperType)){
			operTypeName="会员";
		}
		return operTypeName;
	}

	public void setOperTypeName(String operTypeName) {
		this.operTypeName = operTypeName;
	}

	
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	
	public String getOrderOperName() {
		String name = "";
		/*if("1".equals(this.orderOperType)){
			name = sysManagerService.getSysManagerNameByUuid(this.orderOper);
		}else if("2".equals(this.orderOperType)){
			name = servicestaffService.getServiceStaffNameByUuid(this.orderOper);
		}else if("3".equals(this.orderOperType)){
			name = customerService.getCustomerNameByCustomerUuid(this.orderOper);
		}	*/
	
		if(!StringUtil.isEmpty(name)){
			return name ;
		}
		return this.orderOper;
	}

	public void setOrderOperName(String orderOperName) {
		this.orderOperName = orderOperName;
	}

	public String getUgencyState() {
		return ugencyState;
	}

	public void setUgencyState(String ugencyState) {
		this.ugencyState = ugencyState;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public void setOrderMainUuid(String obj) {
		this.orderMainUuid = obj;
	}

	public String getOrderMainUuid() {
		return this.orderMainUuid;
	}

	public void setOrderOper(String obj) {
		this.orderOper = obj;
	}

	public String getOrderOper() {
		return this.orderOper;
	}

	public void setOrderOpeTime(String obj) {
		this.orderOpeTime = obj;
	}

	public String getOrderOpeTime() {
		return this.orderOpeTime;
	}

	public void setOpeType(String obj) {
		this.opeType = obj;
	}

	public String getOpeType() {
		return this.opeType;
	}

	public void setBeforeContent(String obj) {
		this.beforeContent = obj;
	}

	public String getBeforeContent() {
		return this.beforeContent;
	}

	public void setAfterContent(String obj) {
		this.afterContent = obj;
	}

	public String getAfterContent() {
		return this.afterContent;
	}

	public void setIp(String obj) {
		this.ip = obj;
	}

	public String getIp() {
		return this.ip;
	}

	public void setComputerName(String obj) {
		this.computerName = obj;
	}

	public String getComputerName() {
		return this.computerName;
	}

	public void setComputerMac(String obj) {
		this.computerMac = obj;
	}

	public String getComputerMac() {
		return this.computerMac;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public String getOrderOperType() {
		return orderOperType;
	}

	public void setOrderOperType(String orderOperType) {
		this.orderOperType = orderOperType;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[orderMainUuid=" + this.getOrderMainUuid() + ",orderOper="
				+ this.getOrderOper() + ",orderOpeTime="
				+ this.getOrderOpeTime() + ",opeType=" + this.getOpeType()
				+ ",beforeContent=" + this.getBeforeContent()
				+ ",afterContent=" + this.getAfterContent() + ",ip="
				+ this.getIp() + ",computerName=" + this.getComputerName()
				+ ",computerMac=" + this.getComputerMac() + ",note="
				+ this.getNote() + ",]";
	}
}
