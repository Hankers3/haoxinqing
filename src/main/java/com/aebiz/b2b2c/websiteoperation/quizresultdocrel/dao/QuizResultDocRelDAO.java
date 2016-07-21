package com.aebiz.b2b2c.websiteoperation.quizresultdocrel.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelModel;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelQueryModel;

public interface QuizResultDocRelDAO extends BaseDAO<QuizResultDocRelModel,QuizResultDocRelQueryModel>{
	public List<QuizResultDocRelModel> getListByQuizCategoryUuid(String quizCategoryUuid);
	public List<QuizResultDocRelModel> getListByserviceStaffInfoIdAndquizResultId(String serviceStaffInfoId, String quizResultId);
	
	
	/**
	 * 根据医生的id和问题结果的id获得所有关联咨询结果的uuid
	 * @param serviceStaffInfoId
	 * @param quizResultId
	 * @return
	 */
	public List<String> getAllQuizResultDocRelModelUuids(String serviceStaffInfoId, String quizResultId);
	
	/**
	 * 根据咨询分类的id获取所有的咨询结果的uuids
	 * @param quizCategoryUuid
	 * @return
	 */
	public List<String> getAllQuizResultDocRelModelUuidsByQuizCategoryUuid(String quizCategoryUuid);
}