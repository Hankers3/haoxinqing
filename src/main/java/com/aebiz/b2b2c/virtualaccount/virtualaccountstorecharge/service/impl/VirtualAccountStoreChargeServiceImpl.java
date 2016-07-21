package com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.storefinanceaccount.service.StoreFinanceAccountInteractive;
import com.aebiz.b2b2c.finance.storefinanceaccount.vo.StoreFinanceAccountModel;
import com.aebiz.b2b2c.giftcard.util.PubRandomUtil;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.dao.VirtualAccountStoreChargeDAO;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.service.VirtualAccountStoreChargeService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.vo.VirtualAccountStoreChargeModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.vo.VirtualAccountStoreChargeQueryModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.service.VirtualAccountStoreLogService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.vo.VirtualAccountStoreLogModel;

@Service
@Transactional
public class VirtualAccountStoreChargeServiceImpl
		extends
		BaseServiceImpl<VirtualAccountStoreChargeModel, VirtualAccountStoreChargeQueryModel>
		implements VirtualAccountStoreChargeService {
	private VirtualAccountStoreChargeDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(VirtualAccountStoreChargeDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	// 注入商户账户信息对外接口
	@Autowired
	private StoreFinanceAccountInteractive accountInteractive;

	// 注入商户账户日志service
	@Autowired
	private VirtualAccountStoreLogService virtualAccountStoreLogService;

	@Override
	public String create(VirtualAccountStoreChargeModel m) {
		m.setUuid(us.getNextUuid());
		// 充值单号
		m.setChargeNo(PubRandomUtil.getNumr(12));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getStoreLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());

		// 商户的uuid
		m.setStoreUuid(LoginUserHelper.getStoreLoginUserUuid());
		// 待充值
		m.setChargeState(VirtualAccountStoreChargeModel.UNDER_PAY);

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(VirtualAccountStoreChargeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(VirtualAccountStoreChargeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 重置成功后,需要把重置状态置为成功,同时需要添加一条账户日志记录,操作类型为收入,同时需要更新会员的的账户余额
	 * 
	 * @param m
	 *            void
	 */
	public void UpdateChargeStateToSuccess(VirtualAccountStoreChargeModel m) {
		VirtualAccountStoreChargeModel model = this.getByUuid(m.getUuid());
		model.setChargeState(VirtualAccountStoreChargeModel.PAY_SUCCESS);
		this.update(model);

		// 修改商户账户的的余额
		StoreFinanceAccountModel accountModel = accountInteractive
				.getStoreFinanceAccountModelByAccount(model.getStoreUuid());
		accountInteractive.update(accountModel);

		// 添加商户虚拟账户日志
		VirtualAccountStoreLogModel accountStoreLogModel = new VirtualAccountStoreLogModel();
		// 操作状态为收入
		accountStoreLogModel.setOperType(VirtualAccountStoreLogModel.IN);
		accountStoreLogModel.setStoreUuid(model.getStoreUuid());
		accountStoreLogModel.setOperAmount(model.getOperAmount());
		accountStoreLogModel.setDescription(model.getNote());
		accountStoreLogModel.setDocumentNo(m.getChargeNo());
		accountStoreLogModel.setFrozenState(VirtualAccountStoreLogModel.NORMAL);
		accountStoreLogModel.setRechargeType(VirtualAccountStoreLogModel.ACCOUNT);
		// 最新余额
		accountStoreLogModel.setNowBalance(accountModel.getVirtualMoney());
		virtualAccountStoreLogService.create(accountStoreLogModel);
	}

	/**
	 * 充值失败后需要把重置状态修改为失败,不需要添加日志
	 * 
	 * @param m
	 *            void
	 */
	public void UpdateChargeStateToFail(VirtualAccountStoreChargeModel m) {
		VirtualAccountStoreChargeModel model = this.getByUuid(m.getUuid());
		model.setChargeState(VirtualAccountStoreChargeModel.PAY_FAIL);

		this.update(model);
	}
}