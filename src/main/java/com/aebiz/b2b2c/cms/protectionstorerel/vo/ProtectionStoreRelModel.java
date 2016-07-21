package com.aebiz.b2b2c.cms.protectionstorerel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="protection_store_rel")
public class ProtectionStoreRelModel extends BaseModel{
	
	/*权益编号*/
	private String protectionUuid;
	/*商户编号*/
	private String storeUuid;
	
	public void setProtectionUuid(String obj){
		this.protectionUuid = obj;
	}
	public String getProtectionUuid(){
		return this.protectionUuid;
	}
	
	public void setStoreUuid(String obj){
		this.storeUuid = obj;
	}
	public String getStoreUuid(){
		return this.storeUuid;
	}
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[protectionUuid=" + this.getProtectionUuid() + ",storeUuid=" + this.getStoreUuid() + ",]";
	}	
}
