package com.hxq.mobile.doctor.visit.service;

import com.hxq.mobile.entity.visit.VisitPrecept;
import com.hxq.mobile.util.repository.MybatisBaseService;

import java.util.List;

/**
 * Created by Alice on 2016/4/25 0025.
 * 医生端随访service
 */
public interface VisitPreceptMybatisService extends MybatisBaseService<VisitPrecept,String> {
    List<VisitPrecept> getRecommendVisitpreceptList() throws Exception;

    List<VisitPrecept> getMyVisitpreceptByDoctorid(String doctorUuid) throws Exception;
}
