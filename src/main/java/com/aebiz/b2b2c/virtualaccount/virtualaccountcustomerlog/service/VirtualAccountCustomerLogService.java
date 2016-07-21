package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogQueryModel;

public interface VirtualAccountCustomerLogService
		extends
		BaseService<VirtualAccountCustomerLogModel, VirtualAccountCustomerLogQueryModel> {

	/**
	 * 查询会员收支纪录的总数<br>
	 * 
	 * @param customerUuid
	 * @param operType
	 *            为空时,查全部 0:查收入的 1:查支出的
	 * @return int
	 */
	public int getCustomerAccountLogCount(String customerUuid, String operType);

	/**
	 * 根据订单id获取对象
	 * 
	 * @param orderUuid
	 * @return
	 */
	public VirtualAccountCustomerLogModel getVirtualAccountCustomerLogModelByOrderUuid(
			String orderUuid);

	/**
	 * 根据消费记录的Uuid查询患者的uuid
	 */
	public String getCustomerUuidByLogsUuid(String uuid);
	/**
	 * 根据患者的id获取患者的消费记录日志列表
	 * @param customerUuid
	 * @return
	 */
	public List<VirtualAccountCustomerLogModel> getVirtualAccountCustomerLogListByCustomerUuid(String customerUuid);

}
