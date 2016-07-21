package com.hxq.mobile.common.service;

import java.util.List;

import com.hxq.mobile.entity.common.BasicdataAreaCity;
import com.hxq.mobile.entity.common.BasicdataAreaProvince;
import com.hxq.mobile.entity.common.BasicdataAreaRegion;
import com.hxq.mobile.entity.common.DepartmentInfo;
import com.hxq.mobile.entity.common.HospitalInfo;
import com.hxq.mobile.entity.common.Tags;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * 省、市、县、区、医院、部门
 *
 */
public interface BasicDataService extends SimpleEntityService {
    List<BasicdataAreaProvince> selectAllProvince() throws Exception;

    List<BasicdataAreaCity> selectCityByProvinceUuid(String provinceUuid) throws Exception;

    List<BasicdataAreaRegion> selectRegionByCityId(String cityUuid) throws Exception;

    List<HospitalInfo> selectHospital(String provinceUuid, String cityUuid, String regionUuid) throws Exception;

    List<DepartmentInfo> selectAllDepartment() throws Exception;

    List<Tags> selectAllTags() throws Exception;
}