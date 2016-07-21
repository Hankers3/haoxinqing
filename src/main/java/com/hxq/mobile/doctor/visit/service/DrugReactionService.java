package com.hxq.mobile.doctor.visit.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.http.RequestUtil;

/**
 * 药物不良反应
 *
 */
@Service("com.hxq.mobile.doctor.visit.service.DrugReactionService")
public class DrugReactionService extends SpringJdbcSimpleEntityService {

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		if(RequestUtil.isEmpty(form,"visitRecordUuid")) return null;
		return dao.query(
			"select occurrenceTime,dosageTime,frequency,impact from drug_reaction where visitRecordUuid=?",
			new Object[]{RequestUtil.getFormValue(form, "visitRecordUuid")}, null, getCache());
	}

	@Override
	protected String getCacheName() {return "h1";}

	@Override
	protected String getQueryCacheName() {
		// TODO Auto-generated method stub
		return null;
	}
}
