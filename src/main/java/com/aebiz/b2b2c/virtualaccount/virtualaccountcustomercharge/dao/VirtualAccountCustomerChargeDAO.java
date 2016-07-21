package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeQueryModel;

public interface VirtualAccountCustomerChargeDAO
		extends
			BaseDAO<VirtualAccountCustomerChargeModel, VirtualAccountCustomerChargeQueryModel> {

	/**
	 * 查询充值记录的总数量<br>
	 * 
	 * 如果state为空,则查询的是所有的充值状态的记录
	 * @param customerUuid
	 * @param state
	 * @return 
	 * int
	 */
	public int getChargeCount(String customerUuid, String state);
	
	/**
	 * 查询充值记录列表,带分页的<br>
	 * 
	 * 如果state为空,则查询的是所有的充值状态的记录
	 * @param customerUuid
	 * @param state
	 * @param fromNum
	 * @param pageShow
	 * @return 
	 * List<VirtualAccountCustomerChargeModel>
	 */
	public List<VirtualAccountCustomerChargeModel> searchCharge(
			String customerUuid, String state, int fromNum, int pageShow);
	
	/**
	 * 查询充值记录列表,带分页的<br>
	 * 
	 * 如果state为空,则查询的是所有的充值状态的记录
	 * @param customerUuid
	 * @param state
	 * @param fromNum
	 * @param pageShow
	 * @return 
	 * List<VirtualAccountCustomerChargeModel>
	 */
	public List<String> searchChargeUuids(String customerUuid, String state,
			int fromNum, int pageShow);
	
	/**
	 * 查询所有充值记录的总数量
	 * @param qm
	 * @return
	 */
	public int getAllChargeCount(VirtualAccountCustomerChargeQueryModel qm);
	
	/**
	 * 查询某一个会员的所有充值记录
	 * @param customerUuid
	 * @return
	 */
	public List<VirtualAccountCustomerChargeModel> searchAllCharge(String customerUuid);
	
	/**
	 * 查询某一个会员的所有充值记录Uuids
	 * @param customerUuid
	 * @return
	 */
	public List<String> searchUuidsAllCharge(String customerUuid);
	
	
	/**
	 * 根据订单id获取对象
	 * @param orderUuid
	 * @return
	 */
	public VirtualAccountCustomerChargeModel getVirtualAccountCustomerChargeModelByOrderUuid(String orderUuid);
	
}