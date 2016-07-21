package com.hxq.mobile.patient.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.patient.common.service.ConsultRecordService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.util.MathUtils;

@Service("com.hxq.mobile.patient.common.service.ConsultRecordService")
public class ConsultRecordServiceImpl extends SpringJdbcSimpleEntityService implements ConsultRecordService {

	/**
	 * 获取医生的咨询总量
	 */
	@Override
	public int selectConsultNumBydoctorId(String doctorId) {
		StringBuilder sbf = new StringBuilder(1000);
		sbf.append("select count(distinct o.uuid) from consult_record as o ")
				.append("left join service_staff  as s on o.doctorUuid = s.uuid ").append("where   o.doctorUuid =? ");
		Integer intReturn = dao.queryForObject(sbf.toString(), new Object[] { doctorId }, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}

	/**
	 * 获取医生的预约加号咨询总量
	 * 
	 * @param doctorId
	 * @return
	 */
	@Override
	public int selectAllOrderNumByDoctorId(String doctorId) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select count(distinct o.uuid) from consult_record o")
				.append(" LEFT JOIN service_staff  as s on o.doctorUuid = s.uuid").append(" where  o.doctorUuid =?");
		Integer intReturn = dao.queryForObject(sbf.toString(), new Object[] { doctorId }, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}

	/**
	 * 通过患者id得到ConsultRecordModel的List
	 * 
	 * @param uuid
	 * @param type
	 *            咨询类型 0在线咨询即图文咨询 1电话咨询 2预约加号
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectByCustomerUuid(String uuid, String type) {
		StringBuffer sbf = new StringBuffer(1000);

		sbf.append("SELECT crm.doctorUuid,crm.uuid,crm.createTime,crm.seeDoctorTime,crm.ifread FROM ( ");

		sbf.append(" SELECT   crm.doctorUuid AS doctorUuid,crm.uuid AS UUID,crm.createTime AS createTime,");
		sbf.append(" crm.seeDoctorTime AS seeDoctorTime, MIN(crm.ifread) AS ifread ");

		sbf.append(" from consult_record as crm").append(" LEFT JOIN service_staff as s on crm.doctorUuid = s.uuid")
				.append(" where crm.customerUuid=?").append(" group by crm.doctorUuid  ");
		sbf.append(" ) crm order by crm.ifread asc,crm.createTime desc");
		List<Map<String, Object>> lstReturn = dao.query(sbf.toString(), new Object[] { uuid }, null, getQueryCache());
		return lstReturn;
	}

	
    /**
	 * 通过患者id得到ConsultRecordModel的List
	 * @param uuid
	 * @param type 咨询类型  0在线咨询即图文咨询  1电话咨询  2预约加号 
	 * @return
	 */
	public List<Map<String, Object>> selectConsultRecordByUuidAndConsultType(String uuid, String type){
		StringBuffer sbf = new StringBuffer(1000);

		sbf.append(" SELECT UUID,content ,doctorUuid ,createTime FROM  consult_record  crd WHERE  ifread='0' ");
		if(type.equals("1")){
			sbf.append(" AND  customerUuid =?    ");
		}else if(type.equals("2")){
			sbf.append(" AND  doctorUuid =?    ");
		}
		
		sbf.append(" AND  crd.consultType=?   ");
		
		sbf.append("  ORDER BY createTime DESC LIMIT 0,1");
		List<Map<String, Object>> lstReturn = dao.query(sbf.toString(), new Object[] { uuid,type }, null, getQueryCache());
		return lstReturn;
	}
	
	
	public Object[] selectByCustomerUuidList(String customerUuid, Integer pageCount, Integer pageNo) {
		StringBuffer sbf = new StringBuffer(1000);
		List<Object> args = new ArrayList<Object>();
		args.add(customerUuid);

		sbf.append(
				" from consult_record as a LEFT JOIN service_staff as b on a.doctorUuid=b.uuid where iquestion='1' and customerUuid=?");
		String strWhere = sbf.toString();

		sbf.delete(0, sbf.length());
		String strTotal = sbf.append(" select count(1)").append(strWhere).toString();

		sbf.delete(0, sbf.length());
		String strQuery = sbf.append(" select a.uuid,a.doctorUuid,a.content,a.createTime ,b.realName").append(strWhere)
				.append(" order by a.createTime desc").toString();

		Object[] params = args.isEmpty() ? null : args.toArray();

		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), MathUtils.toInt(pageNo, 1),
				MathUtils.toInt(pageCount, 20));
		List<Map<String, Object>> lst = page.queryByPage(strTotal, strQuery, params, null);
		return new Object[] { lst, page.getPageNo(), page.getTotalPage(), page.getTotalRows() };
	}

	/**
	 * 根据医生id与患者id查询数据
	 */
	public List<Map<String, Object>> selectByCustomerUuidAndDoctorUuid(String customerUuid, String doctorUuid) {
		StringBuffer sbf = new StringBuffer(1000);
		List<Object> args = new ArrayList<Object>();
		args.add(customerUuid);
		args.add(doctorUuid);

		sbf.append(" select a.uuid,a.doctorUuid,a.content,a.createTime,a.customerUuid,a.doctorUuid ");
		sbf.append(" from consult_record as a  where iquestion='1' and customerUuid=? and doctorUuid=? ");
		sbf.append(" order by a.createTime desc").toString();
		Object[] params = args.isEmpty() ? null : args.toArray();
		List<Map<String, Object>> lstReturn = dao.query(sbf.toString(), params, null, getQueryCache());
		return lstReturn;

	}

	public int selectUnread(String customerUuid) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select count(uuid) from consult_record where customerUuid=? and ifread='0' ");
		Integer intReturn = dao.queryForObject(sbf.toString(), new Object[] { customerUuid }, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}

	@Override
	protected String getCacheName() {
		return "h1";
	}

	@Override
	protected String getQueryCacheName() {
		return "ConsultRecordService";
	}

}
