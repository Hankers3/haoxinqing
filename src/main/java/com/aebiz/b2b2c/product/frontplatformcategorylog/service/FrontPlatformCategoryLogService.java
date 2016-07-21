package com.aebiz.b2b2c.product.frontplatformcategorylog.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.product.frontplatformcategorylog.vo.FrontPlatformCategoryLogModel;
import com.aebiz.b2b2c.product.frontplatformcategorylog.vo.FrontPlatformCategoryLogQueryModel;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;

public interface FrontPlatformCategoryLogService extends BaseService<FrontPlatformCategoryLogModel,FrontPlatformCategoryLogQueryModel>{
	/**
	 * 获取所有操作得后台分类uuid集合
	 * 
	 * @return
	 */
	public List<String> getPlatfromCategoryUuidNoSame();
	
	/**
	 * 批量添加分类日志
	 * 
	 * @param frontCategoryUuid
	 * @param platfromCategoryUuids
	 * @param type
	 */
	public void bathAddLog(String frontCategoryUuid,List<String> platfromCategoryUuids,String type);
}
