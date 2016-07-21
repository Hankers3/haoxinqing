package com.hxq.mobile.doctor.visit.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 获取随访申请列表
 *
 */
public interface VisitApplyService extends SimpleEntityService {

	/**
	 * 根据患者id获取患者信息
	 * @param customerUuid 患者id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectCustomerModel(String customerUuid);

	/**
	 * 根据uuid获取患者信息
	 * @param visitApplyUuid  uuid 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectVisitApplyListByUuid(String visitApplyUuid);

	public Map<String, Object> selectApplyDetail(String customerUuid, String applyUuid, String doctorUuid,
			String symptoms) throws Exception;

	public Map<String, Object> selectVisitApplyList(String customerUuid, String doctorUuid, String applyUuid,
			String createTime) throws Exception;

	public int updateForRefuseVivistApply(String applyUuid, String refuseReason) throws Exception;

	public List<Map<String,Object>> getByPreceptUuid(String doctorUuid,String preceptUuid) throws Exception;
}
