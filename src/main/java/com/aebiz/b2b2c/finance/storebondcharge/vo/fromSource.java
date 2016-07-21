package com.aebiz.b2b2c.finance.storebondcharge.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 门店类型的枚举<br>
 * 0:从合同生成的增加保证金 <br>
 * 1:违约处罚，扣减保证金 <br>
 * 2:商户普通充值
 *
 * @author tangyunkai
 *
 * @date 2014年10月29日 下午9:57:29
 *
 */
public enum fromSource {

	//   0:从合同生成的增加保证金
	FROM_CONTRACT("0", MessageHelper.getMessage("storebondcharge.m.fromContract")),

	//  1:违约处罚，扣减保证金
	BREAK_MINUS("1", MessageHelper.getMessage("storebondcharge.m.fromBreak")),

	//  2:商户普通充值
	NORMAL_CHARGE("2", MessageHelper.getMessage("storebondcharge.m.normalPay"));

	private String value = "";

	private String name = "";

	private fromSource(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	/**
	 * 对外提供枚举方法
	 * 
	 * @param value
	 * @return String
	 */
	public static String getNameByValue(String value) {
		for (fromSource p : fromSource.values()) {
			if (p.getValue().equals(value)) {
				return p.getName();
			}
		}
		return "";
	}
}
