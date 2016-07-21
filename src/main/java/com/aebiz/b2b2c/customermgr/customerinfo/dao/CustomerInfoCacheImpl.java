package com.aebiz.b2b2c.customermgr.customerinfo.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerInfoCacheImpl extends
		BaseMemcachedCache<CustomerInfoModel, CustomerInfoQueryModel> implements
		CustomerInfoDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerInfoDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerInfoCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY);
	}

	@Override
    public void update(CustomerInfoModel m) {
        dao.update(m);
        this.mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY + m.getCustomerUuid());
        this.mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY + m.getUuid());

    }
	
	/**
	 * 根据会员编号获取会员基础信息
	 */
	@Override
	public CustomerInfoModel getCustomerInfoModelByCustomerUuid(
			String customerUuid) {
		Object obj = this.mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY
						+ customerUuid);
		CustomerInfoModel m = null;
		if (obj != null) {
			m = (CustomerInfoModel) obj;
		} else {
			m = dao.getCustomerInfoModelByCustomerUuid(customerUuid);
			if (m != null) {
				this.mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY
						+ customerUuid, m);
				this.mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY
						+ m.getUuid(), m);
			}
		}
		return m;
	}

	/**
	 * 更新会员头像信息
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param image
	 *            头像名称
	 */
	@Override
	public void updateImage(String customerUuid, String image) {
		dao.updateImage(customerUuid, image);
		CustomerInfoModel cm = this.getCustomerInfoModelByCustomerUuid(customerUuid);
		if(cm !=null){
	        this.mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY + cm.getUuid());
	        this.mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY + customerUuid);
		}

	}
	/**
	 * 通过客户的Uuid查询用户的真实姓名
	 * 
	 * @param uuid
	 * 
	 * @return
	 */
	@Override
	public String getRealNameByUuid(String uuid) {
		// 从缓存中取得对象
		CustomerInfoModel cm = (CustomerInfoModel) mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY + uuid);
		// 如果对象为空，则从数据库中查询
		String realName="";
		if (cm == null) {
			cm = dao.getCustomerInfoModelByCustomerUuid(uuid);
			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY + cm.getUuid(),
						cm);
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY
						+ cm.getCustomerUuid(), cm);
				realName = cm.getRealName();
			}
		}else{
			realName = cm.getRealName();
		}
	
		return realName;
	}

	/**
	 * 通过客户的Uuid查询用户的性别
	 * 
	 * @param uuid
	 * 
	 * @return
	 */
	@Override
	public String getSexByUuid(String uuid) {
		// 从缓存中取得对象
		CustomerInfoModel cm = (CustomerInfoModel) mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY + uuid);
		// 如果对象为空，则从数据库中查询
		if (cm == null) {
			cm = dao.getCustomerInfoModelByCustomerUuid(uuid);
			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY + cm.getUuid(),
						cm);
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY
						+ cm.getCustomerUuid(), cm);
				
				return cm.getSex();
			}
		}
		// 如果为空，则返回空
		return "";
	}

	/**
	 * 通过客户的Uuid查询用户的生日
	 * 
	 * @param uuid
	 * 
	 * @return
	 */
	@Override
	public String getBirthdayByUuid(String uuid) {
		// 从缓存中取得对象
		CustomerInfoModel cm = (CustomerInfoModel) mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY + uuid);
		// 如果对象为空，则从数据库中查询
		if (cm == null) {
			cm = dao.getByUuid(uuid);
			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY + cm.getUuid(),
						cm);
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY
						+ cm.getCustomerUuid(), cm);
			}
		}
		// 如果为空，则返回空
		if (cm == null) {
			return "";
		}
		return cm.getBirthday();
	}

	/**
	 * 通过客户的Uuid查询用户的的年龄
	 * 
	 * @param uuid
	 * 
	 * @return
	 */
	@Override
	public String getAgeByUuid(String customerUuid) {
		// 从缓存中取得对象
		CustomerInfoModel cm = (CustomerInfoModel) mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY
						+ customerUuid);
		// 如果对象为空，则从数据库中查询
		if (cm == null) {
			cm = (CustomerInfoModel) dao
					.getCustomerInfoModelByCustomerUuid(customerUuid);
			// 从数据库中查询，如果存在，则放入到缓存
			if (cm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY
						+ customerUuid, cm);
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY
						+ cm.getUuid(), cm);
			}
		}
		// 如果为空，则返回空
		if (cm == null) {
			return "";
		}
		return cm.getAge();
	}

	/**
	 * 根据出生日期获得age的大小
	 * 
	 * @param customerUuid
	 * @param birthday
	 * @return
	 */
	@Override
	public void updateAgeByBirthday(String customerUuid, String birthday) {
		dao.updateAgeByBirthday(customerUuid, birthday);
		CustomerInfoModel cm = this.getCustomerInfoModelByCustomerUuid(customerUuid);
		if(cm !=null){
	        this.mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY + cm.getUuid());
	        this.mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMERINFO_KEY + customerUuid);
		}
	}

}
