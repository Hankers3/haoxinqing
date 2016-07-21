package com.wxsupport.web.logic;

import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class CommonCalculater {

	/**
	 * 总分计算：条目得分值简单相加，没有乘积系数。
	 * @param answer
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static Integer simpleSummary(Map<String, String> answer, Map<String, Object> params) throws Exception {
		if(ObjectUtils.isEmpty(answer)) return null;

		int intTotal = 0;
		Entry<String, String> entry = null;
		Iterator<Entry<String, String>> it = answer.entrySet().iterator();
		while (it.hasNext()) {
			entry = it.next();
			intTotal += MathUtils.toInt(entry.getValue(), 0);
		}
		return Integer.valueOf(intTotal);
	}

	/**
	 * 焦虑自评量表(SAS)总分计算：条目总分相加*1.25后取整。
	 * @param answer 用户选择答案
	 * @param params 计算参数
	 * @return 计算结果
	 * @throws Exception
	 */
	public static Integer sas(Map<String, String> answer, Map<String, Object> params) throws Exception {
		if(ObjectUtils.isEmpty(answer)) return null;

		double dbTotal = 0.0;
		Entry<String, String> entry = null;
		Iterator<Entry<String, String>> it = answer.entrySet().iterator();
		while (it.hasNext()) {
			entry = it.next();
			dbTotal += MathUtils.toDouble(entry.getValue(), 0.0);
		}

		double dbRatio = ObjectUtils.isEmpty(params) ? 1.25 : MathUtils.toDouble(params.get("calculate_ratio"), 1.25);
		dbTotal *= dbRatio;
		return new Double(Double.parseDouble(String.valueOf(Math.floor(dbTotal)))).intValue() ;
	}

	/**
	 * 抑郁自评量表(SDS)总分计算：条目总分相加*1.25后取整。
	 * @param answer 用户选择答案
	 * @param params 计算参数
	 * @return 计算结果
	 * @throws Exception
	 */
	public static Integer sds(Map<String, String> answer, Map<String, Object> params) throws Exception {
		if(ObjectUtils.isEmpty(answer)) return null;

		double dbTotal = 0.0;
		Entry<String, String> entry = null;
		Iterator<Entry<String, String>> it = answer.entrySet().iterator();
		while (it.hasNext()) {
			entry = it.next();
			dbTotal += MathUtils.toDouble(entry.getValue(), 0.0);
		}

		double dbRatio = ObjectUtils.isEmpty(params) ? 1.25 : MathUtils.toDouble(params.get("calculate_ratio"), 1.25);
		dbTotal *= dbRatio;
		return new Double(Double.parseDouble(String.valueOf(Math.floor(dbTotal)))).intValue() ;
	}
}
