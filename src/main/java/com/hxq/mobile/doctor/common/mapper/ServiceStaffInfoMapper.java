package com.hxq.mobile.doctor.common.mapper;

import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.util.mybatis.BaseMapper;
import com.wxcommon.mybatis.MyBatisRepository;

@MyBatisRepository
public interface ServiceStaffInfoMapper extends BaseMapper<ServiceStaffInfo,String>{
    ServiceStaffInfo selectByDoctorUuid(String doctorUuid);
}