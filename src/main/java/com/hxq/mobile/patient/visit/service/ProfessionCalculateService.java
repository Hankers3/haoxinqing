package com.hxq.mobile.patient.visit.service;

import com.hxq.mobile.entity.weixin.ProfessionResult;

import java.util.Map;

/**
 * 专业测试结果计算
 *
 */
public interface ProfessionCalculateService {

	/**
	 * 专业测试结果分值计算
	 * @param subjectId 主题ID
	 * @param answer 答题(题目id,选项值)
	 * @return 仅含答题分值的计算结果
	 * @throws Exception
	 */
	ProfessionResult calculate(String subjectId, Map<String, String> answer) throws Exception;
}