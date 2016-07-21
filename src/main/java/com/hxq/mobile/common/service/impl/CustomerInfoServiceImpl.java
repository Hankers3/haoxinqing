package com.hxq.mobile.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Service("com.hxq.mobile.common.service.CustomerInfoService")
public class CustomerInfoServiceImpl extends SpringJdbcSimpleEntityService implements CustomerInfoService {

	private String TABLE_COLUMNS="uuid,delFlag,opeTime,oper,address,birthday,certCode,certType,"
			+ "customerUuid,realName,education,hobby,image,income,industry,marryState,nickName,province,"
			+ "city,region,sex,zipCode,alternativeName,alternativePhone,age,introduceName,diseaseTime,"
			+ "illnessDescription,firstDiagnosis,ifStart,seizureTimes,height,weight,nearlyDrugs";

	@Override
	public CustomerInfo selectByCustomerUuid(String customerUuid) {
		if(ObjectUtils.isEmpty(customerUuid)) return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("SELECT ").append(TABLE_COLUMNS)
		.append(" FROM customer_info WHERE customerUuid=? ORDER BY opeTime DESC LIMIT 0,1");
		List<Map<String, Object>> lst = dao.query(sbf.toString(),
				new Object[]{StringUtils.trimToEmpty(customerUuid)}, null, getQueryCache());
		return ObjectUtils.isEmpty(lst) ? null : SimpleBean2DBHelper.map2Bean(lst.get(0), CustomerInfo.class);
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return null;}
}
