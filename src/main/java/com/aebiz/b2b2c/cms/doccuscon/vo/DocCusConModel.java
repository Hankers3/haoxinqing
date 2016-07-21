package com.aebiz.b2b2c.cms.doccuscon.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 医生患者文章关联表
 * @author szr
 *
 */
@Entity
@Table(name="doc_cus_con")
public class DocCusConModel extends BaseModel{
	/* 医生Uuid */
	private String doctorUuid;
	/* 患者Uuid */
	private String customerUuid;
	/* 文章Uuid */
	private String contentUuid;
	
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
	
	public void setContentUuid(String obj){
		this.contentUuid = obj;
	}
	public String getContentUuid(){
		return this.contentUuid;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[doctorUuid=" + this.getDoctorUuid() + ",customerUuid=" + this.getCustomerUuid() + ",contentUuid=" + this.getContentUuid() + ",]";
	}	
}
