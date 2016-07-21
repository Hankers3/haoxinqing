package com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 会员�?��行业�?br/>
 * 
 * 1.IT/计算�?网络<br/>
 * 2.金融/银行/投资/基金/证券<br/>
 * 3.房地�?建筑/建材/工程<br/>
 * 4.食品/服装/家居<br/>
 * 5.文化/影视/戏曲<br/>
 * 
 * @author likun
 * 
 */
public enum IndustryEnum {
	one("1", MessageHelper.getMessage("customerinfo.industry.one")), two("2",
			MessageHelper.getMessage("customerinfo.industry.two")), three("3",
			MessageHelper.getMessage("customerinfo.industry.three")), four("4",
			MessageHelper.getMessage("customerinfo.industry.four")), five("5",
			MessageHelper.getMessage("customerinfo.industry.five"));

	private String key = "";
	private String value = "";

	private IndustryEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String getValue(String key) {
		for (IndustryEnum ie : IndustryEnum.values()) {
			if (key.equals(ie.key)) {
				return ie.value;
			}
		}
		return "";
	}
}
