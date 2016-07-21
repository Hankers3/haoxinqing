package com.aebiz.b2b2c.customermgr.customershoplevel.service;

import java.util.List;

import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;

/**
 * 平台会员等级会外接口
 * 
 * @author likun
 * 
 */
public interface CustomerShopLevelInteractive {
	/**
	 * 得到平台会员等级model的集合
	 * 
	 * @return
	 */
	public List<CustomerShopLevelModel> getShopLevelModelList();
}
