package com.hxq.mobile.patient.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.entity.common.DoctorNotice;
import com.hxq.mobile.patient.common.service.DoctorNoticeService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.patient.common.service.DoctorNoticeService")
public class DoctorNoticeServiceImpl extends SpringJdbcSimpleEntityService implements DoctorNoticeService {

	/**
	 *根据医生uuid来获取用户公告的信息
	 */
	@Override
	public DoctorNotice selectDoctorNoticeByDoctorUuid(String doctorId) throws Exception {
		List<Map<String,Object>> lst = dao.query(
				"select uuid,delFlag,doctorName,createTime,content from doctor_notice where serviceStatffUuid=? order by createTime desc",
				new Object[]{doctorId}, null, getQueryCache());
		return ObjectUtils.isEmpty(lst) ? null : SimpleBean2DBHelper.map2Bean(lst.get(0), DoctorNotice.class);
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {return null;}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "DoctorNoticeService";}
}
