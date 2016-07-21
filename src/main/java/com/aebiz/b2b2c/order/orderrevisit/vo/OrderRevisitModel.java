package com.aebiz.b2b2c.order.orderrevisit.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.SysManagerService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
/**
 * 订单回访信息
 * @author xueli
 *
 */
@Entity
@Table(name="order_revisit")
@Component
public class OrderRevisitModel extends BaseModel{
	/*获取主订单的service*/
	@Transient
	private static OrderMainService  OrderMainService;

	@Autowired
	public void setOrderMainService(OrderMainService  orderMainServic) {
		this.OrderMainService = orderMainServic;
	}
	
	@Transient
	private static CustomerService  customerService;
	@Autowired
	public void setCustomerService(CustomerService  customerService) {
		this.customerService = customerService;
	}
	
	
	
	/**
	 * 管理员
	 */
	@Autowired
	private static SysManagerService sysManagerService ;
	
	@Autowired
	public  void setSysManagerService(SysManagerService sysService) {
		this.sysManagerService = sysService;
	}

	/*订单编号*/
	private String orderMainUuid;
	
	/*回访人*/
	private String managerUuid;
	/*客户id*/
	private String customerUuid;
	
	/*回访时间*/
	private String commentTime;
	
	/*回访内容*/
	private String content;
	
	/*回访评分 1代表非常满意 2代表满意 3代表一般 4 不满意*/
	private double revistScore;
	
	/*会员名称*/
	@Transient
	private String customerName="";
	
	/*回访人名称*/
	@Transient
	private String managerName="";
	
	/*订单id*/
	@Transient
	private String orderId="";
	
	public String getCustomerUuid() {
        return customerUuid;
    }
    public void setCustomerUuid(String customerUuid) {
        this.customerUuid = customerUuid;
    }
    public String getOrderId() {
	String orderId = OrderMainService.getOrderIdByUuid(this.orderMainUuid) ;
	if(!StringUtil.isEmpty(orderId)){
		return orderId ;
	}
	return this.orderMainUuid ;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getManagerName() {
		String name = sysManagerService.getSysManagerNameByUuid(this.managerUuid);
		if(!StringUtil.isEmpty(name)){
			return name ;
		}
		return this.managerUuid;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getCustomerName() {
		if(!StringUtil.isEmpty(customerUuid)){
			return customerService.getCustomerNameByCustomerUuid(customerUuid) ;
		}
		return customerName ;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setOrderMainUuid(String obj){
		this.orderMainUuid = obj;
	}
	public String getOrderMainUuid(){
		return this.orderMainUuid;
	}
	
	public void setManagerUuid(String obj){
		this.managerUuid = obj;
	}
	public String getManagerUuid(){
		return this.managerUuid;
	}
	
	public void setCommentTime(String obj){
		this.commentTime = obj;
	}
	public String getCommentTime(){
		return this.commentTime;
	}
	
	public void setContent(String obj){
		this.content = obj;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setRevistScore(double obj){
		this.revistScore = obj;
	}
	public double getRevistScore(){
		return this.revistScore;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[orderMainUuid=" + this.getOrderMainUuid() + ",managerUuid=" + this.getManagerUuid() + ",commentTime=" + this.getCommentTime() + ",content=" + this.getContent() + ",revistScore=" + this.getRevistScore() + ",]";
	}	
}
