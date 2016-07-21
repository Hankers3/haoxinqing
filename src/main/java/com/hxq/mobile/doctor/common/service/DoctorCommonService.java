package com.hxq.mobile.doctor.common.service;

import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.util.api.ApiResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Alice on 2016/4/26 0026.
 */
public interface DoctorCommonService {
	ServiceStaff registration_v2(Map<String, String> map) throws Exception;

    ApiResult gotoLogin_v2(HttpServletRequest request,String mobile,String password) throws Exception;

    ApiResult registerTwo_v2(Map<String, String> map) throws Exception;

    ApiResult registerThree_v2(HttpServletRequest request) throws Exception;
}
