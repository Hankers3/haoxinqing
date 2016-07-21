package com.aebiz.b2b2c.customermgr.storeback.dao.customerstorelevel;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelModel;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelQueryModel;

public interface CustomerStoreLevelDAO extends
		BaseDAO<CustomerStoreLevelModel, CustomerStoreLevelQueryModel> {
	/**
	 * 校验商户会员等级名称是否存在
	 * 
	 * @param levelName
	 *            等级名称
	 * @return
	 */
	public boolean checkLevelNameExist(String levelName, String uuid);

	/**
	 * 根据商户编号获取商户会员等级的集合
	 * 
	 * @param storeUuid
	 *            商户编号
	 * @return
	 */
	public List<CustomerStoreLevelModel> getStoreLevelModelList(String storeUuid);

}