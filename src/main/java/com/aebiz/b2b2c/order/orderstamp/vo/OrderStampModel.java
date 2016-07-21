package com.aebiz.b2b2c.order.orderstamp.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 订单客服三方会议的录音关联关系表
 * @author xueli
 *
 */
@Entity
@Table(name = "order_stamp")
public class OrderStampModel extends BaseModel {

	/* 订单编号 */
	private String orderUuid;

	/* 会员编号 */
	private String cutomerUuid;

	/* 医生编号*/
	private String doctocUuid;

	/* 创建时间*/
	private String createTime;

	/* 音频的url */
	private String medurl;
	
	/* 音频时长 */
	private String recordduration;
	
	/*会议Id*/
	private String confId;
	
	public String getConfId() {
		return confId;
	}

	public void setConfId(String confId) {
		this.confId = confId;
	}

	public void setOrderUuid(String obj) {
		this.orderUuid = obj;
	}

	public String getOrderUuid() {
		return this.orderUuid;
	}

	public String getCutomerUuid() {
		return cutomerUuid;
	}

	public void setCutomerUuid(String cutomerUuid) {
		this.cutomerUuid = cutomerUuid;
	}

	public String getDoctocUuid() {
		return doctocUuid;
	}

	public void setDoctocUuid(String doctocUuid) {
		this.doctocUuid = doctocUuid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMedurl() {
		return medurl;
	}

	public void setMedurl(String medurl) {
		this.medurl = medurl;
	}

	public String getRecordduration() {
		return recordduration;
	}

	public void setRecordduration(String recordduration) {
		this.recordduration = recordduration;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName();
	}
}
