package com.aebiz.b2b2c.product.interactive.product.dao;

import java.util.List;
import java.util.Map;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveQueryModel;

public interface ProductInteractiveDAO extends
		BaseDAO<ProductInteractiveModel, ProductInteractiveQueryModel> {
	/**
	 * 对外接口：根据uuid查询商品对象
	 * 
	 * @param uuid
	 * @return
	 */
	public ProductInteractiveModel getProductByUuid(String uuid);

	/**
	 * 对外接口：获取某个商户下可以关联促销的商品集合
	 * 
	 * @param uuids
	 *            过滤商品uuids数组
	 * @param qm
	 *            查询条件
	 * @param storeUuid
	 *            商户uuid
	 * @param start
	 * @param page
	 * @return
	 */
	public List<ProductInteractiveModel> searchProduct(
			ProductInteractiveQueryModel qm, int start, int page);

	/**
	 * 对外接口：获取某个商户下可以关联促销的商品集合个数
	 * 
	 * @param uuids
	 *            过滤商品uuid数组
	 * @param storeUuid
	 *            商户uuid
	 * @return
	 */
	public int getCountByProduct(ProductInteractiveQueryModel qm);

	/**
	 * 根据条件查询商品
	 * 
	 * @param onlyUuids
	 * @param needPage
	 * @param qm
	 * @param start
	 * @param pageShow
	 * @return
	 */
	public List<ProductInteractiveModel> getProductByCondition(
			boolean onlyUuids, boolean needPage,
			ProductInteractiveQueryModel qm, int start, int pageShow);

	/**
	 * 
	 * 通过商品编号查询商品的基础信息<br />
	 * 此方法暂时被前台添加商品到购物车调用<br />
	 * 
	 * 需要的数据有:<br />
	 * 1.商品名称<br />
	 * 2.商品图片<br />
	 * 3.店铺编号<br />
	 * 4.商品状态<br />
	 * 5.审核状态<br />
	 * 
	 * @param productUuid
	 * @return
	 * @author duandeyi 2014-12-19
	 */
	public Map<String, String> getProductParamsByProductUuid(String productUuid);

}