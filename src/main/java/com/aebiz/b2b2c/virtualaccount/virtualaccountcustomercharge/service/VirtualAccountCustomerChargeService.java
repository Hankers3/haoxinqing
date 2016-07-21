package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeQueryModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;

/**
 * 会员提交充值申请,去调用银行卡支付,支付成功后把更改账户的余额<br>
 * 
 * 会员中心可以查看充值的记录
 *
 * @author tangyunkai
 *
 * @date 2014年12月29日 下午8:34:52
 *
 */
public interface VirtualAccountCustomerChargeService
		extends
			BaseService<VirtualAccountCustomerChargeModel, VirtualAccountCustomerChargeQueryModel> {

	/**
	 * 重置成功后,需要把重置状态置为成功,同时需要添加一条账户日志记录,操作类型为收入
	 * @param m 
	 * void
	 */
	public void UpdateChargeStateToSuccess(VirtualAccountCustomerChargeModel m);
	
	/**
	 * 充值失败后需要把重置状态修改为失败,不需要添加日志
	 * @param m 
	 * void
	 */
	public void UpdateChargeStateToFail(VirtualAccountCustomerChargeModel m);

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
	 * 查询所有充值记录的总数量
	 * @param qm
	 * @return
	 */
	public int getAllChargeCount(VirtualAccountCustomerChargeQueryModel qm);
	
	/**
	 * 根据会员id查询该会员的所有充值记录
	 * @param customerUuid
	 * @return
	 */
	public List<VirtualAccountCustomerChargeModel> searchAllCharge(String customerUuid);
	
	
	/**
	 * 根据订单id获取对象
	 * @param orderUuid
	 * @return
	 */
	public VirtualAccountCustomerChargeModel getVirtualAccountCustomerChargeModelByOrderUuid(String orderUuid);

	/**
	 * 创建生成支付流水号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createChargeNo() ;
}
