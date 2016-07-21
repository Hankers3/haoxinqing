package com.hxq.mobile.doctor.visit.service;

import java.util.Map;

import com.hxq.mobile.util.api.ApiResult;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * Created by Alice on 2016/5/5 0005.
 * 医生与患者相关操作service
 */
public interface DoctorCustomerService extends SimpleEntityService {
    ApiResult getCustomerByMobile_v2(Map<String, String> map) throws Exception;

    ApiResult deleteCustomerByCustomerUuidAndGid_v2(String gid, String customerUuid, String doctorUuid) throws Exception;

    ApiResult selectCustomerByDoctorUuidAndGroupUuid_v2(String doctorUuid, String groupUuid) throws Exception;

    Map<String, Object> selectCustomerDoctorRele_v2(String uuid) throws Exception;

	int addCustomer_v2(String id, String uuid, String doctorUuid, String mobile, String name, String illnessDescription, String groupId) throws Exception;

	/**
	 * 统计某医生下关联患者数量
	 * @param doctorUuid 医生id
	 * @return
	 * @throws Exception
	 */
	int selectCustomerNumModel(String doctorUuid) throws Exception;
}
