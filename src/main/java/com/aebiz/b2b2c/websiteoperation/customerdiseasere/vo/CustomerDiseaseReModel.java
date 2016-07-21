package com.aebiz.b2b2c.websiteoperation.customerdiseasere.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="customer_disease_re")
public class CustomerDiseaseReModel extends BaseModel{
	private String diseaseUuid;
	/*临床表现*/
	private String clinicalFeature;
	/*治疗*/
	private String remedy;
	/*发病与预后*/
	private String morbidity;
	/*预防*/
	private String precaution;
	
	public void setDiseaseUuid(String obj){
		this.diseaseUuid = obj;
	}
	public String getDiseaseUuid(){
		return this.diseaseUuid;
	}
	
	public void setClinicalFeature(String obj){
		this.clinicalFeature = obj;
	}
	public String getClinicalFeature(){
		return this.clinicalFeature;
	}
	
	public void setRemedy(String obj){
		this.remedy = obj;
	}
	public String getRemedy(){
		return this.remedy;
	}
	
	public void setMorbidity(String obj){
		this.morbidity = obj;
	}
	public String getMorbidity(){
		return this.morbidity;
	}
	
	public void setPrecaution(String obj){
		this.precaution = obj;
	}
	public String getPrecaution(){
		return this.precaution;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[diseaseUuid=" + this.getDiseaseUuid() + ",clinicalFeature=" + this.getClinicalFeature() + ",remedy=" + this.getRemedy() + ",morbidity=" + this.getMorbidity() + ",precaution=" + this.getPrecaution() + ",]";
	}	
}
