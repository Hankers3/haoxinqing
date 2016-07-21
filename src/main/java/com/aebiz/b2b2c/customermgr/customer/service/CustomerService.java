package com.aebiz.b2b2c.customermgr.customer.service;

import java.io.IOException;
import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthModel;

public interface CustomerService extends BaseService<CustomerModel, CustomerQueryModel> {

	/**
	 * 会员冻结:<br/>
	 * 冻结状态: 1表示冻结，0表示未冻结<br/>
	 * 需要设置冻结状态，冻结类型，操作人，冻结状态设置为1<br/>
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param frozenType
	 *            冻结类型
	 * @param oper
	 *            操作人
	 */
	public void frozen(String customerUuid, String frozenType, String oper);

	/**
	 * 会员解冻:<br/>
	 * 冻结状态: 1表示冻结，0表示未冻结<br/>
	 * 需要记录操作人,将冻结状态设置为0，冻结类型设置为空<br/>
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param oper
	 *            操作人
	 */
	public void unfrozen(String customerUuid, String oper);

	/**
	 * 根据传入的用户名判断该用户名是否已经存在
	 * 
	 * @param customerName
	 *            用户名
	 * @return
	 */
	public boolean checkCustomerNameExist(String customerName);

	/**
	 * 根据传入的手机号判断手机号是否已经存在
	 * 
	 * @param mobile
	 *            手机号
	 * @return
	 */
	public boolean checkMobileExist(String mobile);

	/**
	 * 根据传入的邮箱判断该邮箱是否已经存在
	 * 
	 * @param email
	 *            邮箱
	 * @return
	 */
	public boolean checkEmailExist(String email);

	/**
	 * 更新会员实名认证信息的同时需要更新会员信息,需要更新会员的实名认证状态
	 * 
	 * @param customerAuthModel
	 */
	public void updateCustomer(CustomerAuthModel customerAuthModel);

	/**
	 * 通过会员编号查询出会员
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	public CustomerModel getCustomerByUuid(String customerUuid);

	/**
	 * 会员重置密码,需要将随机生成的密码更新到数据库
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param randomPwd
	 *            随机密码
	 */
	public void resetPwd(String customerUuid, String randomPwd);

	/**
	 * 通过会员编号获取会员名称
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	public String getCustomerNameByCustomerUuid(String customerUuid);

	/**
	 * 更新会员实名认证状态
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param state
	 *            认证状态
	 */
	public void updateCustomerAuthState(String customerUuid, String state);

	/**
	 * 通过会员名称获取会员编号
	 * 
	 * @param customerName
	 *            会员名称
	 * @return
	 */
	public String getCustomerUuidByCustomerName(String customerName);

	/**
	 * 通过会员名编号来获取用户电话号码
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	public String getCustomerMobileByUuid(String customerUuid);

	/**
	 * 通过会员手机号获取会员
	 * 
	 * @param mobile
	 *            会员手机号
	 * @return
	 */
	public CustomerModel getCustomerByMobile(String mobile);

	/**
	 * 根据会员名称获取，会员uuid集合
	 * 
	 * @param customerName
	 *            会员名称
	 * @return
	 */
	public List<String> getCustomerUuids(String customerName);

	/**
	 * 创建生成邀请码 length 邀请码长度
	 * 
	 * @return
	 */
	public String createInviteCode(int length);

	/**
	 * 验证邀请码是否已经存在 存在返回1、不存在返回0
	 * 
	 * @param inviteCode
	 * @return
	 */
	public String inviteCodeIsExist(String inviteCode);

	/**
	 * 根据订单状态获取对应的uuids
	 * 
	 * @param state
	 * @return
	 */
	public List<String> getCustomerUuidsByState(String state);

	/**
	 * 通过用户名或者手机号或者邮箱查询该会员信息
	 * 
	 * @param param
	 * @return
	 * @author zdx
	 */
	public CustomerModel getCustomerModelByLoginNameOrMobileOrEmail(String param);

	/**
	 * 跟新会员登录的错误次数和最后一次错误登录时间
	 * 
	 * @param model
	 *            void
	 * @author zdx
	 */
	public void updateLoginErrorTimes(CustomerModel model);

	/**
	 * 通过手机号码来获得用户密码
	 * 
	 * @param mobile
	 * @return
	 */
	public String getPassword(String mobile);

	/**
	 * 通过患者分类id获取该患者分类下是否有患者
	 * 
	 * @param checkIds
	 * @return
	 */
	boolean checkCustomerCategory(List<String> checkIds);

	/**
	 * 患者审核通过，需要更新患者登录信息中的审核状态<br />
	 * 1表示通过，0表示未审核，2表示未通过
	 * 
	 * @param state
	 * 
	 * @return
	 * @throws IOException
	 */
	public void state(String customerUuid, String state);

	/**
	 * 得到No
	 * 
	 * @param uuid
	 * @return
	 */
	public String getCustomerNoByCustomerUuid(String uuid);

	/**
	 * 根据患者的uuids将所有的患者的信息查询出来
	 * 
	 * @param Uuids
	 * 
	 * @return
	 * 
	 */
	public List<CustomerModel> getAllCustomerByUuids(List<String> Uuids);

	/**
	 * 获取患者编号
	 * 
	 * @param uuid
	 * @return
	 */
	public String getCustomerId(String uuid);

	/**
	 * 根据用户名和密码判断用户是否存在
	 * 
	 * @param uuid
	 * @return
	 */
	public CustomerModel getCustomerInfoModelByLoginNameAndPassword(String loginName, String pwd);

	/**
	 * 检查手机号和密码
	 * 
	 * @param mobile
	 * @param password
	 * @return
	 */
	public CustomerModel checkMobileAndPassword(String mobile, String password);

	/**
	 * 根据患者的id获取患者的余额
	 * 
	 * @param customerUuid
	 * @return
	 */
	public double getAccountAmountByUuid(String customerUuid);

}
