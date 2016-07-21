package com.aebiz.b2b2c.servicestaff.telephonecounse.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.telephonecost.service.TelephoneCostService;
import com.aebiz.b2b2c.servicestaff.telephonecost.vo.TelephoneCostModel;
import com.aebiz.b2b2c.servicestaff.telephonetime.service.TelephoneTimeService;
import com.aebiz.b2b2c.servicestaff.telephonetime.vo.TelephoneTimeModel;
/**
 * 电话咨询设置信息表
 * @author xueli
 *
 */
@Entity
@Table(name="telephone_counse")
@Component
public class TelephoneCounseModel extends BaseModel{
	
	/*注解电话咨询费用信息表*/
	@Transient
	private static TelephoneCostService teleCostService;
	@Autowired
	public void setTelephoneCostService(TelephoneCostService teleCostService) {
		this.teleCostService = teleCostService;
	}
	
	/*注解电话时长信息表*/
	@Transient
	private static TelephoneTimeService teleTimeService;
	@Autowired
	public void setTelephoneCostService(TelephoneTimeService teleTimeService) {
		this.teleTimeService = teleTimeService;
	}

	/*医生编号*/
	private String doctorUuid;
	
	/*星期*/
	private String weekDate;
	
	/*开始时间*/
	private String startTime;
	
	/*结束时间*/
	private String endTime;
	
	/*咨询时长 编号*/
	private String teleTimeUuid;
	
	/*咨询费用 编号*/
	private String teleCostUuid;
	
	/*咨询时长*/
	@Transient
	private String teleTime;
	
	/*咨询费用*/
	@Transient
	private String teleCostl;
	
	public String getTeleTime() {
		if(!StringUtil.isEmpty(teleTimeUuid) ){
			TelephoneTimeModel telePhone =  teleTimeService.getByUuid(teleTimeUuid);
			if(telePhone !=null ){
				return telePhone.getTelTime();
			}
		}
		return teleTime;
	}
	public void setTeleTime(String teleTime) {
		this.teleTime = teleTime;
	}
	public String getTeleCostl() {
		if(!StringUtil.isEmpty(teleCostUuid) ){
			TelephoneCostModel teleCost =  teleCostService.getByUuid(teleCostUuid);
			if(teleCost !=null ){
				return teleCost.getTelCost();
			}
		}
		return teleCostl;
	}
	public void setTeleCostl(String teleCostl) {
		this.teleCostl = teleCostl;
	}
	public void setDoctorUuid(String obj){
		this.doctorUuid = obj;
	}
	public String getDoctorUuid(){
		return this.doctorUuid;
	}
	
	public void setWeekDate(String obj){
		this.weekDate = obj;
	}
	public String getWeekDate(){
		return this.weekDate;
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
	
	public void setTeleTimeUuid(String obj){
		this.teleTimeUuid = obj;
	}
	public String getTeleTimeUuid(){
		return this.teleTimeUuid;
	}
	
	public void setTeleCostUuid(String obj){
		this.teleCostUuid = obj;
	}
	public String getTeleCostUuid(){
		return this.teleCostUuid;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[doctorUuid=" + this.getDoctorUuid() + ",weekDate=" + this.getWeekDate() + ",startTime=" + this.getStartTime() + ",endTime=" + this.getEndTime() + ",teleTimeUuid=" + this.getTeleTimeUuid() + ",teleCostUuid=" + this.getTeleCostUuid() + ",]";
	}	
}
