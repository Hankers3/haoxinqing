package com.hxq.mobile.patient.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.entity.common.DoctorRight;
import com.hxq.mobile.patient.common.service.DoctorRightService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.ObjectUtils;


@Service("com.hxq.mobile.patient.common.service.DoctorRightService")
public class DoctorRightServiceImpl extends SpringJdbcSimpleEntityService implements DoctorRightService {

	/* 获取医生的权限设置信息*/
	@Override
	public DoctorRight selectDoctorRight(String DoctorUuid) {
		List<Map<String,Object>> lst = dao.query("select phone,plus,teletext,personal from doctor_right where doctorUuid=?",
				new Object[]{DoctorUuid}, null, getQueryCache());
		return ObjectUtils.isEmpty(lst) ? null : SimpleBean2DBHelper.map2Bean(lst.get(0), DoctorRight.class);
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {return null;}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "DoctorRightService";}
}
