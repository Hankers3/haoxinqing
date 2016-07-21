package com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo;

public class QuizResultDocRelQueryModel extends QuizResultDocRelModel {
	private String createTime;
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[createTime=" + this.getCreateTime() + ",]";
	}
}
