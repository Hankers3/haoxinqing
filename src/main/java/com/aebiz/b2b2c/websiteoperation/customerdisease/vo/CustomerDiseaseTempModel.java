package com.aebiz.b2b2c.websiteoperation.customerdisease.vo;

import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 商品导入临时 信息表
 * @author wenchao
 *
 */
@Component
public class CustomerDiseaseTempModel extends BaseModel {
    
    /*疾病分类*/
    private String diseaseCategory;
    /*疾病名称*/
    private String diseaseName;
    /*定义和概述*/
    private String diseaseDescription;
    /*病因和发病机制*/
    private String pathogeny;
    /*临床表现*/
    private String clinicalFeature;
    /*治疗*/
    private String remedy;
    /*发病与预后*/
    private String morbidity;
    /*预防*/
	private String precaution;
	        
    public String getDiseaseCategory() {
        return diseaseCategory;
         }
    public void setDiseaseCategory(String diseaseCategory) {
        this.diseaseCategory = diseaseCategory;
        }
    public String getDiseaseName() {
         return diseaseName;
       }
    public void setDiseaseName(String diseaseName) {
         this.diseaseName = diseaseName;
       }
    public String getDiseaseDescription() {
         return diseaseDescription;
       }
    public void setDiseaseDescription(String diseaseDescription) {
         this.diseaseDescription = diseaseDescription;
       }
    public String getPathogeny() {
         return pathogeny;
      }
    public void setPathogeny(String pathogeny) {
        this.pathogeny = pathogeny;
      }
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


}
	
	