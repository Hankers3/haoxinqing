package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.order.accountkey.service.AccountKeyService;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.dao.VirtualAccountCustomerChargeDAO;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.service.VirtualAccountCustomerChargeService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo.VirtualAccountCustomerChargeQueryModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service.VirtualAccountCustomerLogService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;

/**
 * 会员提交充值申请,去调用银行卡支付,支付成功后把更改账户的余额<br>
 * 
 * 会员中心可以查看充值的记录
 * 
 * @author tangyunkai
 * 
 * @date 2014年12月29日 下午8:34:52
 * 
 */
@Service
@Transactional
public class VirtualAccountCustomerChargeServiceImpl
		extends
		BaseServiceImpl<VirtualAccountCustomerChargeModel, VirtualAccountCustomerChargeQueryModel>
		implements VirtualAccountCustomerChargeService {
	private VirtualAccountCustomerChargeDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(VirtualAccountCustomerChargeDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	// 注入会员虚拟账户日志的service
	@Autowired
	private VirtualAccountCustomerLogService accountCustomerLogService;

	// 注入会员账户的对外接口
	@Autowired
	private CustomerAccountInteractive accountInteractive;

	// 编号类型虚拟账户
	public static final String NOTYPE_VACM = "vacm";
	
	@Autowired
	private AccountKeyService accountKeyService;
	
	@Override
	public String create(VirtualAccountCustomerChargeModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getCustomerLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		// 支付单号
		m.setChargeNo(us.getNextUuid("CustCharge", 10));
		// 默认支付状态为待支付
		m.setChargeState(VirtualAccountCustomerChargeModel.UNDER_PAY);
		m.setCreateTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(VirtualAccountCustomerChargeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
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
	public void UpdateChargeStateToSuccess(VirtualAccountCustomerChargeModel m) {
		VirtualAccountCustomerChargeModel model = this.getByUuid(m.getUuid());
		model.setChargeState(VirtualAccountCustomerChargeModel.PAY_SUCCESS);
		this.update(model);

		// 修改会员账户的的余额
		CustomerAccountModel accountModel = accountInteractive
				.getCustomerAccountModelByCustomerUuid(m.getCustomerUuid());
		accountModel.setAccountBalance(accountModel.getAccountBalance()
				+ model.getOperAmount());
		accountInteractive.update(accountModel);

		// 添加会员虚拟账户日志
		VirtualAccountCustomerLogModel accountCustomerLog = new VirtualAccountCustomerLogModel();
		// 操作状态为收入
		accountCustomerLog.setOperType(VirtualAccountCustomerLogModel.IN);
		accountCustomerLog.setCustomerUuid(model.getCustomerUuid());
		accountCustomerLog.setOperAmount(model.getOperAmount());
		// 最新余额
		//accountCustomerLog.setNowBalance(accountModel.getAccountBalance());
		accountCustomerLogService.create(accountCustomerLog);
	}

	/**
	 * 充值失败后需要把重置状态修改为失败,不需要添加日志
	 * 
	 * @param m
	 *            void
	 */
	public void UpdateChargeStateToFail(VirtualAccountCustomerChargeModel m) {
		VirtualAccountCustomerChargeModel model = this.getByUuid(m.getUuid());
		model.setChargeState(VirtualAccountCustomerChargeModel.PAY_FAIL);

		this.update(model);
	}

	/**
	 * 查询充值记录的总数量<br>
	 * 
	 * 如果state为空,则查询的是所有的充值状态的记录
	 * 
	 * @param customerUuid
	 * @param state
	 * @return int
	 */
	public int getChargeCount(String customerUuid, String state) {
		return myDao.getChargeCount(customerUuid, state);
	}

	/**
	 * 查询充值记录列表,带分页的<br>
	 * 
	 * 如果state为空,则查询的是所有的充值状态的记录
	 * 
	 * @param customerUuid
	 * @param state
	 * @param fromNum
	 * @param pageShow
	 * @return List<VirtualAccountCustomerChargeModel>
	 */
	public List<VirtualAccountCustomerChargeModel> searchCharge(
			String customerUuid, String state, int fromNum, int pageShow) {
		return myDao.searchCharge(customerUuid, state, fromNum, pageShow);
	}

	/**
	 * 查询所有充值记录的总数量
	 */
	public int getAllChargeCount(VirtualAccountCustomerChargeQueryModel qm) {
		return myDao.getAllChargeCount(qm);
	}

	/**
	 * 根据会员id查询该会员的所有充值记录
	 */
	@Override
	public List<VirtualAccountCustomerChargeModel> searchAllCharge(
			String customerUuid) {
		return myDao.searchAllCharge(customerUuid);
	}

	/**
	 * 根据订单id获取对象
	 * 
	 * @param orderUuid
	 * @return
	 */
	@Override
	public VirtualAccountCustomerChargeModel getVirtualAccountCustomerChargeModelByOrderUuid(
			String orderUuid) {
		
		return myDao.getVirtualAccountCustomerChargeModelByOrderUuid(orderUuid);
	}
	
	/**
	 * 创建生成支付流水号
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String createChargeNo() {
		String returnNo = "";
		synchronized (NOTYPE_VACM) {
			// 日期
			String dateStr = DateFormatHelper.getNowTimeStr().substring(0, 10);
			String date = dateStr.replace("-", "");
			int maxcount = getMaxCount();

			if (maxcount == 1000000)
				maxcount = 1;

			// 保存订单流水号
			AccountKeyModel accountKey = accountKeyService
					.getAccountKeyModelById(NOTYPE_VACM);

			if (accountKey == null) {
				accountKey = new AccountKeyModel();
				accountKey.setId(NOTYPE_VACM);
				accountKey.setValue("1");
				accountKeyService.create(accountKey);
			} else {
				accountKey.setValue(maxcount + "");
				accountKeyService.update(accountKey);
			}

			// 生成订单号
			String orderno = getId(maxcount + "", 6);
			returnNo = date + orderno;

			return returnNo + "8";
		}
	}
		
		/**
		 * 返回最大数
		 * 
		 */
		private int getMaxCount() {
			try {
				AccountKeyModel key = accountKeyService
						.getAccountKeyModelById(NOTYPE_VACM);
				if (key != null) {
					return Integer.parseInt(key.getValue()) + 1;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return 1;
		}

		/**
		 * 获取定长流水号
		 * 
		 * @param sno
		 * @param length
		 * @return
		 */
		public static String getId(String sno, int length) {
			int zeronum = length - ((sno + "").length());

			String returnSno = "";

			for (int i = 0; i < zeronum; i++) {
				returnSno = returnSno + "0";
			}

			returnSno = returnSno + sno;

			return returnSno;
		}

}