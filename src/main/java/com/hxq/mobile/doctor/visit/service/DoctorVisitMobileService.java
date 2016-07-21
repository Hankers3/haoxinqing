package com.hxq.mobile.doctor.visit.service;

import com.hxq.mobile.util.api.ApiResult;

/**
 * Created by Alice on 2016/4/26 0026.
 */
public interface DoctorVisitMobileService {
    ApiResult getRecommendVisitpreceptByDoctorid_v2(String doctorUuid) throws Exception;
    ApiResult getMyVisitPreceptList_v2(String doctorUuid) throws Exception;
}
