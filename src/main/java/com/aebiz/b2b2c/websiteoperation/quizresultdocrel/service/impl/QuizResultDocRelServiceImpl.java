package com.aebiz.b2b2c.websiteoperation.quizresultdocrel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.service.QuizResultDocRelService;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.dao.QuizResultDocRelDAO;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelModel;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelQueryModel;

@Service
@Transactional
public class QuizResultDocRelServiceImpl extends BaseServiceImpl<QuizResultDocRelModel,QuizResultDocRelQueryModel> implements QuizResultDocRelService {
	private QuizResultDocRelDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(QuizResultDocRelDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(QuizResultDocRelModel m) {
		m.setUuid(us.getNextUuid("QuizResultDocRel",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(QuizResultDocRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(QuizResultDocRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public List<QuizResultDocRelModel> getListByQuizResultUuid(
			String quizResultUuid) {
		
		return myDao.getListByQuizCategoryUuid(quizResultUuid);
	}

	@Override
	public boolean isRepeat(String serviceStaffInfoId, String quizResultId) {
		List<QuizResultDocRelModel> list = myDao.getListByserviceStaffInfoIdAndquizResultId(serviceStaffInfoId, quizResultId);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public void create(String serviceStaffInfoId, String quizResultId) {
		//保存对象
		QuizResultDocRelModel quizResultDocRelModel = new QuizResultDocRelModel();
		//设置医生信息ID
		quizResultDocRelModel.setServiceStaffInfoId(serviceStaffInfoId);
		//设置测试分类结果ID
		quizResultDocRelModel.setQuizResultUuid(quizResultId);
		
		create(quizResultDocRelModel);
	}
}