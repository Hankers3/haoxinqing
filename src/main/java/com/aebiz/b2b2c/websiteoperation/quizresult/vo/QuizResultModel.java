package com.aebiz.b2b2c.websiteoperation.quizresult.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 测试分类结果
 * @author xueli
 *
 */
@Entity
@Table(name="quiz_result")
public class QuizResultModel extends BaseModel{
	
	/*测试题分类编号*/
	private String quizCategoryUuid;
	
	/*开始分数*/
	private int startScore;
	
	/*结束分数*/
	private int endScore;
	
	/*结果*/
	private String result;
	
	/*结果说明*/
	private String resultNote;
	
	/*创建时间*/
	private String createTime;
	
	
	public String getResultNote() {
		return resultNote;
	}
	public void setResultNote(String resultNote) {
		this.resultNote = resultNote;
	}
	public void setQuizCategoryUuid(String obj){
		this.quizCategoryUuid = obj;
	}
	public String getQuizCategoryUuid(){
		return this.quizCategoryUuid;
	}
	
	public void setStartScore(int obj){
		this.startScore = obj;
	}
	public int getStartScore(){
		return this.startScore;
	}
	
	public void setEndScore(int obj){
		this.endScore = obj;
	}
	public int getEndScore(){
		return this.endScore;
	}
	
	public void setResult(String obj){
		this.result = obj;
	}
	public String getResult(){
		return this.result;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[quizCategoryUuid=" + this.getQuizCategoryUuid() + ",startScore=" + this.getStartScore() + ",endScore=" + this.getEndScore() + ",result=" + this.getResult() + ",createTime=" + this.getCreateTime() + ",]";
	}	
}
