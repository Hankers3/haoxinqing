package com.hxq.mobile.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.common.service.BasicDataService;
import com.hxq.mobile.entity.common.BasicdataAreaCity;
import com.hxq.mobile.entity.common.BasicdataAreaProvince;
import com.hxq.mobile.entity.common.BasicdataAreaRegion;
import com.hxq.mobile.entity.common.DepartmentInfo;
import com.hxq.mobile.entity.common.HospitalInfo;
import com.hxq.mobile.entity.common.Tags;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.ObjectUtils;

/**
 * 基础数据
 *
 */
@Service("com.hxq.mobile.common.service.BasicDataService")
public class BasicDataServiceImpl extends SpringJdbcSimpleEntityService implements BasicDataService {

	@Override
	public List<BasicdataAreaProvince> selectAllProvince() throws Exception {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select uuid,oper,opeTime,delFlag,provinceName,state from basicdata_area_province where delFlag='1' order by uuid");
		List<Map<String, Object>> lst = dao.query(sbf.toString(), null, null, getQueryCache());
		return SimpleBean2DBHelper.mapList2BeanList(lst, BasicdataAreaProvince.class);
	}

	@Override
	public List<BasicdataAreaCity> selectCityByProvinceUuid(String provinceUuid) throws Exception {
		if(ObjectUtils.isEmpty(provinceUuid)) return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select uuid,oper,opeTime,delFlag,provinceUuid,cityName,latitude,longitude,state")
		.append(" from basicdata_area_city where delFlag='1' and provinceUuid=? order by uuid");
		List<Map<String, Object>> lst = dao.query(sbf.toString(), new Object[]{provinceUuid}, null, getQueryCache());
		return SimpleBean2DBHelper.mapList2BeanList(lst, BasicdataAreaCity.class);
	}

	@Override
	public List<BasicdataAreaRegion> selectRegionByCityId(String cityUuid) throws Exception {
		if(ObjectUtils.isEmpty(cityUuid)) return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select uuid,oper,opeTime,delFlag,cityUuid,regionName,zipcode,cityCode")
		.append(" from basicdata_area_region where delFlag='1' and cityUuid=? order by uuid");
		List<Map<String, Object>> lst = dao.query(sbf.toString(), new Object[]{cityUuid}, null, getQueryCache());
		return SimpleBean2DBHelper.mapList2BeanList(lst, BasicdataAreaRegion.class);
	}

	@Override
	public List<HospitalInfo> selectHospital(String provinceUuid, String cityUuid, String regionUuid) throws Exception {
		if(ObjectUtils.isEmpty(provinceUuid) && ObjectUtils.isEmpty(cityUuid) && ObjectUtils.isEmpty(regionUuid)) return null;

		StringBuffer sbf = new StringBuffer(1000);
		List<String> params = new ArrayList<String>();

		sbf.append("select uuid,delFlag,opeTime,oper,hospitalName,hospitalClassify,province,city,")
		.append("region,createTime,note,address,hospitalLevel from hospital_info where delFlag='1'");

		if(!ObjectUtils.isEmpty(provinceUuid)) {
			sbf.append(" and province=?");
			params.add(provinceUuid);
		}
		if(!ObjectUtils.isEmpty(cityUuid)) {
			sbf.append(" and city=?");
			params.add(cityUuid);
		}
		if(!ObjectUtils.isEmpty(regionUuid)) {
			sbf.append(" and region=?");
			params.add(regionUuid);
		}

		List<Map<String, Object>> lst = dao.query(sbf.toString(), params.toArray(), null, getQueryCache());
		return SimpleBean2DBHelper.mapList2BeanList(lst, HospitalInfo.class);
	}

	@Override
	public List<DepartmentInfo> selectAllDepartment() throws Exception {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select uuid,delFlag,opeTime,oper,departmentName,createTime,note,mobile,departmentId")
		.append(" from department_info where delFlag='1' order by note");
		List<Map<String, Object>> lst = dao.query(sbf.toString(), null, null, getQueryCache());
		return SimpleBean2DBHelper.mapList2BeanList(lst, DepartmentInfo.class);
	}

	@Override
	public List<Tags> selectAllTags() throws Exception {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select uuid,delFlag,opeTime,oper,tagName from tags where delFlag='1' order by uuid");
		List<Map<String, Object>> lst = dao.query(sbf.toString(), null, null, getQueryCache());
		return SimpleBean2DBHelper.mapList2BeanList(lst, Tags.class);
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "BasicDataService";}
}