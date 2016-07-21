package com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo;

public enum ThirdInterfaceTemplateEnum {
	//提醒卖家发货
	SENDSTOREFHINNERMESSAGE("1", "thirdinterfacetemplate.innermsg.noticeStoreFh");
			
	private String value = "";
	private String name = "";

	private ThirdInterfaceTemplateEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static String getNameByValue(String value) {
		for (ThirdInterfaceTemplateEnum p : ThirdInterfaceTemplateEnum.values()) {
			if (p.getValue().equals(value)) {
				return p.getName();
			}
		}
		return "";
	}
}
