package com.aebiz.b2b2c.cms.platformapply.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyModel;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyQueryModel;

public interface PlatformApplyDAO extends BaseDAO<PlatformApplyModel,PlatformApplyQueryModel>{

	/**
	 * 通过id得到List
	 * @param userId
	 * @param userType
	 * @return
	 */
	public List<PlatformApplyModel> getByUserid(String userId, String userType);

}