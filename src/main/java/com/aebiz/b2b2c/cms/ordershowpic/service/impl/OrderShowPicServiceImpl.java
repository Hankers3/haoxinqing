package com.aebiz.b2b2c.cms.ordershowpic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.ordershowpic.dao.OrderShowPicDAO;
import com.aebiz.b2b2c.cms.ordershowpic.service.OrderShowPicService;
import com.aebiz.b2b2c.cms.ordershowpic.vo.OrderShowPicModel;
import com.aebiz.b2b2c.cms.ordershowpic.vo.OrderShowPicQueryModel;

@Service
@Transactional
public class OrderShowPicServiceImpl extends
		BaseServiceImpl<OrderShowPicModel, OrderShowPicQueryModel> implements
		OrderShowPicService {
	private OrderShowPicDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(OrderShowPicDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderShowPicModel m) {
		m.setUuid(us.getNextUuid("OrderShowPic", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(OrderShowPicModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(OrderShowPicModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过晒单的编号获得晒单所有的图片
	 * 
	 * @param orderShowUuid
	 * @return
	 */
	public List<OrderShowPicModel> getOrderShowPicModelByShowUuid(
			String orderShowUuid) {
		return myDao.getOrderShowPicModelByShowUuid(orderShowUuid);
	}

}