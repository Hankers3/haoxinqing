package com.aebiz.b2b2c.customermgr.storeback.service.customerstorelevel;

import java.util.List;

import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelModel;

/**
 * 商户会员等级对外接口
 * 
 * @author likun
 * 
 */
public interface CustomerStoreLevelInteractive {
	/**
	 * 根据商户编号获取商户会员等级的集合
	 * 
	 * @param storeUuid
	 *            商户编号
	 * @return
	 */
	public List<CustomerStoreLevelModel> getStoreLevelModelList(String storeUuid);

}
