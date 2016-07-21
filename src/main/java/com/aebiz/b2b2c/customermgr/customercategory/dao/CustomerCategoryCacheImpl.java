package com.aebiz.b2b2c.customermgr.customercategory.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryModel;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerCategoryCacheImpl extends
		BaseMemcachedCache<CustomerCategoryModel,CustomerCategoryQueryModel> implements
		CustomerCategoryDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerCategoryDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerCategoryCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERCATEGORY_KEY);
	}
	/**
	 * 
	 * @Description: (这里用一句话描述这个方法的作用)    
	 * @author XP  
	 * @param categoryName
	 * @return
	 * @date 2015-12-29 下午4:29:52
	 */
        @Override
        public String checkCateGoryName(String categoryName) {
            return dao.checkCateGoryName(categoryName);
        }
        /**
         * 
         * @Description: (这里用一句话描述这个方法的作用)    
         * @author XP  
         * @return
         * @date 2015-12-29 下午4:30:12
         */
        @Override
        public List<CustomerCategoryModel> getCustomerCategoryModelList() {
            return dao.getCustomerCategoryModelList();
        }

	
}
