package com.aebiz.b2b2c.websiteoperation.questionnaire.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.websiteoperation.questionnaire.vo.QuestionnaireModel;
import com.aebiz.b2b2c.websiteoperation.questionnaire.vo.QuestionnaireQueryModel;

@Repository
public class QuestionnaireH4Impl extends
		BaseH4Impl<QuestionnaireModel, QuestionnaireQueryModel> implements
		QuestionnaireDAO {

}
