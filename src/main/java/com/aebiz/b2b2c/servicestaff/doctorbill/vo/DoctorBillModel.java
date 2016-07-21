package com.aebiz.b2b2c.servicestaff.doctorbill.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
/**
 * 医生账单信息 
 * @author xueli
 *
 */
@Entity
@Table(name="doctor_bill")
@Component
public class DoctorBillModel extends BaseModel{
	/*交易类型 - 入账 */
	public static final String DOCTOR_DEAL_ENTER="1";
	
	/*交易类型 -提现 */
	public static final String DOCTOR_DEAL_DEPOSIT="2";
	
	/*提现处理 - 未处理 */
	public static final String DEPOSIT_STATE_UNDEAL="0";
	
	/*提现处理 - 已处理*/
	public static final String DEPOSIT_STATE_DEAL="1";
	
	/*注入医生service*/
	@Transient
	private static ServicestaffService staffService;
	@Autowired
	public void setStaffService(ServicestaffService staffService) {
		this.staffService = staffService;
	}
	
	/*医生编号*/
	private String doctorUuid;
	
	/*医生名*/
	@Transient
	private String doctorName;
	
	/*银行编号*/
	private String bankUuid;
	
	/*结算单号*/
	private String settlementUuid;
	
	/*交易类型 1：入账 2：提现*/
	private String dealType;
	
	/*金额*/
	private double money;
	
	/*处理状态 0：未处理 1：处理成功*/
	private String state;
	
	/*创建时间*/
	private String createTime;
	

	/*备注*/
	private String note;
	
	public String getDoctorName() {
		if(!StringUtil.isEmpty(this.doctorUuid)){
			return staffService.getServiceStaffNameByUuid(this.doctorUuid);
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
	
	public void setBankUuid(String obj){
		this.bankUuid = obj;
	}
	public String getBankUuid(){
		return this.bankUuid;
	}
	
	public void setSettlementUuid(String obj){
		this.settlementUuid = obj;
	}
	public String getSettlementUuid(){
		return this.settlementUuid;
	}
	
	public void setDealType(String obj){
		this.dealType = obj;
	}
	public String getDealType(){
		return this.dealType;
	}
	
	public void setMoney(double obj){
		this.money = obj;
	}
	public double getMoney(){
		return this.money;
	}
	
	public void setState(String obj){
		this.state = obj;
	}
	public String getState(){
		return this.state;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setNote(String obj){
		this.note = obj;
	}
	public String getNote(){
		return this.note;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[doctorUuid=" + this.getDoctorUuid() + ",bankUuid=" + this.getBankUuid() + ",settlementUuid=" + this.getSettlementUuid() + ",dealType=" + this.getDealType() + ",money=" + this.getMoney() + ",state=" + this.getState() + ",createTime=" + this.getCreateTime() + ",note=" + this.getNote() + ",]";
	}	
}
