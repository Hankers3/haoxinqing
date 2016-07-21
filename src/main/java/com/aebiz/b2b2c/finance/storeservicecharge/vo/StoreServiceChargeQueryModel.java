package com.aebiz.b2b2c.finance.storeservicecharge.vo;

public class StoreServiceChargeQueryModel extends StoreServiceChargeModel {
	private String createTime2;
	
	/* 商户名称或者商户编号 */
	private String storeNameOrNo = "";

	public String getStoreNameOrNo() {
		return storeNameOrNo;
	}
	public void setStoreNameOrNo(String storeNameOrNo) {
		this.storeNameOrNo = storeNameOrNo;
	}
	
	public void setCreateTime2(String obj){
		this.createTime2 = obj;
	}
	public String getCreateTime2(){
		return this.createTime2;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[createTime2=" + this.getCreateTime2() + ",]";
	}
}
