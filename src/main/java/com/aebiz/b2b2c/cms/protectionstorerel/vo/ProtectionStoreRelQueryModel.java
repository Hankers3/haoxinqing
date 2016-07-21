package com.aebiz.b2b2c.cms.protectionstorerel.vo;

public class ProtectionStoreRelQueryModel extends ProtectionStoreRelModel {
	/*商户的id*/
	private String storeUuid;
	
	public void setStoreUuid(String obj){
		this.storeUuid = obj;
	}
	public String getStoreUuid(){
		return this.storeUuid;
	}
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[storeUuid=" + this.getStoreUuid() + ",]";
	}
}
