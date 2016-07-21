package com.aebiz.b2b2c.customermgr.customer.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.cms.common.CmsCacheConstant;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerCacheImpl extends
		BaseMemcachedCache<CustomerModel, CustomerQueryModel> implements
		CustomerDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY);
	}

	@Override
	public String create(CustomerModel m) {
		String ret = this.dao.create(m);
		return ret;
	}

	@Override
	public void delete(CustomerModel m) {
		super.deleteCache(m);
		super.delete(m);
	}

	@Override
	public void update(CustomerModel m) {
		dao.update(m);
		this.mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY
				+ m.getUuid());
	}

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
	@Override
	public void frozen(String uuid, String frozenType, String oper) {
		dao.frozen(uuid, frozenType, oper);
		mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + uuid);
	}

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
	@Override
	public void unfrozen(String uuid, String oper) {
		dao.unfrozen(uuid, oper);
		
		CustomerModel customer = getCustomerByUuid(uuid);
		
		//删除用用户名和密码做的缓存
		mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY+ customer.getMobile()+customer.getPassword());
		//删除用uuid做的缓存
		mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + uuid);
	}

	/**
	 * 根据传入的用户名判断该用户名是否已经存在
	 * 
	 * @param customerName
	 *            用户名
	 * @return
	 */
	@Override
	public boolean checkCustomerNameExist(String customerName) {
		return dao.checkCustomerNameExist(customerName);
	}

	/**
	 * 根据传入的手机号判断手机号是否已经存在
	 * 
	 * @param mobile
	 *            手机号
	 * @return
	 */
	@Override
	public boolean checkMobileExist(String mobile) {
		return dao.checkMobileExist(mobile);
	}

	/**
	 * 根据传入的邮箱判断该邮箱是否已经存在
	 * 
	 * @param email
	 *            邮箱
	 * @return
	 */
	@Override
	public boolean checkEmailExist(String email) {
		return dao.checkEmailExist(email);
	}

	/**
	 * 通过会员编号查询出会员
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	@Override
	public CustomerModel getCustomerByUuid(String customerUuid) {
		Object obj = this.mcc.get(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY
				+ customerUuid);
		CustomerModel m = null;
		if (obj != null) {
			m = (CustomerModel) obj;
		} else {
			m = dao.getByUuid(customerUuid);
			if (m != null) {
				this.mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY
						+ customerUuid, m);
			}
		}
		return m;
	}

	/**
	 * 会员重置密码,需要将随机生成的密码更新到数据库
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param randomPwd
	 *            随机密码
	 */
	@Override
	public void resetPwd(String customerUuid, String randomPwd) {
		dao.resetPwd(customerUuid, randomPwd);
		mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + customerUuid);
	}

	/**
	 * 通过会员编号获取会员名称
	 * 
	 * @param customerUuid
	 * @return
	 */
	@Override
	public String getCustomerNameByCustomerUuid(String customerUuid) {
		// 从缓存中取得对象
		CustomerModel cm = (CustomerModel) mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + customerUuid);
		// 如果对象为空，则从数据库中查询
		if (cm == null) {
			cm = (CustomerModel) dao.getByUuid(customerUuid);
			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY
						+ customerUuid, cm);
			}
		}
		// 如果为空，则返回空
		if (cm == null) {
			return "";
		}
		return cm.getCustomerName();
	}

	/**
	 * 更新会员实名认证状态
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param state
	 *            认证状态
	 */
	@Override
	public void updateCustomerAuthState(String customerUuid, String state) {
		dao.updateCustomerAuthState(customerUuid, state);
		mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + customerUuid);
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

		return dao.getCustomerUuidByCustomerName(customerName);
	}

	/**
	 * ****************************************对外接口*****************************
	 * 
	 * 通过会员编号或者手机号或者用户名获取会员信息
	 * 
	 * @param param
	 * @return
	 */
	@Override
	public CustomerModel getCustomerModelByCondition(String param) {
		return dao.getCustomerModelByCondition(param);
	}

	/**
	 * 通过会员编号或者会员名称获取会员集合（编号和名称都是模糊查询）
	 * 
	 * @param qm
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	@Override
	public List<CustomerModel> getCustomerModelListByCondition(
			CustomerQueryModel qm, int start, int pageShow) {
		List<String> uuids = this.dao.getUuidsByCondition(qm, start, pageShow);
		// 2：在内存中找 这些uuid对应的对象
		List<CustomerModel> listM = new ArrayList<CustomerModel>();
		List<String> noUuids = new ArrayList<String>();

		for (String uuid : uuids) {
			Object obj = mcc.get(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY
					+ uuid);
			if (obj != null) {
				CustomerModel m = (CustomerModel) obj;
				listM.add(m);
			} else {
				noUuids.add(uuid);
			}
		}

		// 3：内存中找不到对应对象的uuid，从数据库里面获取，并设置到缓存中
		if (noUuids.size() > 0) {
			for (String uuid : noUuids) {
				CustomerModel m = (CustomerModel) dao.getByUuid(uuid);
				// 并设置到缓存中
				if (m != null) {
					mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + uuid,
							m);
					listM.add(m);
				}
			}
		}
		return listM;
	}

	@Override
	public int getCountByCondition(CustomerQueryModel qm) {
		return dao.getCountByCondition(qm);
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
		// 从缓存中取得对象
		CustomerModel cm = (CustomerModel) mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + customerUuid);
		// 如果对象为空，则从数据库中查询
		if (cm == null) {
			cm = (CustomerModel) dao.getByUuid(customerUuid);
			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY
						+ customerUuid, cm);
			}
		}
		// 如果为空，则返回空
		if (cm == null) {
			return "";
		}
		return cm.getMobile();
	}

	/**
	 * 通过会员手机号获取会员
	 * 
	 * @param mobile
	 *            会员手机号
	 * @return
	 */
	@Override
	public CustomerModel getCustomerModelByMobile(String mobile) {
		Object obj = this.mcc.get(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY
				+ mobile);
		CustomerModel m = null;
		if (obj != null) {
			m = (CustomerModel) obj;
		} else {
			m = dao.getCustomerModelByMobile(mobile);
			if (m != null) {
				this.mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY
						+ mobile, m);
			}
		}
		return m;
	}

	/**
	 * 根据会员名称获取，会员uuid集合
	 * 
	 * @param customerName
	 *            会员名称
	 * @return
	 */
	@Override
	public List<String> getCustomerUuids(String customerName) {
		return dao.getCustomerUuids(customerName);
	}

	/**
	 * 通过会员编号获取会员头像名称
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	@Override
	public String getCustomerInfoImageByCustomerUuid(String customerUuid) {
		return dao.getCustomerInfoImageByCustomerUuid(customerUuid);
	}

	/**
	 * 通过账户或者邮箱或者手机号名称查询出该会员所有信息
	 */
	@Override
	public CustomerModel getCustomerModelByLoginNameOrMobileOrEmail(
			String lonigNameOrMobileOrEmail) {
		// 从缓存中取得对象
		CustomerModel cm = (CustomerModel) mcc.get(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + lonigNameOrMobileOrEmail);
		if (cm == null) {
			cm =  dao.getCustomerModelByLoginNameOrMobileOrEmail(lonigNameOrMobileOrEmail);
			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + lonigNameOrMobileOrEmail,
						cm);
			}
		}
		
		return cm ;
	}

	/**
	 * 根据订单状态获取对应的uuids
	 * 
	 * @param state
	 * @return
	 */
	@Override
	public List<String> getCustomerUuidsByState(String state) {
		return dao.getCustomerUuidsByState(state);
	}

	/**
	 * 跟新会员登录的错误次数和最后一次错误登录时间
	 * 
	 * @param model
	 *            void
	 * @author zdx
	 */
	@Override
	public void updateLoginErrorTimes(CustomerModel model) {
		dao.updateLoginErrorTimes(model);
	}

	/**
	 * 通过手机号码来获得用户密码
	 * 
	 * @param mobile
	 * @return
	 */
	@Override
	public String getPassword(String mobile) {
		// 从缓存中取得对象
		CustomerModel cm = (CustomerModel) mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + mobile);
		// 如果对象为空，则从数据库中查询
		if (cm == null) {
			cm = (CustomerModel) dao.getCustomerModelByMobile(mobile);
			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + mobile,
						cm);
			}
		}
		// 如果为空，则返回空
		if (cm == null) {
			return "";
		}
		return cm.getPassword();
	}

	/**
	 * 通过患者分类id获取该患者分类下是否有患者
	 * 
	 * @param checkIds
	 * @return
	 */
	@Override
	public String checkCustomerCategory(List<String> checkIds) {
		return dao.checkCustomerCategory(checkIds);
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
		dao.state(customerUuid, state);
	}

	/**
	 * 根据患者的uuid获取患者的编号
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public String getCustomerNoByCustomerUuid(String uuid) {
		// 从缓存中取得对象
		CustomerModel cm = (CustomerModel) mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + uuid);
		// 如果对象为空，则从数据库中查询
		if (cm == null) {
			cm = (CustomerModel) dao.getByUuid(uuid);
			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + uuid, cm);
			}
		}
		// 如果为空，则返回空
		if (cm == null) {
			return "";
		}
		return cm.getCustomerId();
	}

	/**
	 * 根据用户的Uuids将用户的所有的信息全部查询出来
	 * 
	 * 
	 * @return
	 * 
	 */
	@Override
	public List<CustomerModel> getAllCustomerByUuids(List<String> uuids) {
		List<CustomerModel> listM = new ArrayList<CustomerModel>();
		List<String> noUuids = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc
						.get(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + uuid);
				if (obj != null) {
					CustomerModel m = (CustomerModel) obj;
					listM.add(m);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					CustomerModel m = dao.getByUuid(uuid);
					if (m != null) {
						this.mcc.set(
								CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY
										+ uuid, m);
						listM.add(m);
					}
				}
			}
		}
		return listM;
	}

	/**
	 * 获取患者编号
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public String getCustomerId(String uuid) {
		// 从缓存中取得对象
		CustomerModel cm = (CustomerModel) mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + uuid);
		// 如果对象为空，则从数据库中查询
		if (cm == null) {
			cm = (CustomerModel) dao.getByUuid(uuid);
			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + uuid, cm);
			}
		}
		// 如果为空，则返回空
		if (cm == null) {
			return "";
		}
		return cm.getCustomerId();
	}

	/**
	 * 根据用户名和密码判断用户是否存在
	 * 
	 * @return
	 */
	@Override
	public CustomerModel getCustomerInfoModelByLoginNameAndPassword(
			String loginName, String pwd) {
		return dao.getCustomerInfoModelByLoginNameAndPassword(loginName, pwd);
	}

	@Override
	public double getAccountAmountByUuid(String customerUuid) {
		return dao.getAccountAmountByUuid(customerUuid);
	}

	@Override
	public CustomerModel checkMobileAndPassword(String mobile, String password) {
		// 从缓存中取得对象
		CustomerModel cm = (CustomerModel) mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + mobile+password);
		if (cm == null) {
			cm = dao.checkMobileAndPassword(mobile, password);
			if (cm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMER_KEY + mobile+password, cm);
			}
		} 
		return cm;
	}

}
