package com.hxq.mobile.doctor.visit.mapper;

import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.util.mybatis.BaseMapper;
import com.wxcommon.mybatis.MyBatisRepository;

import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface VisitRecordMapper extends BaseMapper<VisitRecord, String> {
    List<VisitRecord> selectByCustomerUuid(String customerUuid);

    List<VisitRecord> selectByCustomerUuidAndDoctorUuid(@Param("customerUuid") String customerUuid, @Param("doctorUuid") String doctorUuid);

    List<String> selectCustomerUuidByDoctorUuid(String doctorUuid);

    int countVisitRecordByDoctorUuid(String doctorUuid);
}