package com.aebiz.b2b2c.websiteoperation.questionnaire.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "questionnaire")
public class QuestionnaireModel extends BaseModel {

	/* 问卷是否启用：1表示启用 */
	public static final String STARTUSING = "1";

	/* 问卷是否启用：0表示未启用 */
	public static final String ENDUSING = "0";

	/* 问卷标题 */
	private String title = "";

	/* 问卷状态 */
	private String state = "";

	/* 问卷介绍 */
	private String introduce = "";

	/* 备注 */
	private String note = "";

	/* 试题编号集合 */
	@Transient
	private String[] questionIds;

	/* 试题排序集合 */
	@Transient
	private String[] positionValues;

	public void setTitle(String obj) {
		this.title = obj;
	}

	public String getTitle() {
		return this.title;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setIntroduce(String obj) {
		this.introduce = obj;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public String[] getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(String[] questionIds) {
		this.questionIds = questionIds;
	}

	public String[] getPositionValues() {
		return positionValues;
	}

	public void setPositionValues(String[] positionValues) {
		this.positionValues = positionValues;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[title=" + this.getTitle() + ",state=" + this.getState()
				+ ",introduce=" + this.getIntroduce() + ",note="
				+ this.getNote() + ",]";
	}
}
