package com.aebiz.b2b2c.websiteoperation.customerdisease.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 
 * @Description: (疾病表)    
 * @author XP  
 * @date 2016-1-8 上午11:21:53
 */
@Entity
@Table(name="customer_disease")
@Component
public class CustomerDiseaseModel extends BaseModel{
        /*疾病分类*/
	private String diseaseCategory;
	/*疾病名称*/
	private String diseaseName;
	/*定义和概述*/
	private String diseaseDescription;
	/*病因和发病机制*/
	private String pathogeny;
	
	/*临床表现*/
	@Transient
	private String clinicalFeature;
	/*治疗*/
	@Transient
	private String remedy;
	/*发病与预后*/
	@Transient
	private String morbidity;
	/*预防*/
	@Transient
	private String precaution;
	
	public String getClinicalFeature() {
		return clinicalFeature;
	}
	public void setClinicalFeature(String clinicalFeature) {
		this.clinicalFeature = clinicalFeature;
	}
	public String getRemedy() {
		return remedy;
	}
	public void setRemedy(String remedy) {
		this.remedy = remedy;
	}
	public String getMorbidity() {
		return morbidity;
	}
	public void setMorbidity(String morbidity) {
		this.morbidity = morbidity;
	}
	public String getPrecaution() {
		return precaution;
	}
	public void setPrecaution(String precaution) {
		this.precaution = precaution;
	}
	public void setDiseaseCategory(String obj){
		this.diseaseCategory = obj;
	}
	public String getDiseaseCategory(){
		return this.diseaseCategory;
	}
	
	public void setDiseaseName(String obj){
		this.diseaseName = obj;
	}
	public String getDiseaseName(){
		return this.diseaseName;
	}
	
	public void setDiseaseDescription(String obj){
		this.diseaseDescription = obj;
	}
	public String getDiseaseDescription(){
	      return this.diseaseDescription;
	}
	
	public void setPathogeny(String obj){
		this.pathogeny = obj;
	}
	public String getPathogeny(){
	    return this.pathogeny;
	}
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[diseaseCategory=" + this.getDiseaseCategory() + ",diseaseName=" + this.getDiseaseName() + ",diseaseDescription=" + this.getDiseaseDescription() + ",pathogeny=" + this.getPathogeny() + ",]";
	}	
}
