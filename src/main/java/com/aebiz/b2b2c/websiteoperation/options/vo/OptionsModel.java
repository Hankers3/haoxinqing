package com.aebiz.b2b2c.websiteoperation.options.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 测试题规则表
 * 
 * @author szr
 * 
 */
@Entity
@Table(name = "questions_options")
public class OptionsModel extends BaseModel {

	/* 测试题编号 */
	private String questionUuid = "";

	/* 选项 */
	private String optionTitle = "";

	/* 分数 */
	private int optionValue = 0;

	/* 发布时间 */
	private String createTime = "";

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setQuestionUuid(String obj) {
		this.questionUuid = obj;
	}

	public String getQuestionUuid() {
		return this.questionUuid;
	}

	public String getOptionTitle() {
		return optionTitle;
	}

	public void setOptionTitle(String optionTitle) {
		this.optionTitle = optionTitle;
	}

	public void setOptionValue(int obj) {
		this.optionValue = obj;
	}

	public int getOptionValue() {
		return this.optionValue;
	}

	@Override
	public String toString() {
		return "OptionsModel [questionUuid=" + questionUuid + ", optionTitle="
				+ optionTitle + ", optionValue=" + optionValue + "]";
	}

}
