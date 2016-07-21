package com.aebiz.b2b2c.finance.orderreturnmoneymain.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.vo.OrderReturnMoneyMainModel;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.vo.OrderReturnMoneyMainQueryModel;

public interface OrderReturnMoneyMainService extends
		BaseService<OrderReturnMoneyMainModel, OrderReturnMoneyMainQueryModel> {

	/**
	 * 
	 * 在申请退款或退货同意时，生成退货单，可调用此方法
	 * 
	 * @param orderMainUuid
	 * @param afterServiceUuid
	 * @param customerUuid
	 * @param applyTime
	 * @param backMoney
	 * @param reason
	 * @param state
	 * @param returnType
	 * @param description
	 * 
	 * @return
	 */
	public void createOrderReturnMoneyMainModel(String orderMainUuid,
			String afterServiceUuid, String personId, String personType,
			double backMoney, String reason, String state, String returnType,
			String description);

}
