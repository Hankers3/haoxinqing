package com.aebiz.b2b2c.finance.orderreturnmoneylog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.orderreturnmoneylog.dao.OrderReturnMoneyLogDAO;
import com.aebiz.b2b2c.finance.orderreturnmoneylog.service.OrderReturnMoneyLogService;
import com.aebiz.b2b2c.finance.orderreturnmoneylog.vo.OrderReturnMoneyLogModel;
import com.aebiz.b2b2c.finance.orderreturnmoneylog.vo.OrderReturnMoneyLogQueryModel;

@Service
@Transactional
public class OrderReturnMoneyLogServiceImpl
		extends
		BaseServiceImpl<OrderReturnMoneyLogModel, OrderReturnMoneyLogQueryModel>
		implements OrderReturnMoneyLogService {
	private OrderReturnMoneyLogDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(OrderReturnMoneyLogDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderReturnMoneyLogModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(OrderReturnMoneyLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(OrderReturnMoneyLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

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
			String handleDesc) {

		OrderReturnMoneyLogModel logModel = new OrderReturnMoneyLogModel();
		logModel.setReturnMoneyMainUuid(returnMainUuid);
		logModel.setPersonUuid(handleMan);
		logModel.setOperType(handManType);
		logModel.setResult(result);
		logModel.setDealTime(DateFormatHelper.getNowTimeStr());
		logModel.setDescription(handleDesc);

		this.create(logModel);
	}
}