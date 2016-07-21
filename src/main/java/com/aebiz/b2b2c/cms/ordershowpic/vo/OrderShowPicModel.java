package com.aebiz.b2b2c.cms.ordershowpic.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "order_show_pic")
public class OrderShowPicModel extends BaseModel {

	/* 晒单的编号 */
	private String orderShowUuid;

	/* 图片名称 */
	private String picName;

	/* 图片路径 */
	private String picUrl;

	/* 是否封面 0否 1是 */
	private String frontCover;

	/* 图片描述 */
	private String picDesc;

	public void setOrderShowUuid(String obj) {
		this.orderShowUuid = obj;
	}

	public String getOrderShowUuid() {
		return this.orderShowUuid;
	}

	public void setPicName(String obj) {
		this.picName = obj;
	}

	public String getPicName() {
		return this.picName;
	}

	public void setPicUrl(String obj) {
		this.picUrl = obj;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setFrontCover(String obj) {
		this.frontCover = obj;
	}

	public String getFrontCover() {
		return this.frontCover;
	}

	public void setPicDesc(String obj) {
		this.picDesc = obj;
	}

	public String getPicDesc() {
		return this.picDesc;
	}

	@Override
	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[orderShowUuid=" + this.getOrderShowUuid() + ",picName="
				+ this.getPicName() + ",picUrl=" + this.getPicUrl()
				+ ",frontCover=" + this.getFrontCover() + ",picDesc="
				+ this.getPicDesc() + ",]";
	}
}
