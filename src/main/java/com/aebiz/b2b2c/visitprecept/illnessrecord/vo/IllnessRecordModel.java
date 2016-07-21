package com.aebiz.b2b2c.visitprecept.illnessrecord.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
/**
 * 病情记录表 
 * @author wangbingning
 *
 */
@Entity
@Table(name="illness_record")
public class IllnessRecordModel extends BaseModel{
	
	/*注入会员service*/
	@Transient
	private static CustomerService customerService;
	@Autowired
	public void setCustomerService(
			CustomerService  customerService) {
		this.customerService = customerService;
	}
	
	/*注入医生service*/
	@Transient
	private static ServicestaffService staffService;
	@Autowired
	public void setStaffService(ServicestaffService staffService) {
		this.staffService = staffService;
	}
	/*用户编号*/
	private String customerUuid;
	/*会员名*/
	@Transient
	private String customerName;
	
	/*医生编号*/
	private String serviceStaffUuid;
	/*医生名*/
	@Transient
	private String doctorName;
	/*随访编号*/
	private String visitRecordUuid;
	/*病情变化描述 1 代表痊愈 2代表好转  3代表无效 4 代表其他*/
	private String previons;
	/*新增病症*/
	private String newCondition;
	/*创建时间*/
	private String createTime;
	/*修改时间*/
	private String updateTime;
	/*其他症状*/
	private String note;
	
	public void setCustomerUuid(String obj){
		this.customerUuid = obj;
	}
	public String getCustomerUuid(){
		return this.customerUuid;
	}
	
	public void setServiceStaffUuid(String obj){
		this.serviceStaffUuid = obj;
	}
	public String getServiceStaffUuid(){
		return this.serviceStaffUuid;
	}
	
	public void setVisitRecordUuid(String obj){
		this.visitRecordUuid = obj;
	}
	public String getVisitRecordUuid(){
		return this.visitRecordUuid;
	}
	
	public void setPrevions(String obj){
		this.previons = obj;
	}
	public String getPrevions(){
		return this.previons;
	}
	
	public void setNewCondition(String obj){
		this.newCondition = obj;
	}
	public String getNewCondition(){
		return this.newCondition;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setUpdateTime(String obj){
		this.updateTime = obj;
	}
	public String getUpdateTime(){
		return this.updateTime;
	}
	
	public void setNote(String obj){
		this.note = obj;
	}
	public String getNote(){
		return this.note;
	}
	
	public String getCustomerName() {
		if(!StringUtil.isEmpty(customerUuid)){
			return customerService.getCustomerNameByCustomerUuid(this.customerUuid);
		}
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDoctorName() {
		if(!StringUtil.isEmpty(serviceStaffUuid)){
			return staffService.getServiceStaffNameByUuid(serviceStaffUuid);
			
		}
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[customerUuid=" + this.getCustomerUuid() + ",serviceStaffUuid=" + this.getServiceStaffUuid() + ",visitRecordUuid=" + this.getVisitRecordUuid() + ",previons=" + this.getPrevions() + ",newCondition=" + this.getNewCondition() + ",createTime=" + this.getCreateTime() + ",updateTime=" + this.getUpdateTime() + ",note=" + this.getNote() + ",]";
	}	
}
