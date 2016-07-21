package com.aebiz.b2b2c.websiteoperation.questionsrel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "question_naire_rel")
public class QuestionsRelModel extends BaseModel {

	/* 问卷调查编号 */
	private String questionnaireUuid = "";

	/* 试题编号 */
	private String questionUuid = "";

	/* 排序位置 */
	private int position = 0;

	/* 问卷题目title */
	@Transient
	private String questionTitle = "";

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public void setQuestionnaireUuid(String obj) {
		this.questionnaireUuid = obj;
	}

	public String getQuestionnaireUuid() {
		return this.questionnaireUuid;
	}

	public void setQuestionUuid(String obj) {
		this.questionUuid = obj;
	}

	public String getQuestionUuid() {
		return this.questionUuid;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[questionnaireUuid=" + this.getQuestionnaireUuid()
				+ ",questionUuid=" + this.getQuestionUuid() + ",position="
				+ this.getPosition() + ",]";
	}
}
