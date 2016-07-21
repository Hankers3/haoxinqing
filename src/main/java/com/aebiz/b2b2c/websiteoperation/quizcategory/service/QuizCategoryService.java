package com.aebiz.b2b2c.websiteoperation.quizcategory.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryModel;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryQueryModel;

public interface QuizCategoryService extends BaseService<QuizCategoryModel,QuizCategoryQueryModel>{
	/**
	 * 判断分类名 是否存在
	 * @param categoryName
	 * @return
	 */
	public boolean checkCateGoryName(String categoryName);
	
	/**
	 * 根据uuid获取分类名
	 * @param uuid
	 * @return
	 */
	public String  getCategoryName(String uuid);
	
	/**
	 * 文件上传
	 * 
	 * @param ContentModel
	 * @param files
	 * @return
	 */
	public QuizCategoryModel uploadImage(QuizCategoryModel qm,
			MultipartFile[] files);
	/**
	 * 获取所有展示的分类
	 * @return
	 */
	public List<QuizCategoryModel> getQuizCategoryModels();
}
