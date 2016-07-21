package com.aebiz.b2b2c.order.orderdetaildiscount.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "order_detail_discount")
public class OrderDetailDiscountModel extends BaseModel {

	/* 所属订单明细 */
	private String orderDetailUuid;

	/* 优惠类型 */
	private String freeType;

	/* 优惠金额 */
	private double freeMoney;

	/* 促销活动的编号 */
	private String freeEntityUuid;

	/* 备注 */
	private String description;

	/* 如果促销是赠品促销，则保存赠送商品的编号 ，多个以;的形式隔开 */
	private String giftProductUuid;

	public void setFreeType(String obj) {
		this.freeType = obj;
	}

	public String getFreeType() {
		return this.freeType;
	}

	public void setFreeMoney(double obj) {
		this.freeMoney = obj;
	}

	public double getFreeMoney() {
		return this.freeMoney;
	}

	public void setFreeEntityUuid(String obj) {
		this.freeEntityUuid = obj;
	}

	public String getFreeEntityUuid() {
		return this.freeEntityUuid;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public String getOrderDetailUuid() {
		return orderDetailUuid;
	}

	public void setOrderDetailUuid(String orderDetailUuid) {
		this.orderDetailUuid = orderDetailUuid;
	}

	public String getGiftProductUuid() {
		return giftProductUuid;
	}

	public void setGiftProductUuid(String giftProductUuid) {
		this.giftProductUuid = giftProductUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[freeType=" + this.getFreeType() + ",freeMoney="
				+ this.getFreeMoney() + ",freeEntityUuid="
				+ this.getFreeEntityUuid() + ",description="
				+ this.getDescription() + ",]";
	}
}
