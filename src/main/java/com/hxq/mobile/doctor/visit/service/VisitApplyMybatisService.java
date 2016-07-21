package com.hxq.mobile.doctor.visit.service;

import com.hxq.mobile.entity.visit.VisitApply;
import com.hxq.mobile.util.repository.MybatisBaseService;

import java.util.Map;

/**
 * Created by Alice on 2016/4/25 0025.
 * 随访申请service
 */
public interface VisitApplyMybatisService extends MybatisBaseService<VisitApply,String> {
    int countCustomerByVisitPrecept(Map<String,Object> map);
}
