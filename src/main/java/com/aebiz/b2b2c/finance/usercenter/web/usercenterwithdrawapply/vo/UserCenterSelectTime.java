package com.aebiz.b2b2c.finance.usercenter.web.usercenterwithdrawapply.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 查询时间类型。种类有以下几种：<br/>
 * 
 * 1表示三个月之内：trimesterin<br/>
 * 2表示三个月之外：trimesterout<br/>
 * 3表示一年之内：yearin<br/>
 * 
 * @author liuyongtao
 *
 */
public enum UserCenterSelectTime {
	TRIMESTERIN(MessageHelper.getMessage("withdrawapply.m.trimesterin"),"1"),
	TRIMESTEROUT(MessageHelper.getMessage("withdrawapply.m.trimesterout"),"2"),
	YEARIN(MessageHelper.getMessage("withdrawapply.m.yearin"),"3");
	
	String name = "";
	String value = "";
	
	private UserCenterSelectTime(String name, String value){
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
		for (UserCenterSelectTime spe : UserCenterSelectTime.values()) {
			if (spe.getValue().equals(value)) {
				return spe.getName();
			}
		}
		return "";
	}

}
