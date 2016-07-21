package com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 查询时间类型枚举类型
 * 
 * @author huyingying
 * 
 */

public enum QueryTimeType {
	/* 三个月以内 */
	IN_THREE_MONTH("1", MessageHelper.getMessage("vipclubintegrallog.queryTime.in_three_month")),
	/* 三个月以前 */
	OUT_THREE_MONTH("2", MessageHelper.getMessage("vipclubintegrallog.queryTime.out_three_month")),
	/* 一年以内 */
	IN_ONE_YEAR("3", MessageHelper.getMessage("vipclubintegrallog.queryTime.in_one_year")),
	/* 全部 */
	ALL("4", MessageHelper.getMessage("vipclubintegrallog.queryTime.all"));
	/* 查询时间类型值 */
	private String value = "";
	/* 查询时间类型名称 */
	private String name = "";

	private QueryTimeType(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 获取方法
	 * 
	 * @paparm value
	 * @return name
	 */
	public static String getNameByKey(String value) {
		for (QueryTimeType c : QueryTimeType.values()) {
			if (c.getValue().equals(value)) {
				return c.getName();
			}
		}
		return "";
	}
}
