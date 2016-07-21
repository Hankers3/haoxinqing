package com.aebiz.b2b2c.finance.withdrawapply.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.exception.AppException;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

import com.aebiz.b2b2c.finance.withdrawapply.dao.WithdrawApplyDAO;
import com.aebiz.b2b2c.finance.withdrawapply.service.WithdrawApplyService;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;

@Service
@Transactional
public class WithdrawApplyServiceImpl extends
		BaseServiceImpl<WithdrawApplyModel, WithdrawApplyQueryModel> implements
		WithdrawApplyService {
	private WithdrawApplyDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	private ServicestaffService servicestaffService;

	@Autowired
	public void setMyDao(WithdrawApplyDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(WithdrawApplyModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getCustomerLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(WithdrawApplyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getCustomerLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 平台后台驳回家政员的提现申请<br />
	 * 
	 * 1.将申请单状态修改为失败状态<br />
	 * 2.解冻申请时冻结的金额<br />
	 * 3.更新会员的帐户金额（将之前冻结时扣减的可用金额加回去）<br />
	 * 
	 * @param withdrawApplyUuid
	 *            提现的id
	 * @param rejectReason
	 *            驳回的原因
	 */
	public void reject(String withdrawApplyUuid, String rejectReason) {

		// 根据编号获得提现申请对象
		WithdrawApplyModel mo = myDao.getByUuid(withdrawApplyUuid);
		if (mo == null) {
			throw new AppException("mo.no.existed");
		}

		ServicestaffModel servicestaffModel = null;

		if (mo.getUserUuid() != null) {
			// 家政员的提现处理
			servicestaffModel = servicestaffService.getByUuid(mo.getUserUuid());
			if (servicestaffModel == null) {
				throw new AppException("financeaccount.no.existed");
			}

		}

		mo.setSuccessTime(DateFormatHelper.getNowTimeStr());
		mo.setState(WithdrawApplyModel.APPLY_STATE_FAIL);
		if (!StringUtil.isEmpty(rejectReason)) {
			mo.setFailReason(rejectReason);
		} else {
			mo.setFailReason("");
		}

		this.update(mo);
	}

	/**
	 * 平台后台同意家政员提现申请<br/>
	 * 
	 * 1.将申请单状态修改为已转帐状态 2.账户余额减去申请提现金额
	 * 
	 * @param withdrawApplyUuid
	 *            提现申请的id
	 */
	public void transfer(String withdrawApplyUuid) {
		WithdrawApplyModel mo = myDao.getByUuid(withdrawApplyUuid);
		if (mo == null) {
			throw new AppException("mo.no.existed");
		}

		ServicestaffModel servicestaffModel = null;

		if (mo.getUserUuid() != null) {
			// 家政员的提现处理
			servicestaffModel = servicestaffService.getByUuid(mo.getUserUuid());
			if (servicestaffModel == null) {
				throw new AppException("financeaccount.no.existed");
			}

		}

		mo.setSuccessTime(DateFormatHelper.getNowTimeStr());
		mo.setState(WithdrawApplyModel.APPLY_STATE_SUCCESS);
		if (servicestaffModel != null) {
			servicestaffModel.setAccountAmount(servicestaffModel
					.getAccountAmount() - mo.getApplyMoney());
			servicestaffService.update(servicestaffModel);
		}
		this.update(mo);
	}

	/**
	 * 查询提现记录
	 */
	public List<WithdrawApplyModel> getApplyList(String customerUuid,
			String time, int pageNo, int pageCount) {
		return myDao.getApplyList(customerUuid, time, pageNo, pageCount);
	}

	/**
	 * 通过医生查询
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<WithdrawApplyModel> getByDoctorUuid(String doctorUuid) {
		return myDao.getByDoctorUuid(doctorUuid);
	}
}