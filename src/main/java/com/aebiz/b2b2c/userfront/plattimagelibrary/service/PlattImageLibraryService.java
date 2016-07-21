package com.aebiz.b2b2c.userfront.plattimagelibrary.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.userfront.plattimagelibrary.vo.PlattImageLibraryModel;
import com.aebiz.b2b2c.userfront.plattimagelibrary.vo.PlattImageLibraryQueryModel;

public interface PlattImageLibraryService extends
		BaseService<PlattImageLibraryModel, PlattImageLibraryQueryModel> {

	/**
	 * 根据分类的uuid获取该分类下的所有图片
	 * 
	 * @param categoryUuid
	 * @return List<PlattImageLibraryModel>
	 */
	public List<PlattImageLibraryModel> getPlattImageLibraryModelsByCategoryUuid(
			String categoryUuid, int nowPage, int pageShow);

	/**
	 * 根据分类的uuid获取该分类下的所有图片
	 * 
	 * @param categoryUuid
	 * @return List<PlattImageLibraryModel>
	 */
	public List<PlattImageLibraryModel> getPlattImageLibraryModelListByCategoryUuid(
			String categoryUuid);

	/**
	 * 根据分类的uuid获取该分类下的所有图片总数
	 * 
	 * @param categoryUuid
	 * @return int
	 */
	public int getPlattImageLibraryModelsByCategoryUuid(String categoryUuid);

	/**
	 * 上传图片,并返回图片对象
	 * 
	 * @param myfiles
	 * @return PlattImageLibraryModel
	 */
	public PlattImageLibraryModel updloadImage(MultipartFile[] myfiles,
			String categoryUuid);
}
