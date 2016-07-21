package com.aebiz.b2b2c.customermgr.customer.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customer.dao.CustomerDAO;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customershoplevel.service.CustomerShopLevelService;
import com.aebiz.b2b2c.customermgr.customersource.service.CustomerSourceService;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.MobileUtils;
import com.aebiz.b2b2c.order.accountkey.service.AccountKeyService;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyModel;

@Service
@Transactional
public class CustomerServiceImpl extends BaseServiceImpl<CustomerModel, CustomerQueryModel> implements CustomerService {
	// 编号类型验证码
	public static final String INVITE_CODE = "inviteCode";
	private CustomerDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	private CustomerShopLevelService customerShopLevelService;
	@Autowired
	private CustomerInfoService customerInfoService;
	@Autowired
	private AccountKeyService accountKeyService;
	@Autowired
	private CustomerSourceService customerSourceService;

	@Autowired
	public void setMyDao(CustomerDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 平台添加会员:<br />
	 * 
	 * 1.将冻结状态初始为未冻结<br />
	 * 2.初始化会员的激活状态为已激活<br />
	 * 3.初始化平台会员等级为最初级<br />
	 * 4.初始化创建时间为当前时间
	 */
	@Override
	public String create(CustomerModel m) {
		// 添加会员的时候需要将会员的平台等级设置为初始等级(最低等级),若平台会员等级没有就将平台等级uuid设置为空
		String customerShopLevelUuid = customerShopLevelService.getInitLevelUuid();

		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		// 创建会员编号
		m.setCustomerId(us.getNextUuid("C", 8));

		// 初始化会员的冻结状态初始为未冻结
		m.setFrozenState(CustomerModel.CUSTOMER_FROZENSTATE_UNFROZEN);

		// 初始化患者的审核状态为未激活
		m.setActiveState(CustomerModel.CUSTOMER_ACTIVESTATE_UNACTIVATE);

		// 初始化会员的实名认证状态为未认证
		m.setAuthState(CustomerModel.CUSTOMER_AUTHSTATE_UNAUTHED);

		// 初始化会员的平台会员等级为初始等级
		m.setCustomerShopLevelUuid(customerShopLevelUuid);

		// 初始化会员的创建时间
		m.setCreateTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		super.update(m);
	}

	/**
	 * 更新会员实名认证信息的同时需要更新会员信息,需要更新会员的实名认证状态
	 * 
	 * @param customerAuthModel
	 */
	public void updateCustomer(CustomerAuthModel customerAuthModel) {
		CustomerModel customerModel = getCustomerByUuid(customerAuthModel.getCustomerUuid());
		customerModel.setAuthState(customerAuthModel.getAuthState());
		super.update(customerModel);
	}

	/**
	 * 根据传入的用户名判断该用户名是否已经存在
	 */
	public boolean checkCustomerNameExist(String customerName) {
		return this.myDao.checkCustomerNameExist(customerName);
	}

	/**
	 * 根据传入的手机号判断手机号是否已经存在
	 */
	public boolean checkMobileExist(String mobile) {
		return this.myDao.checkMobileExist(mobile);
	}

	/**
	 * 根据传入的邮箱判断该邮箱是否已经存在
	 */
	public boolean checkEmailExist(String email) {
		return this.myDao.checkEmailExist(email);
	}

	/**
	 * 会员冻结:<br/>
	 * 冻结状态: 1表示冻结，0表示未冻结<br/>
	 * 需要设置冻结状态，冻结类型，操作人，冻结状态设置为1<br/>
	 */
	public void frozen(String customerUuid, String frozenType, String oper) {
		this.myDao.frozen(customerUuid, frozenType, oper);
	}

	/**
	 * 会员解冻:<br/>
	 * 冻结状态: 1表示冻结，0表示未冻结<br/>
	 * 需要记录操作人,将冻结状态设置为0，冻结类型设置为空<br/>
	 */
	public void unfrozen(String customerUuid, String oper) {
		this.myDao.unfrozen(customerUuid, oper);
	}

	/**
	 * 通过会员编号查询出会员
	 * 
	 * @param customerUuid
	 * @return
	 */
	public CustomerModel getCustomerByUuid(String customerUuid) {
		return this.myDao.getCustomerByUuid(customerUuid);
	}

	/**
	 * 会员重置密码,需要将随机生成的密码更新到数据库
	 */
	@Override
	public void resetPwd(String customerUuid, String randomPwd) {
		this.myDao.resetPwd(customerUuid, randomPwd);
	}

	/**
	 * 通过会员编号获取会员名称
	 */
	@Override
	public String getCustomerNameByCustomerUuid(String customerUuid) {
		return this.myDao.getCustomerNameByCustomerUuid(customerUuid);
	}

	/**
	 * 更新会员实名认证状态
	 */
	@Override
	public void updateCustomerAuthState(String customerUuid, String state) {
		this.myDao.updateCustomerAuthState(customerUuid, state);
	}

	/**
	 * 通过会员名称获取会员编号
	 * 
	 * @param customerName
	 *            会员名称
	 * @return
	 */
	@Override
	public String getCustomerUuidByCustomerName(String customerName) {

		return this.myDao.getCustomerUuidByCustomerName(customerName);
	}

	/**
	 * 通过会员名编号来获取用户电话号码
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	@Override
	public String getCustomerMobileByUuid(String customerUuid) {

		return myDao.getCustomerMobileByUuid(customerUuid);
	}

	/**
	 * 通过会员手机号获取会员
	 * 
	 * @param mobile
	 *            会员手机号
	 * @return
	 */
	@Override
	public CustomerModel getCustomerByMobile(String mobile) {
		return this.myDao.getCustomerModelByMobile(mobile);
	}

	/**
	 * 通过会员名称获取会员uuids
	 * 
	 * @param customerName
	 *            会员名称
	 * @return
	 */
	@Override
	public List<String> getCustomerUuids(String customerName) {
		return this.myDao.getCustomerUuids(customerName);
	}

	/**
	 * 创建生成邀请码 随机数位数
	 * 
	 * @return
	 */
	@Override
	public String createInviteCode(int length) {
		synchronized (INVITE_CODE) {
			// 获取随机数
			StringBuffer key = new StringBuffer("");

			if (length == 0 || !StringUtil.isEmpty("")) {
				key = new StringBuffer("");
			} else {
				key = MobileUtils.getRandomCodes(length);
			}
			// 获取随机数表的值
			AccountKeyModel accountKey = accountKeyService.getAccountKeyModelById(INVITE_CODE);
			if (accountKey != null) {
				key.append(accountKey.getValue());
				accountKey.setValue((Integer.parseInt(accountKey.getValue()) + 1) + "");
				accountKeyService.update(accountKey);
			}
			return key.toString();
		}
	}

	/**
	 * 验证邀请码是否已经存在 存在返回1、不存在返回0
	 * 
	 * @param inviteCode
	 * @return
	 */
	@Override
	public String inviteCodeIsExist(String inviteCode) {
		return customerSourceService.inviteCodeIsExist(inviteCode);
	}

	/**
	 * 根据订单状态获取对应的uuids
	 * 
	 * @param state
	 * @return
	 */
	@Override
	public List<String> getCustomerUuidsByState(String state) {

		return myDao.getCustomerUuidsByState(state);
	}

	/**
	 * 通过用户名或者手机号或者邮箱查询该会员信息
	 */
	@Override
	public CustomerModel getCustomerModelByLoginNameOrMobileOrEmail(String param) {
		return myDao.getCustomerModelByLoginNameOrMobileOrEmail(param);
	}

	/**
	 * 跟新会员登录的错误次数和最后一次错误登录时间
	 */
	@Override
	public void updateLoginErrorTimes(CustomerModel model) {
		myDao.updateLoginErrorTimes(model);

	}

	/**
	 * 通过手机号码来获得用户密码
	 * 
	 * @param mobile
	 * @return
	 */
	@Override
	public String getPassword(String mobile) {

		return myDao.getPassword(mobile);
	}

	/**
	 * 通过患者分类id获取该患者分类下是否有患者
	 * 
	 * @param checkIds
	 * @return
	 */
	@Override
	public boolean checkCustomerCategory(List<String> checkIds) {
		String flag = myDao.checkCustomerCategory(checkIds);
		if (flag.equals("1")) {
			return true;
		}
		return false;
	}

	/**
	 * 患者审核通过，需要更新患者登录信息中的审核状态<br />
	 * 1表示通过，0表示未审核，2表示未通过
	 * 
	 * @return
	 * @throws IOException
	 */
	@Override
	public void state(String customerUuid, String state) {
		this.myDao.state(customerUuid, state);
	}

	@Override
	public String getCustomerNoByCustomerUuid(String uuid) {
		return myDao.getCustomerNoByCustomerUuid(uuid);
	}

	/**
	 * 根据用户的Uuids将用户的所有的信息全部查询出来
	 * 
	 * @return
	 * 
	 */
	@Override
	public List<CustomerModel> getAllCustomerByUuids(List<String> Uuids) {
		return myDao.getAllCustomerByUuids(Uuids);
	}

	/**
	 * 获取患者编号
	 * 
	 * @param uuid
	 * @return
	 */
	public String getCustomerId(String uuid) {

		return myDao.getCustomerId(uuid);

	}

	/**
	 * 根据用户名和密码判断用户是否存在
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public CustomerModel getCustomerInfoModelByLoginNameAndPassword(String loginName, String pwd) {
		return myDao.getCustomerInfoModelByLoginNameAndPassword(loginName, pwd);
	}

	/**
	 * 根据用户Id获取用户的余额
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public double getAccountAmountByUuid(String customerUuid) {
		return myDao.getAccountAmountByUuid(customerUuid);
	}

	/**
	 * 检查手机号和密码
	 * 
	 * @param mobile
	 * @param password
	 * @return
	 */
	@Override
	public CustomerModel checkMobileAndPassword(String mobile, String password) {
		return this.myDao.checkMobileAndPassword(mobile, password);
	}

}