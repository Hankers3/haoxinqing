package com.hxq.mobile.doctor.visit.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.entity.visit.VisitPrecept;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 随访方案
 *
 */
public interface VisitPreceptService extends SimpleEntityService {

	/**
	 * 根据医生id和患者id获取患者和医生关联关系
	 * 
	 * */
	public Map<String, Object> selectCustomerDoctorReleByid(String customerUuid, String doctorUuid);
	/**
	 * 根据医生id和患者id获取随访推送
	 * 
	 * */
	public Map<String, Object> selectVisitPreceptPushByid(String doctorUuid, String customerUuid);
	
	public int updateAgreeVisitApply(String customerUuid, String doctorUuid, String doctorName, String visitPreceptUuid,
			String visitApplyUuid, Integer period) throws Exception;
	
	public int updateVisitRecord(String doctorUuid, String customerUuid, String visitPreceptUuid, Integer period) throws Exception;
	/**
	 * 根据uuid得到随访方案信息
	 * @param visitUuid
	 * @return
	 */
	public Map<String,Object> getByUuid(String visitUuid);
	

	public List<Map<String,Object>> getMyVisitpreceptByDoctorid(String doctorUuid);
	/**
	 * 得到随访方案数据
	 * @param delFlag   方案是否正常(0:未完成,1:正常,2:已正常)
	 * @param recommend 方案类型(0:医生定义,1:系统自定义,2:患者与医生关联)
	 * @param doctorUuid 医生id
	 * @param customerUuid 患者id
	 * @return
	 */
	public VisitPrecept getMyVisitpreceptByDoctoridAndCustomerId(String delFlag,String recommend,String doctorUuid,String customerUuid);

	/**
	 * 根据医生id,获取所有患者的方案数据
	 * @param delFlag		方案是否删除
	 * @param recommend	 	方案的类型(2:医生,患者方案)
	 * @param doctorUuid	医生id
	 * @return
	 */
	public List<Map<String,Object>> getVisitpreceptByDoctorid(String delFlag,String recommend,String doctorUuid);
	

}
