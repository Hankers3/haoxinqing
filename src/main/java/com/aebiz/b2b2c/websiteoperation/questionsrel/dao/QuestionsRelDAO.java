package com.aebiz.b2b2c.websiteoperation.questionsrel.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelModel;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelQueryModel;

public interface QuestionsRelDAO extends
		BaseDAO<QuestionsRelModel, QuestionsRelQueryModel> {

	/**
	 * 
	 * 根据问卷编号删除所有的问题记录
	 * 
	 * @param questionnaireUuid
	 */
	public void deleteByQuestionnaireUuid(String questionnaireUuid);

}