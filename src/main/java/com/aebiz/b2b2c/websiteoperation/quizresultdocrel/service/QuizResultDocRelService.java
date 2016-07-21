package com.aebiz.b2b2c.websiteoperation.quizresultdocrel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelModel;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelQueryModel;

public interface QuizResultDocRelService extends BaseService<QuizResultDocRelModel,QuizResultDocRelQueryModel>{
	/**
	 * 根据结果分类ID查询关联的医生
	 * @param quizResultUuid
	 * @return
	 * @2015-11-23
	 * @author :SZH
	 */
	public List<QuizResultDocRelModel> getListByQuizResultUuid(String quizResultUuid);
	/**
	 * 是否存在已有的关联关系对象
	 * @param serviceStaffInfoId
	 * @param quizResultId
	 * @return
	 * @2015-11-24
	 * @author :SZH
	 */
	public boolean isRepeat(String serviceStaffInfoId,String quizResultId);
	/**
	 * 根据医生信息ID和测试分类结果ID保存对象
	 * @param serviceStaffInfoId
	 * @param quizResultId
	 * @2015-11-24
	 * @author :SZH
	 */
	public void create(String serviceStaffInfoId,String quizResultId);
}
