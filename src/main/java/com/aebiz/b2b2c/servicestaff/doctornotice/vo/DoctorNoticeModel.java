package com.aebiz.b2b2c.servicestaff.doctornotice.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;

@Entity
@Table(name="doctor_notice")
@Component
public class DoctorNoticeModel extends BaseModel{
	
	//注解医生详情的service
	@Transient 
	private static ServicestaffService servicestaffService; 
	
	@Autowired
	public  void setServicestaffService(ServicestaffService servicestaffService) {
		this.servicestaffService = servicestaffService;
	}
	
	/* 医生编号 */
	private String serviceStatffUuid;
	/* 医生名称 */
	private String doctorName;
	/* 公告标题 */
	private String line;
	/* 公告内容 */
	private String content;
	/* 发布时间 */
	private String createTime;
	
	@Transient
	/* 医生手机号 */
	private String mobile ;
	
	public String getMobile() {

	if(!StringUtil.isEmpty(serviceStatffUuid)){
			return  servicestaffService.getMobileByUuid(serviceStatffUuid);
		}
		return mobile;
	}
	
	
	public void setMobile(String mobile) {
		
		
		this.mobile = mobile;
	}
	public void setServiceStatffUuid(String obj){
		this.serviceStatffUuid = obj;
	}
	public String getServiceStatffUuid(){
		return this.serviceStatffUuid;
	}
	
	public void setDoctorName(String obj){
		this.doctorName = obj;
	}
	public String getDoctorName(){
		return this.doctorName;
	}
	
	public void setLine(String obj){
		this.line = obj;
	}
	public String getLine(){
		return this.line;
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
		return super.toString()+" , Model"+this.getClass().getName()+"[serviceStatffUuid=" + this.getServiceStatffUuid() + ",doctorName=" + this.getDoctorName() + ",line=" + this.getLine() + ",content=" + this.getContent() + ",createTime=" + this.getCreateTime() + ",]";
	}	
}
