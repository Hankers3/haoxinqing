package com.aebiz.b2b2c.customermgr.customerinfo.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 会员月收入区间 <br/>
 * 
 * 1.2000元以下 <br/>
 * 2.2000-3999元<br/>
 * 3.4000-5999元<br/>
 * 4.6000-7999元<br/>
 * 5.8000以上<br/>
 * 
 * @author likun
 * 
 */
public enum IncomeRangeEnum {
	ONE("1", MessageHelper.getMessage("customerinfo.income.one")), TWO("2",
			MessageHelper.getMessage("customerinfo.income.two")), THREE("3",
			MessageHelper.getMessage("customerinfo.income.three")), FOUR("4",
			MessageHelper.getMessage("customerinfo.income.four")), FIVE("5",
			MessageHelper.getMessage("customerinfo.income.five"));

	private String key = "";
	private String value = "";

	private IncomeRangeEnum(String key, String value) {
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
		for (IncomeRangeEnum ir : IncomeRangeEnum.values()) {
			if (key.equals(ir.key)) {
				return ir.value;
			}
		}
		return "";
	}
}
