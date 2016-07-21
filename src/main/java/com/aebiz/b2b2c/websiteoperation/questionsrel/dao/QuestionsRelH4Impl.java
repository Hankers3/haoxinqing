package com.aebiz.b2b2c.websiteoperation.questionsrel.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelModel;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelQueryModel;

@Repository
public class QuestionsRelH4Impl extends
		BaseH4Impl<QuestionsRelModel, QuestionsRelQueryModel> implements
		QuestionsRelDAO {

	/**
	 * 当问卷取消和题目关联时，需要删除试卷和题目的关联关系
	 * 
	 * 使用范围：当问卷关联题目更新的时候，需要先删除所有的关联关系，在进行建立
	 * 
	 */
	@Override
	public void deleteByQuestionnaireUuid(String questionnaireUuid) {
		StringBuffer hql = new StringBuffer(
				" delete from QuestionsRelModel qrm where ");
		hql.append(" qrm.questionnaireUuid=:questionnaireUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("questionnaireUuid", questionnaireUuid);

		query.executeUpdate();
	}
}
