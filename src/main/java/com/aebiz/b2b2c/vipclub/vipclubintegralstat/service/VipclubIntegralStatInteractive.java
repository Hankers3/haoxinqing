package com.aebiz.b2b2c.vipclub.vipclubintegralstat.service;

/**
 * 会员积分统计对位接口
 * @author huyingying
 *
 */
public interface VipclubIntegralStatInteractive {
	
	/**
	 * 通过会员uuid获取可用积分
	 * @param customerUuid
	 * @return
	 */
	public int getUsableIntegralBycustomerUuid(String customerUuid);

}
