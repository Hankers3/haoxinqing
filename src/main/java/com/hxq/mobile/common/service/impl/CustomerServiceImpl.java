package com.hxq.mobile.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.common.service.CustomerService;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Service("com.hxq.mobile.common.service.CustomerService")
public class CustomerServiceImpl extends SpringJdbcSimpleEntityService implements CustomerService {

	private String TABLE_COLUMNS="uuid,delFlag,opeTime,oper,activeCode,activeState,authState,"
			+ "createTime,customerShopLevelUuid,customerName,email,frozenState,frozenType,"
			+ "lastWrongLoginTime,loginErrorTimes,mobile,customerId,qq,lastLoginTime,"
			+ "lastLoginTimef,tipUser,categoryUuid,groupUuid,accountAmount,regState";

	@Override
	public Customer selectCustomerByMobile(String mobile) throws Exception {
		if(ObjectUtils.isEmpty(mobile)) return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("SELECT ").append(TABLE_COLUMNS).append(" FROM customer WHERE mobile=? LIMIT 0,1");
		List<Map<String, Object>> lst = dao.query(sbf.toString(),
				new Object[]{StringUtils.trimToEmpty(mobile)}, null, getQueryCache());
		return ObjectUtils.isEmpty(lst) ? null : SimpleBean2DBHelper.map2Bean(lst.get(0), Customer.class);
	}

	/**
	 * 检查手机号和密码
	 * 
	 * @param mobile
	 * @param password
	 */
	@Override
	public Customer select4CheckMobileAndPassword(String mobile, String password) {
		if (ObjectUtils.isEmpty(mobile) || ObjectUtils.isEmpty(password)) return null;
		List<Map<String, Object>> lst = dao.query("select * from customer where mobile=? and password=?",
				new Object[] { mobile, password }, null, getQueryCache());
		return ObjectUtils.isEmpty(lst) ? null : SimpleBean2DBHelper.map2Bean(lst.get(0), Customer.class);
	}
	/**
	 * 通过id查询患者用户名
	 */
	public String selectCustomerName(String uuid){
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select customerName from customer where uuid=? LIMIT 0,1");
		List<Map<String, Object>> lstReturn = dao.query(sbf.toString(), new Object[]{uuid}, null, getCache());
		return (String) (ObjectUtils.isEmpty(lstReturn) ? null : lstReturn.get(0).get("uuid"));
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
