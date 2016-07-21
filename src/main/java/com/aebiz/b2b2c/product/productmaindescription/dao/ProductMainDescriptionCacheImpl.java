package com.aebiz.b2b2c.product.productmaindescription.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.common.ProductCacheConstant;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionModel;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class ProductMainDescriptionCacheImpl
		extends
		BaseMemcachedCache<ProductMainDescriptionModel, ProductMainDescriptionQueryModel>
		implements ProductMainDescriptionDAO {
	@Resource(name = ProductCacheConstant.PRODUCT_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ProductMainDescriptionDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public ProductMainDescriptionCacheImpl() {
		super(ProductCacheConstant.PRODUCT_MAIN_DESCRIPTION);
	}

	/**
	 * 根据商品uuid集合 删除价格信息
	 * 
	 * @param productUuids
	 */
	public void deletesByProductUuids(List<String> productUuids) {
		myDao.deletesByProductUuids(productUuids);
		if (productUuids != null && productUuids.size() > 0) {
			for (String productUuid : productUuids) {
				mcc.delete(ProductCacheConstant.PRODUCT_MAIN_DESCRIPTION
						+ productUuid);
			}
		}
	}

	/**
	 * 根据商品uuid 查询描述信息
	 * 
	 * @param productUuid
	 * @return
	 */
	public ProductMainDescriptionModel getByProductUuid(String productUuid) {
		if (StringUtil.isEmpty(productUuid)) {
			return null;
		}
		Object obj = mcc.get(ProductCacheConstant.PRODUCT_MAIN_DESCRIPTION
				+ productUuid);
		if (obj != null) {
			return (ProductMainDescriptionModel) obj;
		}

		String uuid = myDao.getUuidByProductUuid(productUuid);
		ProductMainDescriptionModel m = super.getByUuid(uuid);
		if (m != null) {
			this.mcc.set(
					ProductCacheConstant.PRODUCT_MAIN_DESCRIPTION
							+ m.getProductUuid(), m);
		}
		return m;
	}

	@Override
	protected void updateCache(ProductMainDescriptionModel m) {
		Object obj = this.mcc.get(ProductCacheConstant.PRODUCT_MAIN_DESCRIPTION
				+ m.getProductUuid());
		if (obj != null) {
			this.mcc.set(
					ProductCacheConstant.PRODUCT_MAIN_DESCRIPTION
							+ m.getProductUuid(), m);
		}
	}

	@Override
	public String getUuidByProductUuid(String productUuid) {
		return this.myDao.getUuidByProductUuid(productUuid);
	}

	/**
	 * 恢复商品描述表,根据所选productUuid集合
	 * @param productUuids 商品productUuid集合
	 * 
	 */
	public void recycleProductDesc(List<String> productUuids){
		if(productUuids==null ||productUuids.size()==0){
			return;
		}
		for (String productUuid : productUuids) {
			mcc.delete(ProductCacheConstant.PRODUCT_PRODUCTMAIN_KEY+productUuid);
		}
		myDao.recycleProductDesc(productUuids);
	}
}
