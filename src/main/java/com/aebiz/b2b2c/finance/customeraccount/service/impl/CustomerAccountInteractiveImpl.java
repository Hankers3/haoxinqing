package com.aebiz.b2b2c.finance.customeraccount.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.customeraccount.dao.CustomerAccountDAO;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountQueryModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service.VirtualAccountCustomerLogService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;
/**
 * 会员账户信息表对外接口,在添加会员时需要添加进一条记录
 *
 * @author tangyunkai
 *
 * @date 2014年12月8日 下午8:33:52
 *
 */
@Service
@Transactional
public class CustomerAccountInteractiveImpl
		extends
			BaseServiceImpl<CustomerAccountModel, CustomerAccountQueryModel>
		implements
			CustomerAccountInteractive {
	private CustomerAccountDAO myDao = null;
	@Autowired
	private UuidService us;
	/*虚拟账户日志*/
	@Autowired
	private VirtualAccountCustomerLogService virtualAccountCustomerLogService;
	
	@Autowired
	public void setMyDao(CustomerAccountDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerAccountModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	/**
	 * 根据会员的uuid获取会员账户对象
	 * 
	 * @param customerUuid
	 * @return CustomerAccountModel
	 */
	public CustomerAccountModel getCustomerAccountModelByCustomerUuid(
			String customerUuid) {
		return myDao.getCustomerAccountModelByCustomerUuid(customerUuid);
	}

	/**
	 * 根据会员uuid获取虚拟账户的支付密码
	 * 
	 * @param customerUuid
	 * @return String
	 */
	public String getPayPasswdByCustomerUuid(String customerUuid) {
		CustomerAccountModel accountModel = myDao
				.getCustomerAccountModelByCustomerUuid(customerUuid);
		if (accountModel != null) {
			return accountModel.getPayPasswd();
		}

		return "";
	}

	/**
	 * 根据密码和会员的uuid校验输入的虚拟账户密码是否正确
	 * 
	 * @param password
	 * @param customerUuid
	 * @return boolean
	 */
	public boolean checkPassword(String password, String customerUuid) {
		CustomerAccountModel accountModel = this
				.getCustomerAccountModelByCustomerUuid(customerUuid);

		// TODO 以后密码会加密处理
		if (accountModel != null) {
			if (accountModel.getPayPasswd().equals(password)) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	/**
	 * 增加会员可用积分
	 * 
	 * @param customerUuid
	 * @param integral
	 *            void
	 */
	public void addIntegral(String customerUuid, int integral) {
		CustomerAccountModel accountModel = myDao
				.getCustomerAccountModelByCustomerUuid(customerUuid);
		if(null != accountModel){
		// 增加总积分
		accountModel.setTotalIntegral(accountModel.getTotalIntegral()
				+ integral);
		// 增加可用积分
		accountModel.setAvailableIntegral(accountModel.getAvailableIntegral()
				+ integral);
		this.update(accountModel);
		}else{
		    accountModel = new CustomerAccountModel();
	            // 增加总积分
	            accountModel.setTotalIntegral(accountModel.getTotalIntegral()
	                            + integral);
	            // 增加可用积分
	            accountModel.setAvailableIntegral(accountModel.getAvailableIntegral()
	                                + integral);
		    this.create(accountModel);
		}
		
	}

	/**
	 * 扣减会员可用积分
	 * 
	 * @param customerUuid
	 * @param integral
	 *            void
	 */
	public void reduceIntegral(String customerUuid, int integral) {
		CustomerAccountModel accountModel = myDao
				.getCustomerAccountModelByCustomerUuid(customerUuid);

		int availableIntegral = accountModel.getAvailableIntegral() - integral;
		if (availableIntegral < 0) {
			availableIntegral = 0;
		}
		// 扣减可用积分
		accountModel.setAvailableIntegral(availableIntegral);

		this.update(accountModel);
	}

	/**
	 * 增加绑定礼品卡的数量
	 * 
	 * @param customerUuid
	 *            void
	 */
	public void addGiftCardCount(String customerUuid) {
		CustomerAccountModel accountModel = myDao
				.getCustomerAccountModelByCustomerUuid(customerUuid);

		// 绑定数量+1
		accountModel.setGiftCardCount(accountModel.getGiftCardCount() + 1);

		this.update(accountModel);

	}
	
	/**
	 * 增加会员虚拟账余额
	 * @param customerUuid
	 * @param accountBalance 
	 * void
	 */
	public void addAccountBalance(String customerUuid, double accountBalance) {
		CustomerAccountModel accountModel = myDao
				.getCustomerAccountModelByCustomerUuid(customerUuid);
		accountModel.setAccountBalance(Float.parseFloat(accountBalance + "")
				+ accountModel.getAccountBalance());
		this.update(accountModel);
	}
	
	/**
	 * 扣减会员虚拟账户余额
	 * @param customerUuid
	 * @param accountBalance 
	 * void
	 */
	public void reduceAccountBalance(String customerUuid,double accountBalance){
		CustomerAccountModel accountModel = myDao
				.getCustomerAccountModelByCustomerUuid(customerUuid);
		float balance = accountModel.getAccountBalance() - Float.parseFloat(accountBalance+"");
		if(balance < 0){
			balance = 0;
		}
			accountModel.setAccountBalance(balance);
		this.update(accountModel);
	}

	
	/**
	 * 订单取消返还虚拟账户余额
	 * @param customerUuid 会员uuid
	 * @param accountBalance 返还金额
	 * @param orderNo  订单号
	 */
	@Override
	public void returnAccountBalance(String customerUuid, double accountBalance,String orderNo) {
		addAccountBalance(customerUuid, accountBalance);
		
		//虚拟账户支付日志
		VirtualAccountCustomerLogModel virtualAccountCustomerLogModel = new VirtualAccountCustomerLogModel();
		//用户名
		virtualAccountCustomerLogModel.setCustomerUuid(customerUuid);
		//订单号(这里保存的是订单号，没有保存订单uuid)
		virtualAccountCustomerLogModel.setOrderMainUuid(orderNo);
		// 操作类型  0 收入, 1支出
		virtualAccountCustomerLogModel.setOperType("0");
		//金额
		virtualAccountCustomerLogModel.setOperAmount((float)accountBalance);
		//备注
		virtualAccountCustomerLogModel.setDescription("订单"+orderNo+"取消，返还"+accountBalance+"元");
		
		virtualAccountCustomerLogService.create(virtualAccountCustomerLogModel);
		
	}
}