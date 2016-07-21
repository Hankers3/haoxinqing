package com.aebiz.b2b2c.websiteoperation.symptomstructure.vo;

public class SymptomStructureQueryModel extends SymptomStructureModel {
	private String secondaryClassification;
	
	public void setSecondaryClassification(String obj){
		this.secondaryClassification = obj;
	}
	public String getSecondaryClassification(){
		return this.secondaryClassification;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[secondaryClassification=" + this.getSecondaryClassification() + ",]";
	}
}
