package com.aebiz.b2b2c.finance.orderreturnmoneylog.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.orderreturnmoneylog.vo.OrderReturnMoneyLogModel;
import com.aebiz.b2b2c.finance.orderreturnmoneylog.vo.OrderReturnMoneyLogQueryModel;

public interface OrderReturnMoneyLogService extends
		BaseService<OrderReturnMoneyLogModel, OrderReturnMoneyLogQueryModel> {

	/**
	 * 新增退款单日志
	 * 
	 * 记录退款单的各种操作日志<br />
	 * 生成、退款成功、退款失败等信息<br />
	 * 
	 * @param returnMainUuid
	 * @param handleMan
	 * @param handManType
	 * @param result
	 * @param handleTime
	 * @param handleDesc
	 */
	public void createReturnMoneyLog(String returnMainUuid, String handleMan,
			String handManType, String result, String handleTime,
			String handleDesc);
}
