package com.aebiz.b2b2c.cms.platformapply.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyModel;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyQueryModel;

public interface PlatformApplyService extends BaseService<PlatformApplyModel,PlatformApplyQueryModel>{

	/**
	 * 通过id得到List
	 * @param userId
	 * @param userType
	 * @return
	 */
	public List<PlatformApplyModel> getByUserid(String userId, String userType);

}
