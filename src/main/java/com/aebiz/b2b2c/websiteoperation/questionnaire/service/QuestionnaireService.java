package com.aebiz.b2b2c.websiteoperation.questionnaire.service;

import java.util.List;
import java.util.Map;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.websiteoperation.questionnaire.vo.QuestionnaireModel;
import com.aebiz.b2b2c.websiteoperation.questionnaire.vo.QuestionnaireQueryModel;

public interface QuestionnaireService extends
		BaseService<QuestionnaireModel, QuestionnaireQueryModel> {

	/**
	 * 1.在更新问卷的时候，需要先删除问卷所关联的试题 <br />
	 * 2.然后再更新问卷表和中间表<br />
	 * 
	 * @param qnm
	 *            问卷model
	 * @param qrmList
	 *            试题Model集合
	 * @param positionList
	 *            排序集合
	 */
	public void updateQuestionnaire(QuestionnaireModel qnm,
			List<Map<String, String>> questionList);

	/**
	 * 新增问卷调查
	 * 
	 * 1.保存问卷 <br />
	 * 2.保存试题 <br />
	 * 
	 * @param qnm
	 * @param questionList
	 */
	public void addQuestionnaire(QuestionnaireModel qnm,
			List<Map<String, String>> questionList);

}
