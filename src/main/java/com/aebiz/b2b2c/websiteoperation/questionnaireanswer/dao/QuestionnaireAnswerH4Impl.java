package com.aebiz.b2b2c.websiteoperation.questionnaireanswer.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.vo.QuestionnaireAnswerModel;
import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.vo.QuestionnaireAnswerQueryModel;

@Repository
public class QuestionnaireAnswerH4Impl extends
		BaseH4Impl<QuestionnaireAnswerModel, QuestionnaireAnswerQueryModel>
		implements QuestionnaireAnswerDAO {

}
