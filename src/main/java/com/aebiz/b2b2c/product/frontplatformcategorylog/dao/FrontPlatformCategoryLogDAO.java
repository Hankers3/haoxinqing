package com.aebiz.b2b2c.product.frontplatformcategorylog.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.frontplatformcategorylog.vo.FrontPlatformCategoryLogModel;
import com.aebiz.b2b2c.product.frontplatformcategorylog.vo.FrontPlatformCategoryLogQueryModel;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;

public interface FrontPlatformCategoryLogDAO extends BaseDAO<FrontPlatformCategoryLogModel,FrontPlatformCategoryLogQueryModel>{
	/**
	 * 获取所有操作得后台分类uuid集合
	 * 
	 * @return
	 */
	public List<String> getPlatfromCategoryUuidNoSame();
}