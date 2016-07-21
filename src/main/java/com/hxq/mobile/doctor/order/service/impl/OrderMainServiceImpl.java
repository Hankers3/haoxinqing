package com.hxq.mobile.doctor.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.order.service.OrderMainService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.doctor.order.service.OrderMainService")
public class OrderMainServiceImpl extends SpringJdbcSimpleEntityService	implements OrderMainService {

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "OrderMainService";}
	
	/*获取医生电话咨询的数量*/
	@Override
	public int getTotalTelNumByDoctorId(String doctorId) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select count(distinct uuid) from order_main where orderType='1' and doctorUuid=? ");
		Integer intReturn =  dao.queryForObject(sbf.toString(), new Object[]{doctorId}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}

	/** 根据会员ID获取已完成订单信息
	 * @param customerUuid
	 *            会员ID
	 * @param pageCount
	 *            分页
	 * @param pageNo
	 *            页码 
	 */
	@Override
	public List<Map<String, Object>> getOrderList(String orderType, String customerUuid) {
		
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select a.doctorUuid,a.orderType,a.uuid,a.orderTime,a.packageUuid ")
		.append("from order_main as a LEFT JOIN service_staff as s on a.doctorUuid = s.uuid ")
		.append(" where a.state='6' and a.orderType=? and a.customerUuid=? order by a.orderTime desc");
		List<Map<String, Object>> listReturn = dao.query(sbf.toString(), new Object[]{orderType,customerUuid}, null, getCache());
		return ObjectUtils.isEmpty(listReturn)?null:listReturn;
//		List<Object> args = new ArrayList<Object>();
//		args.add(orderType);
//		args.add(customerUuid);
//
//		sbf.append(" from order_main as a LEFT JOIN service_staff as s on a.doctorUuid = s.uuid")
//		.append(" where a.state='6' and a.orderType=? and a.customerUuid=?");
//		String strWhere = sbf.toString();
//
//		sbf.delete(0, sbf.length());
//		String strTotal = sbf.append("select count(1)").append(strWhere).toString();
//
//		sbf.delete(0, sbf.length());
//		String strQuery=sbf.append("select a.doctorUuid,a.orderType,a.uuid,a.orderTime,a.packageUuid")
//				.append(strWhere).append(" order by a.orderTime desc").toString();
//
//		Object[] params = args.isEmpty() ? null : args.toArray();
//		PaginationHelper page = new PaginationHelper(dao, getCache(), pageNo, pageCount);
//		return page.queryByPage(strTotal, strQuery, params, null);
	}

	@Override
	public Map<String, Object> selectIncomeModel(String doctorUuid, String string) throws Exception {
		String sql = "select sum(routPrice) income from order_routing where serviceStaffUuid = ?";
		List<String> staff = new ArrayList<String>();
		staff.add(doctorUuid);
		Object[] staffparams = staff.isEmpty() ? null : staff.toArray();
		List<Map<String, Object>> staffReturn = dao.query(sql, staffparams, null, getCache());
		return (ObjectUtils.isEmpty(staffReturn) ? null : staffReturn.get(0));
	}
}
