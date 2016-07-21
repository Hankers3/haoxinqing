package com.aebiz.b2b2c.visitprecept.drugreaction.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordModel;
/**
 * 药物不良反应表
 * @author wangbingning
 *
 */
@Entity
@Table(name="drug_reaction")
public class DrugReactionModel extends BaseModel{
	
	
	/*注入随访表的service*/
	@Transient
	private static VisitRecordService visitRecordService;
	@Autowired
	public void setStaffService(VisitRecordService visitRecordService) {
		this.visitRecordService = visitRecordService;
	}
	
	
	/*随访编号*/
	private String visitRecordUuid;
	/*影响*/
	private String impact;
	/*症状描述*/
	private String frequency;
	/*持续时间*/
	private String dosageTime;
	/*发生时间*/
	private String occurrenceTime;
	/*创建时间*/
	private String createTime;
	
	/*医生编号*/
	@Transient
	private String serviceStaffUuid;
	
	
	public String getServiceStaffUuid() {
    if(!StringUtil.isEmpty(visitRecordUuid)){
     VisitRecordModel  v = visitRecordService.getByUuid(visitRecordUuid);
	 return v.getServiceStaffUuid();
		 }
		return serviceStaffUuid;
	  }
	public void setServiceStaffUuid(String serviceStaffUuid) {
		this.serviceStaffUuid = serviceStaffUuid;
	}


	/*患者编号*/
	@Transient
	private String customerUuid;
	
	public String getCustomerUuid() {
    if(!StringUtil.isEmpty(visitRecordUuid)){
	 VisitRecordModel  v = visitRecordService.getByUuid(visitRecordUuid);
	    	   return v.getCustomerUuid();
				}
	   return customerUuid;
	   }
	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
	public void setVisitRecordUuid(String obj){
		this.visitRecordUuid = obj;
	}
	public String getVisitRecordUuid(){
		return this.visitRecordUuid;
	}
	
	public void setImpact(String obj){
		this.impact = obj;
	}
	public String getImpact(){
		return this.impact;
	}
	
	public void setFrequency(String obj){
		this.frequency = obj;
	}
	public String getFrequency(){
		return this.frequency;
	}
	
	public void setDosageTime(String obj){
		this.dosageTime = obj;
	}
	public String getDosageTime(){
		return this.dosageTime;
	}
	
	public void setOccurrenceTime(String obj){
		this.occurrenceTime = obj;
	}
	public String getOccurrenceTime(){
		return this.occurrenceTime;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[visitRecordUuid=" + this.getVisitRecordUuid() + ",impact=" + this.getImpact() + ",frequency=" + this.getFrequency() + ",dosageTime=" + this.getDosageTime() + ",occurrenceTime=" + this.getOccurrenceTime() + ",createTime=" + this.getCreateTime() + ",]";
	}	
}
