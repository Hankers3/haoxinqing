package com.hxq.mobile.patient.visit.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.util.repository.SimpleEntityService;


/**
 * 随访表单（记录）
 *
 */
public interface VisitRecordService extends SimpleEntityService {
	List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination);
	
	/**
	 * 	根据医生id 获取随访患者的数量
	 * @param doctorId
	 * @return
	 */
	int selectVisitRecordNumByDoctorId(String doctorId);

	/**
	 * 获取当前时间周期内的指定患者、医生和随访方案的随访表单记录
	 * @param preceptUuid
	 * @param createTime
	 * @return
	 */
	VisitRecord selectRecentVisitRecordByPreceptUuid(String customerUuid, String serviceStaffUuid, String preceptUuid, Date createTime);
}
