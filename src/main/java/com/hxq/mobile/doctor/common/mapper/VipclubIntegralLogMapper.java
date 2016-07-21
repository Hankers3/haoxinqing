package com.hxq.mobile.doctor.common.mapper;

import com.hxq.mobile.entity.common.VipclubIntegralLog;
import com.hxq.mobile.util.mybatis.BaseMapper;
import com.wxcommon.mybatis.MyBatisRepository;

@MyBatisRepository
public interface VipclubIntegralLogMapper extends BaseMapper<VipclubIntegralLog,String>{
    VipclubIntegralLog selectSelective(VipclubIntegralLog vipclubIntegralLog);
}