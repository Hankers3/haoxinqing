package com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo;

public class VisitPreceptExtendQueryModel extends VisitPreceptExtendModel {
	private String preceptUuid;
	
	public void setPreceptUuid(String obj){
		this.preceptUuid = obj;
	}
	public String getPreceptUuid(){
		return this.preceptUuid;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[preceptUuid=" + this.getPreceptUuid() + ",]";
	}
}
