package com.aebiz.b2b2c.cms.innermessage.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 
 * 消息类型分为： <br />
 * 1.订单类型 <br />
 * 2.重要医嘱<br />
 * 3.健康指导<br />
 * 4.在线咨询<br />
 * 5.预约加号<br />
 * 6.随访申请<br />
 * 7.随访详情<br />
 * 8.随访医生<br />
 * 9.服务记录<br />
 * 10.病例<br />
 * 11.内容详情<br />
 * 
 * @author xueli
 * 
 */
public enum InnerMessageTypeEnum {

	ORDER(MessageHelper.getMessage("InnerMessage.status.order"), "1"), 
	DOCTORADVICE(MessageHelper.getMessage("InnerMessage.status.doctoradvice"), "2"), 
	DOCTORHEALTHGUIDE(MessageHelper.getMessage("InnerMessage.status.doctorhealthguide"), "3"), 
	ONLINE(MessageHelper.getMessage("InnerMessage.status.online"), "4"), 
	SUBSCRIBE(MessageHelper.getMessage("InnerMessage.status.subscribe"), "5"), 
	VISIT(MessageHelper.getMessage("InnerMessage.status.visit"), "6"), 
	VISITDETAIL(MessageHelper.getMessage("InnerMessage.status.visitDetail"),"7"),
	VISITDOCTOR(MessageHelper.getMessage("InnerMessage.status.visitDoctor"),"8"),
	SERVICE(MessageHelper.getMessage("InnerMessage.status.service"),"9"),
	CASE(MessageHelper.getMessage("InnerMessage.status.case"),"10"),
	CONTENT(MessageHelper.getMessage("InnerMessage.status.content"),"11");

	String value = "";
	String name = "";

	private InnerMessageTypeEnum(String name, String value) {
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
		for (InnerMessageTypeEnum spe : InnerMessageTypeEnum.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}
}
