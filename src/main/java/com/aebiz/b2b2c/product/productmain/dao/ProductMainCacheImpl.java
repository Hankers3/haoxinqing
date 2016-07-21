package com.aebiz.b2b2c.product.productmain.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.product.common.ProductCacheConstant;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 商品缓存
 * 
 * @author zdx
 * @date 2015-5-26
 */
@Primary
@Repository
public class ProductMainCacheImpl extends
		BaseMemcachedCache<ProductMainModel, ProductMainQueryModel> implements
		ProductMainDAO {

	@Resource(name = ProductCacheConstant.PRODUCT_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ProductMainDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}
	
	@Override
	public void update(ProductMainModel m) {
		dao.update(m);
		this.mcc.delete(ProductCacheConstant.PRODUCT_PRODUCTMAIN_KEY+m.getUuid());
	}

	public ProductMainCacheImpl() {
		super(ProductCacheConstant.PRODUCT_PRODUCTMAIN_KEY);
	}

	@Override
	public void updateState(String state, List<String> productUuids) {
		dao.updateState(state, productUuids);
		for (String uuid : productUuids) {
			this.mcc.delete(ProductCacheConstant.PRODUCT_PRODUCTMAIN_KEY + uuid);
		}

	}

	@Override
	public void updateStateByProductUuid(String state, String productUuid) {
		dao.updateStateByProductUuid(state, productUuid);
		this.mcc.delete(ProductCacheConstant.PRODUCT_PRODUCTMAIN_KEY
				+ productUuid);
	}

	@Override
	public ProductMainModel getProductMainModelByProductUuid(String productUuid) {
		ProductMainModel product = this.getByUuid(productUuid);
		return product;
	}

	@Override
	public void updateAuditStateByUuid(String uuid, String state) {
		dao.updateAuditStateByUuid(uuid, state);
		this.mcc.delete(ProductCacheConstant.PRODUCT_PRODUCTMAIN_KEY + uuid);
	}

	@Override
	public boolean checkProductNoExist(String productNo) {
		return dao.checkProductNoExist(productNo);
	}

	@Override
	public String getProductNameByUuid(String productUuid) {
		ProductMainModel product = this.getByUuid(productUuid);
		if (product != null) {
			return product.getProductName();
		}
		return "";
	}

	/**
	 * 导入药品的信息
	 */
	@Override
	public List<Object> updloadExcel(MultipartFile[] myfiles) {
		return dao.updloadExcel(myfiles);
	}

	@Override
	public List<ProductMainModel> getByProductName(String productName) {
		return dao.getByProductName(productName);
	}

}
