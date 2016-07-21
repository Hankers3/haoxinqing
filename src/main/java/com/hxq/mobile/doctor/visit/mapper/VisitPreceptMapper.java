package com.hxq.mobile.doctor.visit.mapper;

import com.hxq.mobile.entity.visit.VisitPrecept;
import com.hxq.mobile.util.mybatis.BaseMapper;
import com.wxcommon.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface VisitPreceptMapper  extends BaseMapper<VisitPrecept,String> {
    List<VisitPrecept> getRecommendVisitpreceptList();
    List<VisitPrecept> getMyVisitpreceptByDoctorid(String doctorUuid) throws Exception;
}