package com.aebiz.b2b2c.websiteoperation.customerservice.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 
 * @Description: (客服表)    
 * @author XP  
 * @date 2016-1-7 上午10:36:27
 */
@Entity
@Table(name="customer_service")
@Component
public class CustomerServiceModel extends BaseModel{
        /*客服姓名*/
	private String customerServiceName;
	/*客服的开通状态 0 是未开通 1是已开通*/
	private String state;
	 /*客服电话*/
	private String customerServiceMobile;
	
	public void setCustomerServiceName(String obj){
		this.customerServiceName = obj;
	}
	public String getCustomerServiceName(){
		return this.customerServiceName;
	}
	
	public void setState(String obj){
		this.state = obj;
	}
	public String getState(){
		return this.state;
	}
	
	public void setCustomerServiceMobile(String obj){
		this.customerServiceMobile = obj;
	}
	public String getCustomerServiceMobile(){
		return this.customerServiceMobile;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[customerServiceName=" + this.getCustomerServiceName() + ",state=" + this.getState() + ",customerServiceMobile=" + this.getCustomerServiceMobile() + ",]";
	}	
}
