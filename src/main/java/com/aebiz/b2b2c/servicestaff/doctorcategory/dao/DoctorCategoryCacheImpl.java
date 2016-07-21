package com.aebiz.b2b2c.servicestaff.doctorcategory.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.servicestaff.bankrelation.dao.BankRelationDAO;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.doctorcategory.vo.DoctorCategoryModel;
import com.aebiz.b2b2c.servicestaff.doctorcategory.vo.DoctorCategoryQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 上午11:06:05
 */
@Primary
@Repository
public class DoctorCategoryCacheImpl extends
		BaseMemcachedCache<DoctorCategoryModel, DoctorCategoryQueryModel>
		implements DoctorCategoryDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private DoctorCategoryDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public DoctorCategoryCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_DOCTORCATEGORY_KEY);
	}
	/**
	 * 
	 * @Description: (这里用一句话描述这个方法的作用)    
	 * @author XP  
	 * @param categoryName
	 * @return
	 * @date 2015-12-29 上午11:12:04
	 */
        @Override
        public String checkCateGoryName(String categoryName) {
            return dao.checkCateGoryName(categoryName);
        }

	
}
