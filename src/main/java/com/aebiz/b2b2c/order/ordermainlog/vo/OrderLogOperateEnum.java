package com.aebiz.b2b2c.order.ordermainlog.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 
 * 订单操作类型： <br />
 * 1.创建订单：CREATE<br />
 * 2.更新订单状态:UPDATESTATE<br />
 * 3.更新订单价格:UPDATEPRICE<br />
 * 4.更新订单地址:UPDATEADDRESS<br />
 * 5.会员取消订单:CANCELBYUSER<br />
 * 6.商户取消订单:CANCELBYSTORE<br />
 * 7.系统取消订单:CANCELBYSYSTEM<br />
 * 8.删除订单:DELETE<br />
 * 9.订单退换货关闭订单：BACKGOODS<br />
 * 10.延长收货时间：DELAYCONFIRM
 * 
 * @author duandeyi
 * 
 */
public enum OrderLogOperateEnum {

	CREATE(MessageHelper.getMessage("orderlog.operate.create"), "1"), UPDATESTATE(
			MessageHelper.getMessage("orderlog.operate.updatestate"), "2"), UPDATEPRICE(
			MessageHelper.getMessage("orderlog.operate.updateprice"), "3"), UPDATEADDRESS(
			MessageHelper.getMessage("orderlog.operate.updateaddress"), "4"), CANCELBYUSER(
			MessageHelper.getMessage("orderlog.operate.cancelbyuser"), "5"), CANCELBYSTORE(
			MessageHelper.getMessage("orderlog.operate.cancelbystore"), "6"), CANCELBYSYSTEM(
			MessageHelper.getMessage("orderlog.operate.cancelbysystem"), "7"), DELETE(
			MessageHelper.getMessage("orderlog.operate.delete"), "8"), BACKGOODS(
			MessageHelper.getMessage("orderlog.operate.bakcgoods"), "9"), DELAYCONFIRM(
			MessageHelper.getMessage("orderlog.operate.delayconfirm"), "10");

	String value = "";
	String name = "";

	private OrderLogOperateEnum(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getNameByKey(String value) {
		for (OrderLogOperateEnum spe : OrderLogOperateEnum.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}
}
