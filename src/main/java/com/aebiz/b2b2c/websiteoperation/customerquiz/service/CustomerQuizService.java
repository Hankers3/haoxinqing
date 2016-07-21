package com.aebiz.b2b2c.websiteoperation.customerquiz.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.websiteoperation.customerquiz.vo.CustomerQuizModel;
import com.aebiz.b2b2c.websiteoperation.customerquiz.vo.CustomerQuizQueryModel;

public interface CustomerQuizService extends BaseService<CustomerQuizModel,CustomerQuizQueryModel>{
	
	/**
	 * 保存患者自测数据
	 * @param customerUuid
	 * @param quizCategoryUuid
	 * @param score
	 */
	public String saveCustomerQuiz(String customerUuid,String quizCategoryUuid ,String score);
}
