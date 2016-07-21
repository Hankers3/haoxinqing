package com.aebiz.b2b2c.websiteoperation.symptomstructure.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 
 * @Description: (症状表)    
 * @author XP  
 * @date 2016-1-8 下午2:46:07
 */
@Entity
@Table(name="symptom_structure")
@Component
public class SymptomStructureModel extends BaseModel{
        /*一级分类*/
	private String firstClassification;
	/*二级分类*/
	private String secondaryClassification;
	/*症状*/
	private String symptom;
	/*细分症状*/
	private String breakdownSymptom;
	
	public void setFirstClassification(String obj){
		this.firstClassification = obj;
	}
	public String getFirstClassification(){
		return this.firstClassification;
	}
	
	public void setSecondaryClassification(String obj){
		this.secondaryClassification = obj;
	}
	public String getSecondaryClassification(){
		return this.secondaryClassification;
	}
	
	public void setSymptom(String obj){
		this.symptom = obj;
	}
	public String getSymptom(){
		return this.symptom;
	}
	
	public void setBreakdownSymptom(String obj){
		this.breakdownSymptom = obj;
	}
	public String getBreakdownSymptom(){
		return this.breakdownSymptom;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[firstClassification=" + this.getFirstClassification() + ",secondaryClassification=" + this.getSecondaryClassification() + ",symptom=" + this.getSymptom() + ",breakdownSymptom=" + this.getBreakdownSymptom() + ",]";
	}	
}
