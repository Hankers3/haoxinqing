package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;

/**
 * 会员消费信心记录表
 * 
 * @author tangyunkai
 * 
 * @date 2014年12月29日 上午10:47:29
 * 
 */
@Entity
@Table(name = "virtual_account_customer_log")
@Component
public class VirtualAccountCustomerLogModel extends BaseModel {

	/* 患者service */
	@Transient
	private static CustomerInfoService customerInfoService;

	@Autowired
	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}

	/* 患者service */
	@Transient
	private static CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * 操作类型 收入
	 */
	public static final String IN = "0";

	/**
	 * 操作类型 支出
	 */
	public static final String OUT = "1";

	/* 用户编号 */
	private String customerUuid = "";

	/* 操作类型 收入,支出 */
	private String operType = "";

	/* 操作金额 */
	private double operAmount;

	/* 当前余额 */
	private double nowBalance;

	/* 订单号 */
	private String orderMainUuid = "";

	/* 备注 */
	private String description = "";

	/* 冻结状态 */
	private String frozenState = "";

	/* 患者的真实姓名 */
	@Transient
	private String customerRealName;

	/* 患者的用户名 */

        @Transient
        private String customerNickName;
        
        /* 支付方式 1：微信支付 2：支付支付 3：虚拟账户*/
        private String payType; 
    
        /* 患者消费类型  1 电话咨询 ;2私人医生*/
        private String orderType;
        
        /*订单时间*/
        private String orderTime;
        
        /* 患者的手机*/
        @Transient
        private String mobile;
        
        public String getPayType() {
            return payType;
        }
         
        public void setPayType(String payType) {
            this.payType = payType;
        }
        
        public String getMobile() {
           if(!StringUtil.isEmpty(customerUuid)){
               return  customerService.getCustomerMobileByUuid(customerUuid);
            }   
            return mobile;
        }
        
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        public String getOrderTime() {
            return orderTime;
        }
        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }
        public String getOrderType() {
            return orderType;
        }
        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }
     
        public String getCustomerNickName() {
        	if(!StringUtil.isEmpty(customerUuid)){
        		return  customerService.getCustomerNameByCustomerUuid(customerUuid);
        	}
            return customerNickName;
        }
        public void setCustomerNickName(String customerNickName) {
            this.customerNickName = customerNickName;
        }
        public String getCustomerRealName() {
        	if(!StringUtil.isEmpty(customerUuid)){
        		return  customerInfoService.getRealNameByUuid(customerUuid);
        	}
        	return customerRealName;
        }
        public void setCustomerRealName(String customerRealName) {
            this.customerRealName = customerRealName;
        }
        public void setCustomerUuid(String obj) {
        	this.customerUuid = obj;

	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setOperType(String obj) {
		this.operType = obj;
	}

	public String getOperType() {
		return this.operType;
	}

	public void setOperAmount(float obj) {
		this.operAmount = obj;
	}


	public void setNowBalance(float obj) {
		this.nowBalance = obj;
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

	public void setFrozenState(String obj) {
		this.frozenState = obj;
	}

	public String getFrozenState() {
		return this.frozenState;
	}

	
	public double getOperAmount() {
        return operAmount;
	}

        public void setOperAmount(double operAmount) {
            this.operAmount = operAmount;
        }
    
        public double getNowBalance() {
            return nowBalance;
        }
    
        public void setNowBalance(double nowBalance) {
            this.nowBalance = nowBalance;
        }
    
        public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[customerUuid=" + this.getCustomerUuid() + ",operType="
				+ this.getOperType() + ",operAmount=" + this.getOperAmount()
				+ ",nowBalance=" + this.getNowBalance() + ",orderMainUuid="
				+ this.getOrderMainUuid() + ",description="
				+ this.getDescription() + ",frozenState="
				+ this.getFrozenState() + ",]";
	}
}
