package com.aebiz.b2b2c.websiteoperation.questions.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 试题类型：<br>
 * 1.SINGLESELECTION 单选题<br>
 * 2.MULTISELECTION 多选题<br>
 * 3.ESSAYQUESTION 问答题<br>
 * 4.COMPLEXQUESTION 复合体<br>
 * 
 * @author likun
 * 
 */
public enum QuestionTypeEnum {
	SINGLESELECTION("1", MessageHelper
			.getMessage("questionnaire.m.singleSelection")), MULTISELECTION(
			"2", MessageHelper.getMessage("questionnaire.m.multiSelection")), ESSAYQUESTION(
			"3", MessageHelper.getMessage("questionnaire.m.essayQuestion")), COMPLEXQUESTION(
			"4", MessageHelper.getMessage("questionnaire.m.complexQuestion"));

	private String key;
	private String value;

	private QuestionTypeEnum(String key, String value) {
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
		for (QuestionTypeEnum qt : QuestionTypeEnum.values()) {
			if (key.equals(qt.key)) {
				return qt.value;
			}
		}
		return "";
	}
}
