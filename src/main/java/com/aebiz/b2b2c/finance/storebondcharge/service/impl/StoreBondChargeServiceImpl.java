package com.aebiz.b2b2c.finance.storebondcharge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.storebondcharge.dao.StoreBondChargeDAO;
import com.aebiz.b2b2c.finance.storebondcharge.service.StoreBondChargeService;
import com.aebiz.b2b2c.finance.storebondcharge.vo.StoreBondChargeModel;
import com.aebiz.b2b2c.finance.storebondcharge.vo.StoreBondChargeQueryModel;
import com.aebiz.b2b2c.finance.storefinanceaccount.service.StoreFinanceAccountService;
import com.aebiz.b2b2c.finance.storefinanceaccount.vo.StoreFinanceAccountModel;
import com.aebiz.b2b2c.store.storeaccount.service.StoreAccountInteractive;

@Service
@Transactional
public class StoreBondChargeServiceImpl extends
		BaseServiceImpl<StoreBondChargeModel, StoreBondChargeQueryModel>
		implements StoreBondChargeService {
	private StoreBondChargeDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(StoreBondChargeDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	// 注入商户财务账户信息service
	@Autowired
	private StoreFinanceAccountService storeFinanceAccountService;

	@Autowired
	private StoreAccountInteractive storeAccountInteractive;

	@Override
	public String create(StoreBondChargeModel m) {
		m.setUuid(us.getNextUuid());

		// 保证金单据号
		m.setBondUuid(us.getNextUuid("BZJ_NO_", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);

		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(StoreBondChargeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(StoreBondChargeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 商户自己提交缴保证金单据<br>
	 * 
	 * 为了区别合同生成的和违约生成的,这里fromSource的值为3(商户普通充值)<br>
	 * 
	 * 如果商户有由于违约造成的扣除保证金单据,则必须提醒商户先支付扣除保证金单据后才可以再次提交新的交保证金单据
	 * 
	 * @param model
	 *            void
	 */
	public void createBondChargeByStore(StoreBondChargeModel model) {
		model.setOper(LoginUserHelper.getStoreLoginUserUuid());
		// 未支付状态
		model.setOperState(StoreBondChargeModel.UNDER_PAY);
		// 商户普通充值
		model.setFromSource(StoreBondChargeModel.NORMAL_CHARGE);
		// 增加保证金
		model.setOperType(StoreBondChargeModel.ADD);

		this.create(model);
	}

	/**
	 * 生成合同后,缴保证金单据<br>
	 * 
	 * 这里fromSource的值为 0(从合同生成的增加保证金)
	 * 
	 * @param model
	 *            void
	 */
	public void createBondChargeByContract(StoreBondChargeModel model) {
		model.setOper(LoginUserHelper.getStoreLoginUserUuid());
		// 未支付状态
		model.setOperState(StoreBondChargeModel.UNDER_PAY);
		// 合同生成保证金
		model.setFromSource(StoreBondChargeModel.FROM_CONTRACT);
		// 增加保证金
		model.setOperType(StoreBondChargeModel.ADD);

		this.create(model);
	}

	/**
	 * 违约处罚扣保证金时,缴保证金单据<br>
	 * 
	 * 这里fromSource的值为 1(违约生成保证金单据,扣减保证金)<br>
	 * 
	 * 这个时候需要把商户账户的已冻结保证金金额在原来的基础上加上这次扣的,同时把保证金余额减去扣的<br>
	 * 
	 * 当支付成功时把冻结金额的减去,<br>
	 * 
	 * 但是需要把保证金余额加上充值的金额,因为冻结的部分在支付成功后就相当于已经扣除了保证金,这样就能保证财务的的平衡
	 * 
	 * @param model
	 *            void
	 */
	public void createBondChargeByBreak(StoreBondChargeModel model) {
		model.setOper(LoginUserHelper.getStoreLoginUserUuid());
		// 未支付状态
		model.setOperState(StoreBondChargeModel.UNDER_PAY);
		// 违约扣除保证金
		model.setFromSource(StoreBondChargeModel.BREAK_MINUS);
		// 增加保证金
		model.setOperType(StoreBondChargeModel.MINUS);

		String accountUuid = LoginUserHelper.getStoreLoginUserUuid();


		// 商户的uuid
		model.setAccountUuid(accountUuid);
		this.create(model);

		StoreFinanceAccountModel financeAccount = storeFinanceAccountService
				.getStoreFinanceAccountModelByAccount(accountUuid);

		if (financeAccount != null) {
			// 获取扣减后的商户保证金
			float bondChargeMoney = 0;
			if (financeAccount.getBondMoney() >= model.getOperAmount()) {
				bondChargeMoney = financeAccount.getBondMoney()
						- model.getOperAmount();
			}
			// 获取新的冻结保证金
			float freezeBondMoney = financeAccount.getFreezeBondMoney()
					+ model.getOperAmount();

			// 调用商户扣减保证金余额和增加冻结保证金金额
			storeFinanceAccountService.updateBondChargeMoneyAndFreezeBondMoney(
					financeAccount, bondChargeMoney, freezeBondMoney);
		}
	}

	/**
	 * 添加完合同后生成的商户保证金单据,供商户去支付
	 * 
	 * @param accountUuid
	 *            商户uuid
	 * @param contract
	 *            合同uuid
	 * @param amount
	 *            保证金金额 void
	 */
	public void createBondChargeByContract(String accountUuid, String contract,
			float amount) {
		StoreBondChargeModel model = new StoreBondChargeModel();
		// 商户uuid
		model.setAccountUuid(accountUuid);
		// 合同编号
		model.setContractUuid(contract);
		// 备注
		model.setNote("合同编号:" + contract);
		// 保证金金额
		model.setOperAmount(amount);

		model.setPayType(StoreBondChargeModel.ONLINE);

		this.createBondChargeByContract(model);
	}

	/**
	 * 将保证金单据置成功
	 * 
	 * @param uuid
	 *            void
	 */
	public void updateBondChargeToSuccess(String uuid) {
		StoreBondChargeModel bondCharge = this.getByUuid(uuid);

		// 把操作状态改为成功
		bondCharge.setOperState(StoreBondChargeModel.PAY_SUCCESS);

		this.updateCell(bondCharge, "operState");
	}
}