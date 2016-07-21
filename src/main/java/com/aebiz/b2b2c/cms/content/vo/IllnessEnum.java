package com.aebiz.b2b2c.cms.content.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.order.ordermain.vo.OrderFromTypeEnum;
/**
 * app资讯筛选疾病信息
 * @author xueli
 * 1：精神分裂症
 * 2：双相障碍
 * 3：抑郁障碍
 * 4：焦虑障碍
 * 5：强迫症
 * 6：创伤后应激障碍
 * 7：睡眠障碍
 * 8：物质依赖
 * 9：其他
 */
public enum IllnessEnum {
	schizophrenia(MessageHelper.getMessage("illness.m.schizophrenia"), "1"),
	bipolar(MessageHelper.getMessage("illness.m.bipolar"), "2"),
	depressive(MessageHelper.getMessage("illness.m.depressive"), "3"),
	anxiety(MessageHelper.getMessage("illness.m.anxiety"), "4"),
	ocd(MessageHelper.getMessage("illness.m.ocd"), "5"),
	ptsd(MessageHelper.getMessage("illness.m.ptsd"), "6"),
	sleepdisorder(MessageHelper.getMessage("illness.m.sleepdisorder"), "7"),
	substance(MessageHelper.getMessage("illness.m.substance"), "8"),
	other(MessageHelper.getMessage("illness.m.other"), "9");
	
	String value = "";
	String name = "";

	private IllnessEnum(String name, String value) {
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
		for (OrderFromTypeEnum spe : OrderFromTypeEnum.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}
}
