package com.hxq.mobile.doctor.visit.service;

import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.util.repository.MybatisBaseService;

import java.util.List;

/**
 * Created by Alice on 2016/4/26 0026.
 */
public interface VisitRecordMybatisService extends MybatisBaseService<VisitRecord, String> {
    List<VisitRecord> selectByCustomerUuid(String customerUuid);
}
