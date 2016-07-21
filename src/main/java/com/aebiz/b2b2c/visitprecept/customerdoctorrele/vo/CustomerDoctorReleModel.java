package com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 患者和医生关联关系表
 * @author xueli
 *
 */
@Entity
@Table(name="customer_doctor_rele")
public class CustomerDoctorReleModel extends BaseModel{
	/*分组编号*/
	private String groupUuid;
	
	/*医生编号*/
	private String doctorUuid;
	
	/*患者编号*/
	private String customerUuid;
	
	/*创建时间*/
	private String createTime;
	
	
	public void setGroupUuid(String obj){
		this.groupUuid = obj;
	}
	public String getGroupUuid(){
		return this.groupUuid;
	}
	
	public void setDoctorUuid(String obj){
		this.doctorUuid = obj;
	}
	public String getDoctorUuid(){
		return this.doctorUuid;
	}
	
	public void setCustomerUuid(String obj){
		this.customerUuid = obj;
	}
	public String getCustomerUuid(){
		return this.customerUuid;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[groupUuid=" + this.getGroupUuid() + ",doctorUuid=" + this.getDoctorUuid() + ",customerUuid=" + this.getCustomerUuid() + ",createTime=" + this.getCreateTime() + ",]";
	}	
}
