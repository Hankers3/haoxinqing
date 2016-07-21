package com.aebiz.b2b2c.websiteoperation.questionnaire.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.websiteoperation.questionnaire.dao.QuestionnaireDAO;
import com.aebiz.b2b2c.websiteoperation.questionnaire.service.QuestionnaireService;
import com.aebiz.b2b2c.websiteoperation.questionnaire.vo.QuestionnaireModel;
import com.aebiz.b2b2c.websiteoperation.questionnaire.vo.QuestionnaireQueryModel;
import com.aebiz.b2b2c.websiteoperation.questions.service.QuestionsService;
import com.aebiz.b2b2c.websiteoperation.questionsrel.service.QuestionsRelService;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelModel;

@Service
@Transactional
public class QuestionnaireServiceImpl extends
		BaseServiceImpl<QuestionnaireModel, QuestionnaireQueryModel> implements
		QuestionnaireService {
	private QuestionnaireDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	private QuestionsService questionsService;
	@Autowired
	private QuestionsRelService questionsRelService;

	@Autowired
	public void setMyDao(QuestionnaireDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(QuestionnaireModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(QuestionnaireModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(QuestionnaireModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 删除问卷，需要删除问卷和试题的关联关系，然后删除问卷
	 */
	@Override
	public void deletes(List<String> needDeleteUuids) {
		for (String questionnaireUuid : needDeleteUuids) {
			questionsRelService.deleteByQuestionnaireUuid(questionnaireUuid);
		}
		super.deletes(needDeleteUuids);
	}

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
			List<Map<String, String>> questionList) {

		// 更新试卷
		this.update(qnm);

		// 删除试卷和题目的关联关系
		questionsRelService.deleteByQuestionnaireUuid(qnm.getUuid());

		// 循环试题列表，获得试题和试卷的关联关系
		saveQuestionsRels(qnm.getUuid(), questionList);
	}

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
			List<Map<String, String>> questionList) {

		// 创建试卷
		String questionnaireUuid = this.create(qnm);

		// 循环试题列表，获得试题和试卷的关联关系
		saveQuestionsRels(questionnaireUuid, questionList);

	}

	/**
	 * 创建试卷和试题的关联关系
	 */
	private void saveQuestionsRels(String questionnaireUuid,
			List<Map<String, String>> questionList) {

		// 循环前台所选择的试题列表，Map:key==试题的uuid，Value=试题的position
		if (questionList != null && questionList.size() > 0) {
			for (int i = 0; i < questionList.size(); i++) {
				Map<String, String> positionMap = (Map<String, String>) questionList
						.get(i);

				// 获得试题的uuid和位置进行保存
				for (String questionUuid : positionMap.keySet()) {
					int position = 0;
					try {
						// 得到position值
						position = Integer.parseInt(positionMap
								.get(questionUuid));
					} catch (Exception ex) {
					}
					// 创建关联关系
					QuestionsRelModel qrm = new QuestionsRelModel();
					qrm.setQuestionnaireUuid(questionnaireUuid);
					qrm.setQuestionUuid(questionUuid);
					qrm.setPosition(position);
					questionsRelService.create(qrm);
				}
			}
		}
	}
}