package com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo;

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
 * 用户积分统计表
 * 
 * @author huyingying
 * 
 */
@Entity
@Table(name = "vipclub_Integral_stat")
@Component
public class VipclubIntegralStatModel extends BaseModel {
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

	/* 会员Uuid */
	private String customerUuid;

	/* 会员名 */
	@Transient
	private String customerName;
	
	/*用户类型 1：会员 2：医生*/
	private String userType;
	
	/*
	 * 积分类型 1：可用积分 2：已用积分 3：累计积分 4：购物获得积分 5：行为获得积分 6：奖励获得积分
	 */
	private String intergralType;

	/* 积分总数 */
	private int intergralCount;
	
	/* 会员手机号 */
	private String cmobile;
	

	public String getCmobile() {
        return cmobile;
    }

	  public void setCmobile(String cmobile) {
        this.cmobile = cmobile;
    }

	    public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCustomerName() {
		//用户类型如果是2 代表是医生
		if(!StringUtil.isEmpty(this.userType)&& "2".equals(userType)){
			return servicestaffService.getServiceStaffNameByUuid(this.getCustomerUuid());
		}else{
			return customerService.getCustomerNameByCustomerUuid(this
					.getCustomerUuid());
		}	
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	/**
	 * toString方法
	 * @return String
	 * 
	 */
	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[customerUuid=" + this.getCustomerUuid() + ",intergralType="
				+ this.getIntergralType() + ",intergralCount="
				+ this.getIntergralCount() + ",]";
	}
}
