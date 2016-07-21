package com.aebiz.b2b2c.product.productmaindescription.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 
 * @Description: (药品信息扩展表)    
 * @author XP  
 * @date 2015-12-29 下午9:46:37
 */
@Entity
@Table(name="product_main_description")
@Component
public class ProductMainDescriptionModel extends BaseModel{
	public static final String USE_DEFUALT = "1";
	public static final String NO_USE_DEFUALT = "0";
	
	/* 商品uuid*/
	private String productUuid;
	
	/* 是否使用店铺售后服务0-不使用 1-使用*/
	private String useDefult="1";
	
	/* 应该做的化验检查 */
        private String laboratorExamination="";
	
        /* 注意事项 */
        private String attention;
	
        /* 药物相互作用 */
        private String drugInteractio;

        /* 商品描述*/
	private String description;
	
	/* 商品简介*/
	private String note;
	
	public String getLaboratorExamination() {
            return laboratorExamination;
        }
        public void setLaboratorExamination(String laboratorExamination) {
            this.laboratorExamination = laboratorExamination;
        }
        public String getAttention() {
            return attention;
        }
        public void setAttention(String attention) {
            this.attention = attention;
        }
        public String getDrugInteractio() {
            return drugInteractio;
        }
        public void setDrugInteractio(String drugInteractio) {
            this.drugInteractio = drugInteractio;
        }
	
	public String getUseDefult() {
		return useDefult;
	}
	public void setUseDefult(String useDefult) {
		this.useDefult = useDefult;
	}
	public void setProductUuid(String obj){
		this.productUuid = obj;
	}
	public String getProductUuid(){
		return this.productUuid;
	}
	
	public void setDescription(String obj){
		this.description = obj;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setNote(String obj){
		this.note = obj;
	}
	public String getNote(){
		return this.note;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[productUuid=" + this.getProductUuid() + ",description=" + this.getDescription() + ",note=" + this.getNote() + ",]";
	}	
}
