package com.aebiz.b2b2c.websiteoperation.quizresult.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultQueryModel;

public interface QuizResultService extends BaseService<QuizResultModel,QuizResultQueryModel>{
	/**
	 * 根据测试分类ID获取测试分类结果
	 * @return
	 * @2015-11-20
	 * @author :SZH
	 */
	public List<QuizResultModel> getListByQuizCategoryUuid(String quizCategoryUuid);
	
	/**
	 * 
	 * 根据测试分类ID和分数 获取测试分类结果
	 * @param quizCategoryUuid
	 * @param score
	 * @return
	 */
	public QuizResultModel getByQuizCategoryUuid(String quizCategoryUuid ,String score);
}
