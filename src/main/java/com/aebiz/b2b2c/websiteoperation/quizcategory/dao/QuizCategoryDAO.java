package com.aebiz.b2b2c.websiteoperation.quizcategory.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryModel;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryQueryModel;

public interface QuizCategoryDAO extends BaseDAO<QuizCategoryModel,QuizCategoryQueryModel>{
	/**
	 * 判断分类名 是否存在
	 * @param categoryName
	 * @return
	 */
	public String checkCateGoryName(String categoryName);
	/**
	 * 根据uuid获取分类名
	 * @param uuid
	 * @return
	 */
	public String  getCategoryName(String uuid);
	/**
	 * 获取所有展示的分类
	 * @return
	 */
	public List<QuizCategoryModel> getQuizCategoryModels();
	
	/**
	 * 获取所有展示的分类uuid
	 * @return
	 */
	public List<String> getQuizCategoryModelUuids();
}