package com.aebiz.b2b2c.servicestaff.servicestaffsource.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 
 * @author hedongfei
 *
 */
@Entity
@Table(name="service_staff_source")
public class ServicestaffsourceModel extends BaseModel{
	/*客户端来源*/
	private String clientType;
	
	/*来源站点*/
	private String siteType;
	
	/*第三方类型*/
	private String thirdPlatType;
	
	/*来源地址*/
	private String formUrl;
	
	/*推荐人*/
	private String introducer;
	
	/*会员编号*/
	private String serviceStaffUuid;
	
	
	
	
	public void setClientType(String obj){
		this.clientType = obj;
	}
	public String getClientType(){
		return this.clientType;
	}
	
	public void setSiteType(String obj){
		this.siteType = obj;
	}
	public String getSiteType(){
		return this.siteType;
	}
	
	public void setThirdPlatType(String obj){
		this.thirdPlatType = obj;
	}
	public String getThirdPlatType(){
		return this.thirdPlatType;
	}
	
	public void setFormUrl(String obj){
		this.formUrl = obj;
	}
	public String getFormUrl(){
		return this.formUrl;
	}
	
	public void setIntroducer(String obj){
		this.introducer = obj;
	}
	public String getIntroducer(){
		return this.introducer;
	}
	
	public void setServiceStaffUuid(String obj){
		this.serviceStaffUuid = obj;
	}
	public String getServiceStaffUuid(){
		return this.serviceStaffUuid;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[clientType=" + this.getClientType() + ",siteType=" + this.getSiteType() + ",thirdPlatType=" + this.getThirdPlatType() + ",formUrl=" + this.getFormUrl() + ",introducer=" + this.getIntroducer() + ",serviceStaffUuid=" + this.getServiceStaffUuid() + ",]";
	}	
}
