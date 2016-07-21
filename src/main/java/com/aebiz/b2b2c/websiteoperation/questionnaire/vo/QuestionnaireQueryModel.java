package com.aebiz.b2b2c.websiteoperation.questionnaire.vo;

public class QuestionnaireQueryModel extends QuestionnaireModel {
	private String title;

	public void setTitle(String obj) {
		this.title = obj;
	}

	public String getTitle() {
		return this.title;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[title=" + this.getTitle() + ",]";
	}
}
