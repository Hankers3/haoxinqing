package com.aebiz.b2b2c.userfront.plattimagelibrary.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.userfront.plattimagelibrary.vo.PlattImageLibraryModel;
import com.aebiz.b2b2c.userfront.plattimagelibrary.vo.PlattImageLibraryQueryModel;

public interface PlattImageLibraryDAO extends
		BaseDAO<PlattImageLibraryModel, PlattImageLibraryQueryModel> {

	/**
	 * 根据分类的uuid获取该分类下的所有图片
	 * 
	 * @param categoryUuid
	 * @return List<PlattImageLibraryModel>
	 */
	public List<PlattImageLibraryModel> getPlattImageLibraryModelsByCategoryUuid(
			String categoryUuid, int nowPage, int pageShow);

	/**
	 * 根据分类的uuid获取该分类下的所有图片总数
	 * 
	 * @param categoryUuid
	 * @return int
	 */
	public int getPlattImageLibraryModelsByCategoryUuid(String categoryUuid);

	/**
	 * 根据分类的uuid获取该分类下的所有图片
	 * 
	 * @param categoryUuid
	 * @return List<PlattImageLibraryModel>
	 */
	public List<PlattImageLibraryModel> getPlattImageLibraryModelListByCategoryUuid(
			String categoryUuid);
	
}