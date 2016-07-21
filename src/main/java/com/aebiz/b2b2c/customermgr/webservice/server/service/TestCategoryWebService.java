package com.aebiz.b2b2c.customermgr.webservice.server.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 测试题
 * 
 * @author szr
 * 
 */
@WebService
public interface TestCategoryWebService {
	/**
	 * 获取平台所有的测试题库种类
	 * 
	 * @param jsonString
	 * @return
	 */
	public String getQuestionTypeList(
			@WebParam(name = "jsonString") String jsonString);

	/**
	 * 根据题库种类来获取改种类下所有测试题目
	 * 
	 * @param jsonString
	 * @return
	 */
	public String getQuestionList(
			@WebParam(name = "jsonString") String jsonString);

}
