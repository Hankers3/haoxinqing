package com.aebiz.b2b2c.customermgr.customersource.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 会员来源第三方类型：<br/>
 * 
 * 1.qq账号登录<br/>
 * 2.新浪账号登录<br/>
 * 3.微信账号登录<br/>
 * 4.百度账号登录<br/>
 * 
 * @author likun
 * 
 */
public enum ThirdPlatTypeEnum {
	QQ("1", MessageHelper.getMessage("customersource.thirdplat.QQ")), SINA("2",
			MessageHelper.getMessage("customersource.thirdplat.Sina")), WEIXIN(
			"3", MessageHelper.getMessage("customersource.thirdplat.WeiXin")), BAIDU(
			"4", MessageHelper.getMessage("customersource.thirdplat.BaiDu"));

	private String key;
	private String value;

	private ThirdPlatTypeEnum(String key, String value) {
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
		for (ThirdPlatTypeEnum tp : ThirdPlatTypeEnum.values()) {
			if (key.equals(tp.key)) {
				return tp.value;
			}
		}
		return "";
	}
}
