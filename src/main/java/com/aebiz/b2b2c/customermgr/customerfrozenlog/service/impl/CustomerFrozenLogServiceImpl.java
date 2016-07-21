package com.aebiz.b2b2c.customermgr.customerfrozenlog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.dao.CustomerFrozenLogDAO;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.service.CustomerFrozenLogService;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogModel;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogQueryModel;

@Service
@Transactional
public class CustomerFrozenLogServiceImpl extends
		BaseServiceImpl<CustomerFrozenLogModel, CustomerFrozenLogQueryModel>
		implements CustomerFrozenLogService {
	private CustomerFrozenLogDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(CustomerFrozenLogDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerFrozenLogModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerFrozenLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CustomerFrozenLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 添加冻结日志记录
	 */
	public void addFrozenLog(String customerUuid, String frozenType,
			String note, String oper) {
		CustomerFrozenLogModel customerFrozenLogModel = new CustomerFrozenLogModel();

		customerFrozenLogModel.setUuid(us.getNextUuid());
		customerFrozenLogModel.setOper(oper);
		customerFrozenLogModel.setOpeTime(DateFormatHelper.getNowTimeStr());
		customerFrozenLogModel.setDelFlag(BaseModel.DEL_FLAG_VALID);
		customerFrozenLogModel.setCustomerUuid(customerUuid);
		customerFrozenLogModel.setFrozenType(frozenType);
		customerFrozenLogModel.setNote(note);

		/* 操作类型：1表示冻结，0表示解冻 */
		customerFrozenLogModel
				.setOperType(CustomerFrozenLogModel.FROZEN_OPER_FREZON);
		super.create(customerFrozenLogModel);
	}

	/**
	 * 添加解冻日志记录
	 */
	public void addUnFrozenLog(String customerUuid, String note, String oper) {
		CustomerFrozenLogModel customerFrozenLogModel = new CustomerFrozenLogModel();

		customerFrozenLogModel.setUuid(us.getNextUuid());
		customerFrozenLogModel.setOper(oper);
		customerFrozenLogModel.setOpeTime(DateFormatHelper.getNowTimeStr());
		customerFrozenLogModel.setDelFlag(BaseModel.DEL_FLAG_VALID);
		customerFrozenLogModel.setCustomerUuid(customerUuid);

		// 解冻操作暂无类型，解冻原因放到备注中保存
		customerFrozenLogModel.setFrozenType("");
		customerFrozenLogModel.setNote(note);
		/* 操作类型：1表示冻结，0表示解冻 */
		customerFrozenLogModel
				.setOperType(CustomerFrozenLogModel.FROZEN_OPER_UNFREZON);
		super.create(customerFrozenLogModel);
	}

	/**
	 * 根据会员编号获取会员最新冻结日志记录
	 */
	@Override
	public CustomerFrozenLogModel getFrozenLog(String customerUuid) {
		return this.myDao.getFrozenLog(customerUuid);

	}

	/**
	 * 根据会员编号获取会员最新解冻日志记录
	 */
	@Override
	public CustomerFrozenLogModel getUnFrozenLog(String customerUuid) {
		return this.myDao.getUnFrozenLog(customerUuid);
	}
}