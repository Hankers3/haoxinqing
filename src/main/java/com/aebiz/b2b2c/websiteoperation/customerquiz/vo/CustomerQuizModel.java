package com.aebiz.b2b2c.websiteoperation.customerquiz.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.websiteoperation.quizcategory.service.QuizCategoryService;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryModel;

@Entity
@Table(name="customer_quiz")
@Component
public class CustomerQuizModel extends BaseModel{
	
	
	//注入测试分类的service
	@Transient
	private static   QuizCategoryService quizCategoryService;
	@Autowired
	public void setQuizCategoryService(QuizCategoryService quizCategoryService) {
		this.quizCategoryService = quizCategoryService;
	}
	
	/*测试人员编号*/
	private String userUuid;
	/*测试人员名称*/
	private String userName;
	/*测试分类编号*/
	private String quizCategoryUuid;
	/*测试结果*/
	private String result;
	/*测试总分*/
	private int totalScore;
	/*测试时间*/
	private String createTime;
	/*备注*/
	private String note;
	/*测试分类名*/
	@Transient
	private String categoryName ;
	
	
	/*根据分类id查询分类名称*/
	public String getCategoryName() {
		if(!StringUtil.isEmpty(quizCategoryUuid)){
			return  quizCategoryService.getCategoryName(quizCategoryUuid);
		}
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public void setUserUuid(String obj){
		this.userUuid = obj;
	}
	public String getUserUuid(){
		return this.userUuid;
	}
	
	public void setUserName(String obj){
		this.userName = obj;
	}
	public String getUserName(){
		return this.userName;
	}
	
	public void setQuizCategoryUuid(String obj){
		this.quizCategoryUuid = obj;
	}
	public String getQuizCategoryUuid(){
		return this.quizCategoryUuid;
	}
	
	public void setResult(String obj){
		this.result = obj;
	}
	public String getResult(){
		return this.result;
	}
	
	public void setTotalScore(int obj){
		this.totalScore = obj;
	}
	public int getTotalScore(){
		return this.totalScore;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setNote(String obj){
		this.note = obj;
	}
	public String getNote(){
		return this.note;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[userUuid=" + this.getUserUuid() + ",userName=" + this.getUserName() + ",quizCategoryUuid=" + this.getQuizCategoryUuid() + ",result=" + this.getResult() + ",totalScore=" + this.getTotalScore() + ",createTime=" + this.getCreateTime() + ",note=" + this.getNote() + ",]";
	}	
}
