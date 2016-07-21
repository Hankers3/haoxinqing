package com.aebiz.b2b2c.visitprecept.casegroup.vo;

public class CaseGroupQueryModel extends CaseGroupModel {
	private String groupName;
	
	public void setGroupName(String obj){
		this.groupName = obj;
	}
	public String getGroupName(){
		return this.groupName;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[groupName=" + this.getGroupName() + ",]";
	}
}
