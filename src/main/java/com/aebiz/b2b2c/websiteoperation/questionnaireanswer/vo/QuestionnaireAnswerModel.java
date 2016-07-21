package com.aebiz.b2b2c.websiteoperation.questionnaireanswer.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "question_naire_answer")
public class QuestionnaireAnswerModel extends BaseModel {

	/* 用户编号 */
	private String customerUuid = "";

	/* 试卷编号 */
	private String questionnaireUuid = "";

	/* 试题编号 */
	private String questionUuid = "";

	/* 答案 */
	private String answer = "";

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
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

	public void setAnswer(String obj) {
		this.answer = obj;
	}

	public String getAnswer() {
		return this.answer;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[customerUuid=" + this.getCustomerUuid()
				+ ",questionnaireUuid=" + this.getQuestionnaireUuid()
				+ ",questionUuid=" + this.getQuestionUuid() + ",answer="
				+ this.getAnswer() + ",]";
	}
}
