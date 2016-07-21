package com.hxq.mobile.patient.visit.service;

import java.util.Date;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;
import com.wxcommon.repository.AbstractEntity;

/**
 * 患者端随访记录的:睡眠情况/进食情况/其它情况/其它检查及结果/体重记录
 *
 */
public interface VisitRecordExtendService extends SimpleEntityService {
	/**
	 * 保存随访记录下的其他检查及结果
	 * @param bean 名字、描述、图片
	 * @param imgs 图片id，通过逗号间隔
	 * @return
	 * @throws Exception
	 */
	public int insertCheckResult(AbstractEntity<?> bean, String imgs) throws Exception;

	/**
	 * 保存随访记录下的病情记录
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public int insertIllnessRecord(Map<String, Object> params) throws Exception;

	/**
	 * 保存随访记录下的病情记录
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public int insertDrugReaction(Map<String, Object> params) throws Exception;
	
	/**
	 * 检查本随访周期内该患者是否填写了睡眠情况
	 * @param customerUuid 患者id
	 * @param createTime 检查周期起始时间
	 * @return null参数不正确，true未填写，false已填写
	 */
	public Boolean select4SleepPeriodCheck(String customerUuid, Date opeTime);
		
	/**
	 * 检查本随访周期内该患者是否填写了进食情况
	 * @param customerUuid 患者id
	 * @param createTime 检查周期起始时间
	 * @return null参数不正确，true未填写，false已填写
	 */
	public Boolean select4FoodPeriodCheck(String customerUuid, Date opeTime);

	/**
	 * 检查本随访周期内该患者是否填写了其他情况
	 * @param customerUuid 患者id
	 * @param createTime 检查周期起始时间
	 * @return null参数不正确，true未填写，false已填写
	 */
	public Boolean select4OtherPeriodCheck(String customerUuid, Date opeTime);

	/**
	 * 检查本随访周期内该患者是否填写了体重情况
	 * @param customerUuid 患者id
	 * @param createTime 检查周期起始时间
	 * @return null参数不正确，true未填写，false已填写
	 */
	public Boolean select4WeightPeriodCheck(String customerUuid, Date opeTime);
	
	/**
	 * 检查本随访周期内该患者是否填写心电图
	 * @param customerUuid 患者id
	 * @param createTime 检查周期起始时间
	 * @return null参数不正确，true未填写，false已填写
	 */
	public Boolean select4ElectrocardiogramPeriodCheck(String customerUuid, Date opeTime);
	
	/**
	 * 检查本随访周期内该患者是否填写血常规
	 * @param customerUuid 患者id
	 * @param createTime 检查周期起始时间
	 * @return null参数不正确，true未填写，false已填写
	 */
	public Boolean select4BloodRoutinePeriodCheck(String customerUuid, Date opeTime);
	
	/**
	 * 检查本随访周期内该患者是否填写肝功能
	 * @param customerUuid 患者id
	 * @param createTime 检查周期起始时间
	 * @return null参数不正确，true未填写，false已填写
	 */
	public Boolean select4HepaticPeriodCheck(String customerUuid, Date opeTime);
	
	/**
	 * 检查本随访周期内该患者是否填写其他项
	 * @param customerUuid 患者id
	 * @param createTime 检查周期起始时间
	 * @return null参数不正确，true未填写，false已填写
	 */
	public Boolean select4ExtendPeriodCheck(String customerUuid, String preceptExtendUuid, Date opeTime);
}
