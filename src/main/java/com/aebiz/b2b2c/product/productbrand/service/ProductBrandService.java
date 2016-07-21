package com.aebiz.b2b2c.product.productbrand.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandModel;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandQueryModel;

/**
 * 商品品牌管理业务接口 商品品牌是用来发布商品时，所要选择的和前台列表页用来筛选的 现在的品牌有子品牌的概念，比如可口可乐，下面有雪碧、果粒橙
 * 
 * @author huangpinpin
 * 
 */
public interface ProductBrandService extends
		BaseService<ProductBrandModel, ProductBrandQueryModel> {
	/**
	 * 根据文件名得到文件路径
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public String getUrlByFileName(String fileName);

	/**
	 * 通过父分类uuid查询所属子分类集合
	 * 
	 * @param productBrandUuid
	 * @return
	 */
	public List<ProductBrandQueryModel> getSubProductBrandsByUuid(
			String productBrandUuid);

	/**
	 * 根据品牌uuid,获取品牌名称
	 * 
	 * @param uuid
	 * @return
	 */
	public String getBrandNameByUuid(String uuid);
	
	/**
	 * 根据品牌uuid集合,获取品牌名称集合
	 * @param uuid
	 * @return
	 */
	public List<String> getBrandNamesByUuids(List<String> uuids);
	
	/**
	 * 根据品牌uuid集合,获取品牌集合
	 * @param uuid
	 * @return
	 */
	public List<ProductBrandModel> getByUuids(List<String> uuids);

 
}
