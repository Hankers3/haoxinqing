package com.aebiz.b2b2c.websiteoperation.questions.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsModel;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsQueryModel;

public interface QuestionsService extends
		BaseService<QuestionsModel, QuestionsQueryModel> {

	/**
	 * 在保存试题的时候，同时保存试题的选项
	 * 
	 * 1.保存试题 <br />
	 * 2.删除原先的试题和选项的关联关系<br />
	 * 3.再重新保存所有的选项
	 * 
	 * @param qm
	 *            试题
	 * 
	 * @param omList
	 *            试题选项的列表，需要循环保存
	 */
	public void updateQuestion(QuestionsModel qm, List<OptionsModel> omList,
			MultipartFile[] imgFiles);

	/**
	 * 在保存试题的时候
	 * 
	 * 同时保存试题的选项
	 * 
	 * 1.保存试题 <br />
	 * 2.保存所有的选项
	 * 
	 * @param qm
	 *            试题
	 * 
	 * @param omList
	 *            试题选项的列表，需要循环保存
	 */
	public void addQuestion(QuestionsModel qm, List<OptionsModel> omList,
			MultipartFile[] imgFiles);

	/**
	 * 
	 * 根据问卷编号查询出所有的问卷试题model
	 * 
	 * @param questionnaireUuid
	 * @return
	 */
	public List<QuestionsModel> getQuestionsByQuestionnaireUuid(
			String questionnaireUuid);

	/**
	 * 查询出所有没有被选中的试题数量
	 * 
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
	 * @return
	 */
	public List<QuestionsModel> getQuestionsByQuizCategoryUuid(
			String quizCategoryUuid, String status);

}
