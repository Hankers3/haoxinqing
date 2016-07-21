package com.hxq.mobile.patient.visit.service;

import com.hxq.mobile.entity.weixin.ProfessionResult;
import com.hxq.mobile.util.repository.BaseService;

import java.util.List;
import java.util.Map;

public interface CszyResultService extends BaseService<ProfessionResult, String>{
	
    List<ProfessionResult> selectByCondition(ProfessionResult record) throws Exception;
    
    /**
	 * 通过患者id得到自评的历史记录
	 * 
	 * @param costomerUuid
	 * @return
	 */
	public List<Map<String, Object>> selectHistory (String customerUuid);
}
