package com.aebiz.b2b2c.websiteoperation.concern.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
/**
 * 记录关注信息表信息
 * @author xueli
 *
 */
@Entity
@Table(name="concern")
@Component
public class ConcernModel extends BaseModel{
	/*患者信息 service*/
	@Transient
	private static ServicestaffService  staffService;
	
	@Autowired
	public void setStaffService(ServicestaffService  staffService) {
		this.staffService = staffService;
	}
	
	//患者编号
	private String customerUuid;
	
	//医生编号
	private String doctorUuid;
	
	//关注时间
	private String createTime;
	
	//状态1是关注2是取消关注
	private String state;
	
	@Transient
	private String doctorName="";
	
	public String getDoctorName() {
		if(!StringUtil.isEmpty(doctorUuid)){
			return staffService.getServiceStaffNameByUuid(doctorUuid);
		}
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public void setCustomerUuid(String obj){
		this.customerUuid = obj;
	}
	public String getCustomerUuid(){
		return this.customerUuid;
	}
	
	public void setDoctorUuid(String obj){
		this.doctorUuid = obj;
	}
	public String getDoctorUuid(){
		return this.doctorUuid;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setState(String obj){
		this.state = obj;
	}
	public String getState(){
		return this.state;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[customerUuid=" + this.getCustomerUuid() + ",doctorUuid=" + this.getDoctorUuid() + ",createTime=" + this.getCreateTime() + ",state=" + this.getState() + ",]";
	}	
}
