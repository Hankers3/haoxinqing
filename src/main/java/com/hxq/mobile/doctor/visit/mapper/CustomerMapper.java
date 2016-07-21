package com.hxq.mobile.doctor.visit.mapper;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.util.mybatis.BaseMapper;
import com.wxcommon.mybatis.MyBatisRepository;

@MyBatisRepository
public interface CustomerMapper extends BaseMapper<Customer, String> {
    Customer selectByMobile(String mobile);
    List<Customer> selectByCustomerList(Map<String,Object> map);
}