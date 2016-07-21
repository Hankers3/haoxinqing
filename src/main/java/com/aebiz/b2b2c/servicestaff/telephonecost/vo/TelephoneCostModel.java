package com.aebiz.b2b2c.servicestaff.telephonecost.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 
 * 电话咨询的费用
 * 
 */
@Entity
@Table(name="telephone_cost")
public class TelephoneCostModel extends BaseModel{
	/*电话咨询费用*/
	private String telCost;
	/*电话咨询状态*/
	private String state;
	
	public void setTelCost(String obj){
		this.telCost = obj;
	}
	public String getTelCost(){
		return this.telCost;
	}
	
	public void setState(String obj){
		this.state = obj;
	}
	public String getState(){
		return this.state;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[telCost=" + this.getTelCost() + ",state=" + this.getState() + ",]";
	}	
}
