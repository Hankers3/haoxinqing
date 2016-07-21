package com.aebiz.b2b2c.servicestaff.doctortelecoun.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
/**
 * 医生电话咨询设置信息表  
 * 用来设置电话咨询的时间 、时长、价格。
 * @author xueli
 *
 */
@Entity
@Table(name="doctor_tele_coun")
@Component
public class DoctorTeleCounModel extends BaseModel{
	/*注入医生service*/
	@Transient
	private static ServicestaffService staffService;
	
	@Autowired
	public  void setStaffService(ServicestaffService staffService) {
		this.staffService=staffService;
	}
	
	
	/*医生编号*/
	private String doctorUuid;
	
	/*医生名称*/
	@Transient
	private String doctorName;
	
	/*开始时间*/
	private String startTime;
	
	/*结束时间*/
	private String endTime;
	
	/*时长 1：15分钟 2：20分钟 3：30分钟 */
	private String duration;
	
	/*咨询费用 */
	private double price;
	
	/*备注*/
	private String note;
	
	public String getDoctorName() {
		if(!StringUtil.isEmpty(this.doctorUuid)){
			return staffService.getServiceStaffNameByUuid(doctorUuid);
		}
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public void setDoctorUuid(String obj){
		this.doctorUuid = obj;
	}
	public String getDoctorUuid(){
		return this.doctorUuid;
	}
	
	public void setStartTime(String obj){
		this.startTime = obj;
	}
	public String getStartTime(){
		return this.startTime;
	}
	
	public void setEndTime(String obj){
		this.endTime = obj;
	}
	public String getEndTime(){
		return this.endTime;
	}
	
	public void setDuration(String obj){
		this.duration = obj;
	}
	public String getDuration(){
		return this.duration;
	}
	
	public void setPrice(double obj){
		this.price = obj;
	}
	public double getPrice(){
		return this.price;
	}
	
	public void setNote(String obj){
		this.note = obj;
	}
	public String getNote(){
		return this.note;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[doctorUuid=" + this.getDoctorUuid() + ",startTime=" + this.getStartTime() + ",endTime=" + this.getEndTime() + ",duration=" + this.getDuration() + ",price=" + this.getPrice() + ",note=" + this.getNote() + ",]";
	}	
}
