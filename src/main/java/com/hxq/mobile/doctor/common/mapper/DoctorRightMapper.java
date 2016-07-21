package com.hxq.mobile.doctor.common.mapper;

import com.hxq.mobile.entity.common.DoctorRight;
import com.hxq.mobile.util.mybatis.BaseMapper;
import com.wxcommon.mybatis.MyBatisRepository;

@MyBatisRepository
public interface DoctorRightMapper extends BaseMapper<DoctorRight,String>{
    DoctorRight selectByDoctorUuid(String DoctorUuid);
}