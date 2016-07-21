package com.aebiz.b2b2c.finance.orderreturnmoneymain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.orderreturnmoneylog.service.OrderReturnMoneyLogService;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.dao.OrderReturnMoneyMainDAO;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.service.OrderReturnMoneyMainService;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.vo.OrderReturnMoneyMainModel;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.vo.OrderReturnMoneyMainQueryModel;

@Service
@Transactional
public class OrderReturnMoneyMainServiceImpl
		extends
		BaseServiceImpl<OrderReturnMoneyMainModel, OrderReturnMoneyMainQueryModel>
		implements OrderReturnMoneyMainService {
	private OrderReturnMoneyMainDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	private OrderReturnMoneyLogService orderReturnMoneyLogService;

	@Autowired
	public void setMyDao(OrderReturnMoneyMainDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderReturnMoneyMainModel m) {

		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(OrderReturnMoneyMainModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(OrderReturnMoneyMainModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

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
			String description) {

		String uuid = us.getNextUuid();
		// 1.生成退款单
		OrderReturnMoneyMainModel mainModel = new OrderReturnMoneyMainModel();
		mainModel.setUuid(us.getNextUuid());
		mainModel.setOrderMainUuid(orderMainUuid);
		mainModel.setAfterServiceUuid(afterServiceUuid);
		mainModel.setCustomerUuid(personId);
		mainModel.setApplyTime(DateFormatHelper.getNowTimeStr());
		mainModel.setBackMoney(backMoney);
		mainModel.setReason(reason);
		mainModel.setState(state);
		mainModel.setReturnType(returnType);
		mainModel.setDescription(description);
		mainModel.setPersonType(personType);
		this.create(mainModel);

		// 2.生成退款单日志
		orderReturnMoneyLogService.createReturnMoneyLog(afterServiceUuid,
				personId, personType, state, DateFormatHelper.getNowTimeStr(),
				description);
	}
}