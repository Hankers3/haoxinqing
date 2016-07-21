package com.aebiz.b2b2c.websiteoperation.questions.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsModel;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsQueryModel;

public interface QuestionsDAO extends
		BaseDAO<QuestionsModel, QuestionsQueryModel> {

	/**
	 * 通过试卷的Uuid获得所有试题的列表
	 * 
	 * @param questionnaireUuid
	 * @return
	 */
	public List<QuestionsModel> getQuestionsByQuestionnaireUuid(
			String questionnaireUuid);

	/**
	 * 查询出所有没有被选中的试题数量
	 * 
	 * @param questionnaireUuid
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param choosedQuestionIds
	 * @return
	 */
	public int getQuestionsCountByNotSelected(String[] choosedQuestionIds);

	/**
	 * 得到没有被选中的questions
	 * 
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param choosedQuestionIds
	 * @return
	 */
	public List<QuestionsModel> getQuestionsByNotSelected(int iDisplayStart,
			int iDisplayLength, String[] choosedQuestionIds);

	/**
	 * 通过测试分类得到题
	 * 
	 * @param quizCategoryUuid
	 * @param state 
	 * @return
	 */
	public List<QuestionsModel> getQuestionsByQuizCategoryUuid(
			String quizCategoryUuid, String state);
	/**
	 * 根据试卷的uuid获取questions的所有的uuids
	 * @param questionnaireUuid
	 * @return
	 */
	public List<String> getAllQuestionsModelUuids(String quizUuid);
	
	/**
	 * 根据测试分类的id和测试分类的状态获取questions的所有的uuids
	 * @param quizCategoryUuid
	 * @param state
	 * @return
	 */
	public List<String> getAllQuestionsModelUuidsByQuizCategoryUuidAndState(String quizCategoryUuid,
                String state);

}