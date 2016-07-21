package com.aebiz.b2b2c.customermgr.storeback.dao.customerstorelevel;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelModel;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelQueryModel;

@Repository
public class CustomerStoreLevelH4Impl extends
		BaseH4Impl<CustomerStoreLevelModel, CustomerStoreLevelQueryModel>
		implements CustomerStoreLevelDAO {

	/**
	 * 校验商户会员等级名称是否存在
	 */
	@Override
	public boolean checkLevelNameExist(String levelName, String uuid) {
		StringBuffer hql = new StringBuffer(
				" from CustomerStoreLevelModel cslm where cslm.levelName=:levelName ");
		Query query = null;
		if (!StringUtil.isEmpty(uuid)) {
			hql.append(" and cslm.uuid!=:uuid ");
			query = this.getH4Session().createQuery(hql.toString());
			query.setString("levelName", levelName);
			query.setString("uuid", uuid);
		} else {
			query = this.getH4Session().createQuery(hql.toString());
			query.setString("levelName", levelName);
		}
		if (query.list() != null && query.list().size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据商户编号获取商户会员等级的集合
	 */
	@Override
	public List<CustomerStoreLevelModel> getStoreLevelModelList(String storeUuid) {
		StringBuffer hql = new StringBuffer(
				" from CustomerStoreLevelModel where storeUuid=:storeUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("storeUuid", storeUuid);
		List<CustomerStoreLevelModel> storeLevelModelList = query.list();

		return storeLevelModelList;
	}
}
