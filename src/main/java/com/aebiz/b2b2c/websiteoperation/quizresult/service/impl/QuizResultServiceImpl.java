package com.aebiz.b2b2c.websiteoperation.quizresult.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.websiteoperation.quizresult.service.QuizResultService;
import com.aebiz.b2b2c.websiteoperation.quizresult.dao.QuizResultDAO;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultQueryModel;

@Service
@Transactional
public class QuizResultServiceImpl extends BaseServiceImpl<QuizResultModel,QuizResultQueryModel> implements QuizResultService {
	private QuizResultDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(QuizResultDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(QuizResultModel m) {
		m.setUuid(us.getNextUuid("QuizResult",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(QuizResultModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(QuizResultModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 根据测试分类ID获取测试分类结果
	 * @return
	 * @2015-11-20
	 * @author :SZH
	 */
	@Override
	public List<QuizResultModel> getListByQuizCategoryUuid(String quizCategoryUuid) {

		return myDao.getListByQuizCategoryUuid(quizCategoryUuid);
	}
	/**
	 * 
	 * 根据测试分类ID和分数 获取测试分类结果
	 * @param quizCategoryUuid
	 * @param score
	 * @return
	 */
	@Override
	public QuizResultModel getByQuizCategoryUuid(String quizCategoryUuid,
			String score) {
		
		return myDao.getByQuizCategoryUuid(quizCategoryUuid, score);
	}
}