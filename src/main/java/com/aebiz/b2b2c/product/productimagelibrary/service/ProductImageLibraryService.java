package com.aebiz.b2b2c.product.productimagelibrary.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productimagelibrary.vo.ProductImageLibraryModel;
import com.aebiz.b2b2c.product.productimagelibrary.vo.ProductImageLibraryQueryModel;

public interface ProductImageLibraryService
		extends
			BaseService<ProductImageLibraryModel, ProductImageLibraryQueryModel> {

	/**
	 * 根据分类的uuid获取该分类下的所有图片
	 * 
	 * @param categoryUuid
	 * @return List<ProductImageLibraryModel>
	 */
	public List<ProductImageLibraryModel> getProductImageLibraryModelsByCategoryUuid(
			String categoryUuid,int nowPage ,int pageShow);
	
	/**
	 * 根据分类的uuid获取该分类下的所有图片总数
	 * 
	 * @param categoryUuid
	 * @return int
	 */
	public int getProductImageLibraryModelsByCategoryUuid(
			String categoryUuid);
	
	/**
	 * 上传图片,并返回图片对象
	 * @param myfiles
	 * @return 
	 * ProductImageLibraryModel
	 */
	public ProductImageLibraryModel updloadImage(MultipartFile[] myfiles,String categoryUuid);
	
	
}
