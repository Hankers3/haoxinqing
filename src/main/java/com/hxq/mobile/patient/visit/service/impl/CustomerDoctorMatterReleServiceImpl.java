package com.hxq.mobile.patient.visit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.entity.common.CustomerDoctorMatterRele;
import com.hxq.mobile.patient.visit.service.CustomerDoctorMatterReleService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.patient.visit.service.CustomerDoctorMatterReleService")
public class CustomerDoctorMatterReleServiceImpl  extends SpringJdbcSimpleEntityService  implements CustomerDoctorMatterReleService {

/*	
	 * 根据医生id和患者id查看  医患是否同时存在
	 
	@Override
	public CustomerDoctorRele selectCustomerDoctorRele(String customerUuid, String doctorUuid) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("SELECT * from customer_doctor_rele where doctorUuid=? and customerUuid=? ");
		List<Map<String,Object>> lstReturn = dao.query(sbf.toString(), new Object[]{doctorUuid, customerUuid}, null,getCache());
		return ObjectUtils.isEmpty(lstReturn) ? null : SimpleBean2DBHelper.map2Bean(lstReturn.get(0), CustomerDoctorRele.class);
	}

	*//**
	 * 根据医生id与分组id得到医患关联数据
	 * @param doctorUuid
	 * @param groupUuid
	 * @return
	 *//*
	public List<CustomerDoctorRele> selectByDoctorUuidAndGroupUuid(String doctorUuid,String groupUuid){
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("SELECT uuid,delFlag,opeTime,customerUuid,createTime from customer_doctor_rele where doctorUuid=? and groupUuid=? ");
		List<Map<String,Object>> lstReturn = dao.query(sbf.toString(), new Object[]{doctorUuid, groupUuid}, null,getCache());
		return ObjectUtils.isEmpty(lstReturn) ? null : SimpleBean2DBHelper.mapList2BeanList(lstReturn, CustomerDoctorRele.class);
	}

	@Override
	public String selectDoctorid(String uuid) {
		if(ObjectUtils.isEmpty(uuid)) return null;
		List<Map<String, Object>> lstReturn = dao.query(
				"select doctorUuid from customer_doctor_rele where customerUuid = ? and delFlag = '1' LIMIT 0,1",
				new String[]{uuid}, null, getQueryCache());
		return (String) (ObjectUtils.isEmpty(lstReturn) ? null : lstReturn.get(0).get("uuid"));
	}*/
	/*
	 * 通过患者id 查询医生id和创建时间 
	 * delFlag='1' and groupUuid>='0'
	 * groupUuid：分组编号：0默认是（未）分组，-1：是临时分组
	 */
	@Override
	public List<Map<String, Object>> selectByConsultRecordUuid(String consultRecordUuid){
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("SELECT UUID,STATUS,consultRecordUuid,doctorUuid,customerUuid FROM customer_doctor_matter_rele where consultRecordUuid=? ");
		List<Map<String, Object>> lst = dao.query(sbf.toString(), new Object[]{consultRecordUuid}, null, getCache());
		return ObjectUtils.isEmpty(lst)?null:lst;
	}

	
	/**
	 * 添加一条数据
	 * @param cust
	 */
	public int create(CustomerDoctorMatterRele cust){
		return 0;
		//dao.insert(cust, "customer_doctor_matter_rele");
	}
	
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {	return null;}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "CustomerDoctorReleService";}

}
