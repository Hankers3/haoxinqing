package com.aebiz.b2b2c.order.orderclose.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderLogOperateEnum;
/**
 * 记录订单关闭信息原因
 * @author xueli
 *
 */
@Entity
@Table(name = "order_close")
public class OrderCloseModel extends BaseModel {

	public static final String CANCELBYUSER = "5";
	public static final String CANCELBYSTORE = "6";
	public static final String CANCELBYSYSTEM = "7";
	public static final String BACKGOODS = "9";

	/* 订单编号 */
	private String orderUuid;

	/* 操作角色（买家、卖家、平台、退款关闭） */
	private String cancelMan;

	/* 操作原因 */
	private String cancelReason;

	/* 操作时间 */
	private String cancelTime;

	@Transient
	private String cancelManName;

	public String getCancelManName() {
		return OrderLogOperateEnum.getNameByKey(this.getCancelMan());
	}

	public void setCancelManName(String cancelManName) {
		this.cancelManName = cancelManName;
	}

	public void setOrderUuid(String obj) {
		this.orderUuid = obj;
	}

	public String getOrderUuid() {
		return this.orderUuid;
	}

	public void setCancelMan(String obj) {
		this.cancelMan = obj;
	}

	public String getCancelMan() {
		return this.cancelMan;
	}

	public void setCancelReason(String obj) {
		this.cancelReason = obj;
	}

	public String getCancelReason() {
		return this.cancelReason;
	}

	public void setCancelTime(String obj) {
		this.cancelTime = obj;
	}

	public String getCancelTime() {
		return this.cancelTime;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[orderUuid=" + this.getOrderUuid() + ",cancelMan="
				+ this.getCancelMan() + ",cancelReason="
				+ this.getCancelReason() + ",cancelTime="
				+ this.getCancelTime() + ",]";
	}
}
