package com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 医生常用项表
 * @author xp
 *
 */
@Entity
@Table(name="visit_customer_common")
public class VisitCustomerCommonModel extends BaseModel{
	/*医生的uuid*/
	private String serviceStatffUuid;
	/*常用项内容*/
	private String content;
	/*创建时间*/
	private String createTime;
	
	public void setServiceStatffUuid(String obj){
		this.serviceStatffUuid = obj;
	}
	public String getServiceStatffUuid(){
		return this.serviceStatffUuid;
	}
	
	public void setContent(String obj){
		this.content = obj;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[serviceStatffUuid=" + this.getServiceStatffUuid() + ",content=" + this.getContent() + ",createTime=" + this.getCreateTime() + ",]";
	}	
}
