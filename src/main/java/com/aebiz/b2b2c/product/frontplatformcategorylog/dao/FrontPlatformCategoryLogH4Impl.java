package com.aebiz.b2b2c.product.frontplatformcategorylog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

import com.aebiz.b2b2c.product.frontplatformcategorylog.vo.FrontPlatformCategoryLogModel;
import com.aebiz.b2b2c.product.frontplatformcategorylog.vo.FrontPlatformCategoryLogQueryModel;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveQueryModel;

@Repository
public class FrontPlatformCategoryLogH4Impl extends BaseH4Impl<FrontPlatformCategoryLogModel,FrontPlatformCategoryLogQueryModel> implements FrontPlatformCategoryLogDAO {

	
	/**
	 * 获取所有操作得后台分类uuid集合
	 * 
	 * @return
	 */
	public List<String> getPlatfromCategoryUuidNoSame() {
		ProductInteractiveModel out = new ProductInteractiveModel();
		String hql = "select o.platformcategoryUuid from FrontPlatformCategoryLogModel o where 1=1 and o.same=:same ";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("same", FrontPlatformCategoryLogModel.NO_SAME);
		
		List<String> list = query.list();
		return list;
	}
}
