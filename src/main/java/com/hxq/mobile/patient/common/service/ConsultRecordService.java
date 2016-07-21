package com.hxq.mobile.patient.common.service;


import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

public interface ConsultRecordService extends SimpleEntityService{

	/**
	 * 获取医生的咨询总量
	 * @param doctorId
	 * @return
	 */
     public int selectConsultNumBydoctorId(String doctorId);
     
     /**
      * 获取医生的加号总量
      * @param doctorId
      * @return
      */
     public int selectAllOrderNumByDoctorId(String doctorId);

    /**
 	 * 通过患者id得到ConsultRecordModel的List
 	 * @param uuid
 	 * @param type 咨询类型  0在线咨询即图文咨询  1电话咨询  2预约加号 
 	 * @return
 	 */
 	public List<Map<String, Object>> selectByCustomerUuid(String uuid, String type);
 	
    /**
	 * 通过医生id得到ConsultRecordModel的List
	 * @param uuid
	 * @param type 1传入医生id,2传入患者id
	 * @return
	 */
	public List<Map<String, Object>> selectConsultRecordByUuidAndConsultType(String doctorUuid, String type);
	
	
	
 	
 	/**
 	 * 通过患者id查询list
 	 * @param customerUuid
 	 * @return
 	 */
 	public Object[] selectByCustomerUuidList(String customerUuid,Integer pageCount, Integer pageNo);
 	
 	/**
 	 * 根据患者id,医生id,查询出最后一个问题
 	 * @param customerUuid
 	 * @param doctorUuid
 	 * @return
 	 */
	public List<Map<String, Object>> selectByCustomerUuidAndDoctorUuid(String customerUuid,String doctorUuid);
 	
 	/**
 	 * 通过患者id查询未读消息的数量
 	 */
 	public int selectUnread(String customerUuid); 
 	
}
