package com.aebiz.b2b2c.websiteoperation.questionnaireanswer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.service.QuestionnaireAnswerService;
import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.dao.QuestionnaireAnswerDAO;
import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.vo.QuestionnaireAnswerModel;
import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.vo.QuestionnaireAnswerQueryModel;

@Service
@Transactional
public class QuestionnaireAnswerServiceImpl
		extends
		BaseServiceImpl<QuestionnaireAnswerModel, QuestionnaireAnswerQueryModel>
		implements QuestionnaireAnswerService {
	private QuestionnaireAnswerDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(QuestionnaireAnswerDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(QuestionnaireAnswerModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(QuestionnaireAnswerModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(QuestionnaireAnswerModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}