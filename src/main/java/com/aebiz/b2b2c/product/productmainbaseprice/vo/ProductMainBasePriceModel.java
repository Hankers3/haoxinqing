package com.aebiz.b2b2c.product.productmainbaseprice.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 商品基础价格实体类
 * @author huangpinpin
 *
 */
@Entity
@Table(name="product_main_base_price")
public class ProductMainBasePriceModel extends BaseModel{
	
	/* 商品编号*/
	private String productUuid;
	
	/* 市场价*/
	private double marketPrice;
	
	/* 商城价*/
	private double shopPrice;
	
	/* 状态*/
	private String state;
	
	/* 有效开始时间*/
	private String startTime;
	
	/* 有效结束时间*/
	private String endTime;

	/*商品计费单位*/
	private String chargetype ;
	
	public String getChargetype() {
		return chargetype;
	}
	
	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}
	
	public void setProductUuid(String obj){
		this.productUuid = obj;
	}
	public String getProductUuid(){
		return this.productUuid;
	}
	
	public void setMarketPrice(double obj){
		this.marketPrice = obj;
	}
	public double getMarketPrice(){
		return this.marketPrice;
	}
	
	public void setShopPrice(double obj){
		this.shopPrice = obj;
	}
	public double getShopPrice(){
		return this.shopPrice;
	}
	
	public void setState(String obj){
		this.state = obj;
	}
	public String getState(){
		return this.state;
	}
	
	public void setStartTime(String obj){
		this.startTime = obj;
	}
	public String getStartTime(){
		return this.startTime;
	}
	
	public void setEndTime(String obj){
		this.endTime = obj;
	}
	public String getEndTime(){
		return this.endTime;
	}
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[productUuid=" + this.getProductUuid() + ",marketPrice=" + this.getMarketPrice() + ",shopPrice=" + this.getShopPrice() + ",state=" + this.getState() + ",startTime=" + this.getStartTime() + ",endTime=" + this.getEndTime() + ",chargetype=" + this.getChargetype() + ",]";
	}	
}
