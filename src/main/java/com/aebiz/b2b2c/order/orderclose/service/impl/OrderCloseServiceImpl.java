package com.aebiz.b2b2c.order.orderclose.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.order.orderclose.dao.OrderCloseDAO;
import com.aebiz.b2b2c.order.orderclose.service.OrderCloseService;
import com.aebiz.b2b2c.order.orderclose.vo.OrderCloseModel;
import com.aebiz.b2b2c.order.orderclose.vo.OrderCloseQueryModel;

@Service
@Transactional
public class OrderCloseServiceImpl extends
		BaseServiceImpl<OrderCloseModel, OrderCloseQueryModel> implements
		OrderCloseService {
	private OrderCloseDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(OrderCloseDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderCloseModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);

		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	
	public String createOrderClose(String orderId, String operMan, String reason) {
		OrderCloseModel m = new OrderCloseModel();
		m.setOrderUuid(orderId);
		m.setCancelReason(reason);
		m.setCancelMan(operMan);
		m.setOper(operMan);
		m.setCancelTime(DateFormatHelper.getNowTimeStr());

		return this.create(m);
	}

	/**
	 * 鏍规嵁璁㈠崟缂栧彿鏌ヨ璁㈠崟鐨勫叧闂秷鎭�
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderCloseModel getOrderCloseModelByOrderUuid(String orderUuid) {
		return myDao.getOrderCloseModelByOrderUuid(orderUuid);
	}
}