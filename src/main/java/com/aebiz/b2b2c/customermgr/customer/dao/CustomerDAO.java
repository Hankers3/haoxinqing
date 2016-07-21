package com.aebiz.b2b2c.customermgr.customer.dao;

import java.io.IOException;
import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;

public interface CustomerDAO extends BaseDAO<CustomerModel, CustomerQueryModel> {
	/**
	 * 会员冻结:<br/>
	 * 冻结状态1表示冻结，0表示未冻结<br/>
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
	 * 冻结状态1表示冻结，0表示未冻结<br/>
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
	 * ****************************************对外接口*****************************
	 * 
	 * 通过会员编号或者手机号或者用户名获取会员信息
	 * 
	 * @param param
	 * @return
	 */
	public CustomerModel getCustomerModelByCondition(String param);

	/**
	 * 通过会员编号或者会员名称获取会员集合（编号和名称都是模糊查询）
	 * 
	 * @param qm
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	public List<CustomerModel> getCustomerModelListByCondition(
			CustomerQueryModel qm, int start, int pageShow);

	/**
	 * 通过会员会员名称模糊查询会员数量
	 * 
	 * @param qm
	 * @return
	 */
	public int getCountByCondition(CustomerQueryModel qm);

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
	public CustomerModel getCustomerModelByMobile(String mobile);

	/**
	 * 根据会员名称获取，会员uuid集合
	 * 
	 * @param customerName
	 *            会员名称
	 * @return
	 */
	public List<String> getCustomerUuids(String customerName);

	/**
	 * 通过会员编号获取会员头像名称
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	public String getCustomerInfoImageByCustomerUuid(String customerUuid);

	/**
	 * 通过账户或者邮箱或者手机号名称查询出该会员所有信息
	 */
	public CustomerModel getCustomerModelByLoginNameOrMobileOrEmail(
			String lonigNameOrMobileOrEmail);

	/**
	 * 根据订单状态获取对应的uuids
	 * 
	 * @param state
	 * @return
	 */
	public List<String> getCustomerUuidsByState(String state);

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
	public String checkCustomerCategory(List<String> checkIds);

	/**
	 * 患者审核通过，需要更新患者登录信息中的审核状态<br />
	 * 1表示通过，0表示未审核，2表示未通过
	 * 
	 * @return
	 * @throws IOException
	 */
	public void state(String customerUuid, String state);

	/**
	 * 根据患者的uuid获取患者的编号
	 * 
	 * @param uuid
	 * @return
	 */
	public String getCustomerNoByCustomerUuid(String uuid);

	/**
	 * 根据用户的Uuids将用户的所有的信息全部查询出来
	 * 
	 * 
	 * @return
	 * 
	 */
	List<CustomerModel> getAllCustomerByUuids(List<String> uuids);

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
	 * @return
	 */
	public CustomerModel getCustomerInfoModelByLoginNameAndPassword(
			String loginName, String pwd);
        /**
         * 根据用户的id获取用户的余额
         * @param customerUuid
         * @return
         */
        public double getAccountAmountByUuid(String customerUuid);

	/**
	 * 检查手机号和密码
	 * 
	 * @param mobile
	 * @param password
	 * @return
	 */
	public CustomerModel checkMobileAndPassword(String mobile, String password);

}