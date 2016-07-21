package com.aebiz.b2b2c.websiteoperation.customerquiz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.websiteoperation.customerquiz.dao.CustomerQuizDAO;
import com.aebiz.b2b2c.websiteoperation.customerquiz.service.CustomerQuizService;
import com.aebiz.b2b2c.websiteoperation.customerquiz.vo.CustomerQuizModel;
import com.aebiz.b2b2c.websiteoperation.customerquiz.vo.CustomerQuizQueryModel;
import com.aebiz.b2b2c.websiteoperation.quizcategory.service.QuizCategoryService;
import com.aebiz.b2b2c.websiteoperation.quizresult.service.QuizResultService;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;


@Service
@Transactional
public class CustomerQuizServiceImpl extends BaseServiceImpl<CustomerQuizModel,CustomerQuizQueryModel> implements CustomerQuizService {
	private CustomerQuizDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(CustomerQuizDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
	@Autowired
	private CustomerService customerService;
	@Autowired
	private QuizCategoryService quizCategoryService;
	@Autowired
	private QuizResultService quizResultService;
	
	@Override
	public String create(CustomerQuizModel m) {
		m.setUuid(us.getNextUuid("CustomerQuiz",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(CustomerQuizModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(CustomerQuizModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 保存患者自测数据
	 * @param customerUuid
	 * @param quizCategoryUuid
	 * @param score
	 */
	@Override
	public String saveCustomerQuiz(String customerUuid, String quizCategoryUuid,
			String score) {
		CustomerQuizModel m = new CustomerQuizModel();
		m.setUserUuid(customerUuid);
		m.setQuizCategoryUuid(quizCategoryUuid);
		m.setTotalScore(Integer.parseInt(score));
		String customerName = customerService.getCustomerNameByCustomerUuid(customerUuid);
		String categoryName = quizCategoryService.getCategoryName(quizCategoryUuid);
		QuizResultModel quizResultModel = quizResultService.getByQuizCategoryUuid(quizCategoryUuid, score);
		String result = "";
		if(quizResultModel != null){
			result = quizResultModel.getResult();
		}
		m.setResult(result);
		m.setUserName(customerName);
		m.setCategoryName(categoryName);
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		return create(m);
	}
}