package com.hxq.mobile.doctor.visit.mapper;

import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.util.mybatis.BaseMapper;
import com.wxcommon.mybatis.MyBatisRepository;

@MyBatisRepository
public interface CustomerInfoMapper extends BaseMapper<CustomerInfo, String> {
    CustomerInfo selectByCustomerUuid(String customerUuid);
}