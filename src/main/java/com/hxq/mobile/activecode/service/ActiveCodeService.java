package com.hxq.mobile.activecode.service;

import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;
/**
 * 
 * @author qjy
 * 
 *邀请码
 */
public interface ActiveCodeService extends SimpleEntityService{
	
	public Object[] selectActiveCodeInfo(Map<String, Object> map);

	public Object[] selectDetails(Map<String, Object> map);

}
