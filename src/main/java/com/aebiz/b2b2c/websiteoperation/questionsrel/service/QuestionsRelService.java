package com.aebiz.b2b2c.websiteoperation.questionsrel.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelModel;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelQueryModel;

public interface QuestionsRelService extends
		BaseService<QuestionsRelModel, QuestionsRelQueryModel> {

	/**
	 * 
	 * 根据问卷编号删除所有的问卷试题model
	 * 
	 * @param questionnaireUuid
	 */
	public void deleteByQuestionnaireUuid(String questionnaireUuid);

}
