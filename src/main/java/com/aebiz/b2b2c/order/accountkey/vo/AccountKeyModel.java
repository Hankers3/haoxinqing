package com.aebiz.b2b2c.order.accountkey.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="account_key")
public class AccountKeyModel extends BaseModel{
	private String id;
	private String value;
	
	public void setId(String obj){
		this.id = obj;
	}
	public String getId(){
		return this.id;
	}
	
	public void setValue(String obj){
		this.value = obj;
	}
	public String getValue(){
		return this.value;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[id=" + this.getId() + ",value=" + this.getValue() + ",]";
	}	
}
