package com.hxq.mobile.patient.visit.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.patient.visit.service.IllnessRecordService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * 病情变化记录
 *
 */
@Service("com.hxq.mobile.patient.visit.service.IllnessRecordService")
public class IllnessRecordServiceImpl extends SpringJdbcSimpleEntityService implements IllnessRecordService {

	/*检查本随访周期内该患者是否填写了病情变化 */
	@Override
	public Boolean select4PeriodCheck(String customerUuid, Date createTime){
		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(createTime)) return null;
		List<Map<String, Object>> lst = dao.query(
				"select 1 from illness_record where customerUuid=? and createTime>=? LIMIT 0,1",
				new Object[]{StringUtils.trimToEmpty(customerUuid), createTime},
				null, getQueryCache());
		return ObjectUtils.isEmpty(lst);
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "IllnessRecordService";}
}