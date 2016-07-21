package com.aebiz.b2b2c.websiteoperation.quizresult.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultQueryModel;

public interface QuizResultDAO extends BaseDAO<QuizResultModel,QuizResultQueryModel>{
	/**
	 * 根据测试分类ID获取测试分类结果
	 * @return
	 * @2015-11-20
	 * @author :SZH
	 */
	public List<QuizResultModel> getListByQuizCategoryUuid(String quizCategoryUuid);
	/**
	 * 根据提问分类的uuid获取咨询结果的uuids
	 * @param quizCategoryUuid
	 * @return
	 */
	public List<String> getAllQuizCategoryModelUuids(String quizCategoryUuid);
	
	/**
	 * 
	 * 根据测试分类ID和分数 获取测试分类结果
	 * @param quizCategoryUuid
	 * @param score
	 * @return
	 */
	public QuizResultModel getByQuizCategoryUuid(String quizCategoryUuid ,String score);
}