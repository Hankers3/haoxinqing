package com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;



/**
 * 会员积分类型枚举类型
 * 
 * @author huyingying
 * 
 */
public enum StatIntegralType {

	/* 可用积分 */
	USABLE("1", MessageHelper
			.getMessage("vipclubintegralstat.intergralType.usable")),

	/* 已用积分 */
	USED("2", MessageHelper
			.getMessage("vipclubintegralstat.intergralType.used")),

	/* 累计积分 */
	TOTAL("3", MessageHelper
			.getMessage("vipclubintegralstat.intergralType.total")),

	/* 购物获得积分 */
	GET_BY_SHOP("4", MessageHelper
			.getMessage("vipclubintegralstat.intergralType.get_by_shop")),

	/* 行为获得积分 */
	GET_BY_BEHAVIOUR("5", MessageHelper
			.getMessage("vipclubintegralstat.intergralType.get_by_behaviour")),

	/* 奖励获得积分 */
	GET_BY_REWARD("6", MessageHelper
			.getMessage("vipclubintegralstat.intergralType.get_by_reward"));

	/* 积分类型值 */
	private String value = "";

	/* 积分类型名称 */
	private String name = "";

	private StatIntegralType(String value, String name) {
		this.value = value;
		this.name = name;
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

	/**
	 * 获取方法
	 * 
	 * @paparm value
	 * 
	 * @return name
	 */
	public static String getNameByKey(String value) {
		for (StatIntegralType c : StatIntegralType.values()) {
			if (c.getValue().equals(value)) {
				return c.getName();
			}
		}
		return "";
	}
}
