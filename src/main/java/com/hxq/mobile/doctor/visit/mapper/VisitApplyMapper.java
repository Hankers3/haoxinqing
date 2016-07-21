package com.hxq.mobile.doctor.visit.mapper;

import com.hxq.mobile.entity.visit.VisitApply;
import com.hxq.mobile.util.mybatis.BaseMapper;
import com.wxcommon.mybatis.MyBatisRepository;

import java.util.Map;

@MyBatisRepository
public interface VisitApplyMapper  extends BaseMapper<VisitApply,String> {
    int countCustomerByVisitPrecept(Map<String,Object> map);
}