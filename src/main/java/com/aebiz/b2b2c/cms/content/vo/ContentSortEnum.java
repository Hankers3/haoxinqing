package com.aebiz.b2b2c.cms.content.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.order.ordermain.vo.OrderFromTypeEnum;
/**
 * app资讯筛选类别（临床量表）
 * @author xueli
 * 1：精神病/分裂症
 * 2：躁狂
 * 3：抑郁
 * 4：焦虑
 * 5：恐惧/强迫
 * 6：创伤后应激障碍
 * 7：物质依赖
 * 8：总评
 * 9：其他
 */
public enum ContentSortEnum {
	psychosis(MessageHelper.getMessage("sort.m.psychosis"), "1"),
	mania(MessageHelper.getMessage("sort.m.mania"), "2"),
	depressed(MessageHelper.getMessage("sort.m.depressed"), "3"),
	anxiety(MessageHelper.getMessage("sort.m.anxiety"), "4"),
	fear(MessageHelper.getMessage("sort.m.fear"), "5"),
	ptsd(MessageHelper.getMessage("sort.m.ptsd"), "6"),
	substance(MessageHelper.getMessage("sort.m.substance"), "7"),
	general(MessageHelper.getMessage("sort.m.general"), "8"),
	other(MessageHelper.getMessage("sort.m.other"), "9");
	
	String value = "";
	String name = "";

	private ContentSortEnum(String name, String value) {
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
