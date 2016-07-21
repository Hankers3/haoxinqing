package com.hxq.mobile.doctor.order.service;

import java.util.List;
import java.util.Map;

import com.hxq.mobile.util.repository.SimpleEntityService;

public interface OrderMainService extends SimpleEntityService {

	/**
	 * 获取电话咨询的数量
	 * 
	 * @param doctorId
	 * @return
	 */
	public int getTotalTelNumByDoctorId(String doctorId);
	
	/**
	 * 根据会员ID获取已完成订单信息
	 * 
	 * @param customerUuid
	 *            会员ID
	 * @param pageCount
	 *            分页
	 * @param pageNo
	 *            页码
	 * @return
	 */
	public List<Map<String, Object>> getOrderList(String orderType,String customerUuid);

	public Map<String, Object> selectIncomeModel(String doctorUuid, String string) throws Exception;
}
