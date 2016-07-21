package com.aebiz.b2b2c.product.productmodifylog.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name="product_modify_log")
public class ProductModifyLogModel extends BaseModel{
	private String productUuid;
	private double beforePrice;
	private double endPrice;
	private String beforOther;
	private String endOther;
	
	public String getProductUuid() {
		return productUuid;
	}

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}

	public double getBeforePrice() {
		return beforePrice;
	}

	public void setBeforePrice(double beforePrice) {
		this.beforePrice = beforePrice;
	}

	public double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(double endPrice) {
		this.endPrice = endPrice;
	}

	public String getBeforOther() {
		return beforOther;
	}

	public void setBeforOther(String beforOther) {
		this.beforOther = beforOther;
	}

	public String getEndOther() {
		return endOther;
	}

	public void setEndOther(String endOther) {
		this.endOther = endOther;
	}

	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[productUuid=" + this.getProductUuid() + ",beforePrice=" + this.getBeforePrice() + ",endPrice=" + this.getEndPrice() + ",beforOther=" + this.getBeforOther() + ",endOther=" + this.getEndOther() + ",]";
	}	
}
