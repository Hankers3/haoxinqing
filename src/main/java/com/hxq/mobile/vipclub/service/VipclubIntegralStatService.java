package com.hxq.mobile.vipclub.service;

import com.hxq.mobile.entity.common.VipclubIntegralStat;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 会员积分统计表Service接口
 */
public interface VipclubIntegralStatService extends SimpleEntityService {
	/**
	 * 通过会员id,积分类型查询会员积分统计表
	 *
	 * @param customerUuid
	 * @return integralStatModel
	 */
	public VipclubIntegralStat selectByCustomerUuidAndIntegralType(String customerUuid, String integralType);
}
