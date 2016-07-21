package com.hxq.mobile.doctor.visit.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.api.ApiResult;
import com.hxq.mobile.util.repository.SimpleEntityService;

/** 
* @author  作者 E-mail: liuyang
* @date 创建时间：2016年5月17日 下午7:54:43 
* @version 2.0 
* @parameter  
* @since  
* @return  
*/
public interface CaseGroupSerivce extends SimpleEntityService{
	/**
	 * 根据医生id获取所有的分组信息
	 * @param doctorUuid
	 * @return
	 */
	public List<Map<String,Object>> getByDoctorUuid(String doctorUuid);
	
    ApiResult updateCaseGroup_v2(String groupUuid, String groupName) throws Exception;

    ApiResult insertCaseGroup_v2(String doctorUuid, String groupName) throws Exception;

    ApiResult deleteCaseGroup_v2(String groupUuid) throws Exception;

	public List<Map<String,Object>> selectConsultRecordByid(String doctorUuid, String consultType) throws Exception;

	public Object selectConsultRecordMap(List<Map<String, Object>> onlines,String doctorUuid) throws Exception;
	
	public List<Map<String, Object>> selectConsultRecordInformation(String customerUuid,String doctorUuid) throws Exception;

	public Object selectPatientConsultInformation(String customerUuid,String doctorUuid) throws Exception;

	Map<String, Object> selectConsultRecordByCustomerUuid(String customerUuid,String doctorUuid) throws Exception;

	int selectConsultRecordCount(String contentUuid) throws Exception;

	public int selectUnreadConsultRecord(String doctorUuid) throws Exception;

	public Object selectDoctorConsultInformation(String doctorUuid) throws Exception;

	List<Map<String, Object>> selectDoctorConsultRecordInformation(String doctorUuid) throws Exception;

	public int selectPatientByUnreadConsultRecord(String customerUuid) throws Exception;

}
