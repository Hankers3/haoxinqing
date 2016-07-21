package com.hxq.mobile.doctor.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.util.mybatis.BaseMapper;
import com.wxcommon.mybatis.MyBatisRepository;

@MyBatisRepository
public interface ServiceStaffMapper extends BaseMapper<ServiceStaff, String> {

    /**
     * 使用手机号查询医生信息表
     *
     * @param mobile 手机号
     * @return ServiceStaff
     */
    ServiceStaff selectByMobile(String mobile);

    /**
     * 验证登录
     * @param mobile
     * @param password
     * @return
     */
    ServiceStaff selectByMobileAndPassword(@Param("mobile") String mobile, @Param("password") String password);
    
    
    List<ServiceStaff>  selectByServiceStaffList(Map<String,Object> map);
}