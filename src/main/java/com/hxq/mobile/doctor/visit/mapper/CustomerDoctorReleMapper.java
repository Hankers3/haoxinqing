package com.hxq.mobile.doctor.visit.mapper;

import com.hxq.mobile.entity.common.CustomerDoctorRele;
import com.hxq.mobile.util.mybatis.BaseMapper;
import com.wxcommon.mybatis.MyBatisRepository;

import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface CustomerDoctorReleMapper extends BaseMapper<CustomerDoctorRele, String> {
	 CustomerDoctorRele selectByCustomerUuidAndDoctorUuid(@Param("customerUuid") String customerUuid, @Param("doctorUuid") String doctorUuid);

	 List<CustomerDoctorRele> selectBygroupUuidAndDoctorUuid(@Param("groupUuid") String groupUuid, @Param("doctorUuid") String doctorUuid);

	 int deleteByCustomerIdAndGroupId(@Param("customerUuid") String customerUuid, @Param("groupUuid") String groupUuid);

	 int countCustomerByDoctorUuid(@Param("doctorUuid") String doctorUuid);
}