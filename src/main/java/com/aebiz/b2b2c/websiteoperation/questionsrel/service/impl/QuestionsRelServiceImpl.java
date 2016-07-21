package com.aebiz.b2b2c.websiteoperation.questionsrel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.websiteoperation.questionsrel.dao.QuestionsRelDAO;
import com.aebiz.b2b2c.websiteoperation.questionsrel.service.QuestionsRelService;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelModel;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelQueryModel;

@Service
@Transactional
public class QuestionsRelServiceImpl extends
		BaseServiceImpl<QuestionsRelModel, QuestionsRelQueryModel> implements
		QuestionsRelService {
	private QuestionsRelDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(QuestionsRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(QuestionsRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(QuestionsRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(QuestionsRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据问卷编号删除所有的问卷试题model
	 */
	@Override
	public void deleteByQuestionnaireUuid(String questionnaireUuid) {
		myDao.deleteByQuestionnaireUuid(questionnaireUuid);
	}
}