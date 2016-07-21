package com.hxq.mobile.doctor.visit.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 医生端随访记录的:睡眠情况/进食情况/其它情况/其它检查及结果/体重记录
 *
 */
public interface VisitRecordExtendService extends SimpleEntityService {

	/**
	 * 获取随访记录下的睡眠情况
	 * @param visitRecordUuid 随访记录id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectSleep(String visitRecordUuid);

	/**
	 * 获取随访记录下的进食情况
	 * @param visitRecordUuid 随访记录id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectEat(String visitRecordUuid);

	/**
	 * 获取随访记录下的其它情况
	 * @param visitRecordUuid 随访记录id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectOther(String visitRecordUuid);

	/**
	 * 获取随访记录下的其他检查及结果
	 * @param visitRecordUuid 随访记录id
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectCheckResult(String visitRecordUuid);
	
	/**
	 * 获取随访记录下的所有数据
	 * @param visitRecordUuid随访记录id
	 * @return
	 */
	public List<Map<String,Object>> getAllVisitPreceptByPreceptUuid(String visitRecordUuid);

	/**
	 * 获取随访记录下的体重记录
	 * @param visitRecordUuid 随访记录id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectWeight(String visitRecordUuid);
}
