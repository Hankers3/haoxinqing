package com.aebiz.b2b2c.cms.platformapply.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyModel;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyQueryModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;

@Repository
public class PlatformApplyH4Impl extends
		BaseH4Impl<PlatformApplyModel, PlatformApplyQueryModel> implements
		PlatformApplyDAO {
	/**
	 * 通过id得到List
	 * 
	 * @param userId
	 * @param userType
	 * @return
	 */
	@Override
	public List<PlatformApplyModel> getByUserid(String userId, String userType) {
		StringBuffer hql = new StringBuffer(
				" select o from PlatformApplyModel as o,PlatFormInfoModel as p where o.userUuid =:userId and o.userType =:userType and o.vidoUuid = p.uuid order by p.startTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("userId", userId);
		query.setString("userType", userType);

		List list  = query.list();
		if (list != null && list.size()>0) {
			return  (List<PlatformApplyModel>) list;
		}
		return null;
	}
    

}
