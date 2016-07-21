package com.aebiz.b2b2c.product.interactive.product.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.interactive.product.dao.ProductInteractiveDAO;
import com.aebiz.b2b2c.product.interactive.product.service.ProductInteractiveService;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveQueryModel;
import com.alibaba.fastjson.JSON;

@Service
@Transactional
public class ProductInteractiveServiceImpl extends
		BaseServiceImpl<ProductInteractiveModel, ProductInteractiveQueryModel>
		implements ProductInteractiveService {
	private ProductInteractiveDAO myDao = null;
	
	
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(ProductInteractiveDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public List<ProductInteractiveModel> getByCondition(
			ProductInteractiveQueryModel qm, int start, int pageShow) {
		// TODO Auto-generated method stub
		return myDao.getByCondition(qm, start, pageShow);
	}

	/**
	 * 对外接口：根据uuid查询商品对象
	 * 
	 * @param uuid
	 * @return
	 */
	public ProductInteractiveModel getProductByUuid(String uuid) {
		return myDao.getProductByUuid(uuid);
	}

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
			ProductInteractiveQueryModel qm, int start, int page) {
		return myDao.searchProduct(qm, start, page);
	}

	/**
	 * 对外接口：获取某个商户下可以关联促销的商品集合个数
	 * 
	 * @param uuids
	 *            过滤商品uuid数组
	 * @param storeUuid
	 *            商户uuid
	 * @return
	 */
	public int getCountByProduct(ProductInteractiveQueryModel qm) {
		return myDao.getCountByProduct(qm);
	}

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
	public Map<String, String> getProductParamsByProductUuid(String productUuid) {
		return myDao.getProductParamsByProductUuid(productUuid);
	}
	
	
	
	
}