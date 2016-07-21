package com.hxq.mobile.patient.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.entity.common.Concern;
import com.hxq.mobile.patient.common.service.ConcernService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.patient.common.service.ConcernService")
public class ConcernServiceImpl extends SpringJdbcSimpleEntityService implements ConcernService{

	//通过患者id得到患者关注医生表
	@Override
	public List<Map<String, Object>> selectByCustomerUuid(String customerUuid) {
		
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select a.uuid,a.opeTime,a.delFlag,a.customerUuid,a.doctorUuid,a.createTime,a.state from concern as a ")
		.append(" left join service_staff as s on a.doctorUuid =s.uuid ")
		.append(" where a.customerUuid= ? ORDER BY a.createTime DESC");
		return dao.query(sbf.toString(), new Object[]{customerUuid}, null, getQueryCache());
		
	}
	
	//查看用户是否关注该医生
	@Override
	public int selectConcernType(String doctorUuid, String customerUuid) {
		String hql = "select count(distinct o.uuid) from concern as o where o.doctorUuid=? and o.customerUuid=?";
		Integer intReturn =  dao.queryForObject(hql.toString(), new Object[]{doctorUuid,customerUuid}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;	
	}
	
	//获取关注该医生的粉丝数
	@Override
	public int selectConcernNumByDoctorId(String doctorId) {
		String hql = "select count(distinct uuid) from concern  where doctorUuid=? ";
		Integer intReturn =  dao.queryForObject(hql.toString(), new Object[]{doctorId}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}
	/*
	 * 通过医生id 和患者id 查询信息
	 */
	@Override
	public Concern selectByCustomerAndDoctorUuid(String customerUuid,String doctorUuid) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select uuid,oper,opeTime,delFlag,createTime,state from concern where customerUuid =? and doctorUuid=?");
		List<Map<String, Object>> lstReturn = dao.query(sbf.toString(), new Object[]{customerUuid,doctorUuid}, null, getCache());
		return ObjectUtils.isEmpty(lstReturn) ? null : SimpleBean2DBHelper.map2Bean(lstReturn.get(0), Concern.class);
	}
	
	@Override
	public Concern selectConcernByid(String doctorUuid) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select uuid,oper,opeTime,delFlag,createTime,state from concern where doctorUuid=?");
		List<Map<String, Object>> lstReturn = dao.query(sbf.toString(), new Object[]{doctorUuid}, null, getCache());
		return ObjectUtils.isEmpty(lstReturn) ? null : SimpleBean2DBHelper.map2Bean(lstReturn.get(0), Concern.class);
	}
	
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {return null;}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {	return "ConcernService";}

}
