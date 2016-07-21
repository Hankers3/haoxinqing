package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.wxcommon.repository.AbstractEntity;

/**
 * 
 * @Description: (药品信息扩展表)    
 * @author 
 * @date 
 */
@SuppressWarnings("serial")
@Entity(name="product_main_description")
public class ProductMainDescription extends AbstractEntity<String> {
	
	@Transient
	public static final String USE_DEFUALT = "1";
	@Transient
	public static final String NO_USE_DEFUALT = "0";
	
	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
    public String uuid;
	/* 商品uuid*/
	public String productUuid;
	/* 是否使用店铺售后服务0-不使用 1-使用*/
	@Column(columnDefinition="default 1")
	public String useDefult;
	/* 应该做的化验检查 */
    public String laboratorExamination="";
    /* 注意事项 */
    public String attention;
	/* 药物相互作用 */
	public String drugInteractio;
    /* 商品描述*/
	public String description;
	/* 商品简介*/
	public String note;
	
	public ProductMainDescription() {super();}
	public ProductMainDescription(String uuid) {
		super();
		this.uuid = uuid;
	}
	@Override
	public String getId() {
		return uuid;
	}
	@Override
	public void setId(String id) {
		this.uuid = id;	
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getProductUuid() {
		return productUuid;
	}
	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}
	public String getUseDefult() {
		return useDefult;
	}
	public void setUseDefult(String useDefult) {
		this.useDefult = useDefult;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
