package com.aebiz.b2b2c.customermgr.customerinfo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.dao.CustomerH4Impl;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoQueryModel;

@Repository
public class CustomerInfoH4Impl extends
		BaseH4Impl<CustomerInfoModel, CustomerInfoQueryModel> implements
		CustomerInfoDAO {
	/**
	 * 根据会员编号获取会员基础信息
	 */
	public CustomerInfoModel getCustomerInfoModelByCustomerUuid(
			String customerUuid) {
		StringBuffer hql = new StringBuffer(
				" from CustomerInfoModel where customerUuid =:customerUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);

		List list = query.list();
		if (list != null && list.size() > 0) {
			return (CustomerInfoModel) list.get(0);
		}
		return null;
	}

	/**
	 * 更新会员头像信息
	 */
	@Override
	public void updateImage(String customerUuid, String image) {
		StringBuffer hql = new StringBuffer(
				" update CustomerInfoModel set image=:image where customerUuid=:customerUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("image", image);
		query.setString("customerUuid", customerUuid);

		query.executeUpdate();
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
		StringBuffer hql = new StringBuffer(
				" select o.realName from CustomerInfoModel as o where o.customerUuid =:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		List list = query.list();

		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}
		return "";
	}

	@Override
	public String getSexByUuid(String uuid) {
		StringBuffer hql = new StringBuffer(
				" select o.sex from CustomerInfoModel as o where o.customerUuid =:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		List list = query.list();

		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}
		return "";
	}

	@Override
	public String getBirthdayByUuid(String uuid) {
		StringBuffer hql = new StringBuffer(
				" select o.birthday from CustomerInfoModel as o where o.customerUuid =:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		List list = query.list();

		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}
		return "";
	}

	/**
	 * 通过客户的Uuid查询用户的年龄
	 * 
	 * @param uuid
	 * 
	 * @return
	 */
	@Override
	public String getAgeByUuid(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				" select o.age from CustomerInfoModel as o where o.customerUuid =:customerUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		List list = query.list();

		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}
		return "";
	}

	/**
	 * 根据出生日期获得age的大小
	 * 
	 * @param customerUuid
	 * @param birthday
	 * @return
	 */
	public void updateAgeByBirthday(String customerUuid, String birthday) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		int age = 0;
		try {
			if (formatter.parse(birthday) != null) {
				if (cal.before(formatter.parse(birthday))) {
					throw new IllegalArgumentException("出生日期有问题 ");
				}

				int yearNow = cal.get(Calendar.YEAR);
				cal.setTime(formatter.parse(birthday));
				int yearBirth = cal.get(Calendar.YEAR);
				age = yearNow - yearBirth;
				StringBuffer hql = new StringBuffer(
						"update CustomerInfoModel set age =:age where customerUuid =:customerUuid");
				Query query = this.getH4Session().createQuery(hql.toString());
				query.setString("age", Integer.toString(age));
				query.setString("customerUuid", customerUuid);
				query.executeUpdate();
			}
		} catch (ParseException e) {

			e.printStackTrace();
		}

	}

}
