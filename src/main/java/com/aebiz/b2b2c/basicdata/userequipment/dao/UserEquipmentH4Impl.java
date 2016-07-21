package com.aebiz.b2b2c.basicdata.userequipment.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.basicdata.userequipment.vo.UserEquipmentModel;
import com.aebiz.b2b2c.basicdata.userequipment.vo.UserEquipmentQueryModel;

@Repository
public class UserEquipmentH4Impl extends BaseH4Impl<UserEquipmentModel, UserEquipmentQueryModel>
		implements UserEquipmentDAO {
	/**
	 * 通过用户id得到对象
	 * 
	 * @param userUuid
	 * @return
	 */
	@Override
	public UserEquipmentModel getByUserUuid(String userUuid, String deviceNumber) {

		StringBuffer hql = new StringBuffer("select o from UserEquipmentModel o where 1=1 ");
		if (!StringUtil.isEmpty(userUuid)) {
			hql.append(" and o.userUuid =:userUuid ");
		}
		if (!StringUtil.isEmpty(deviceNumber)) {
			hql.append(" and o.deviceNumber=:deviceNumber ");
		}
		Query q = getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(userUuid)) {
			q.setString("userUuid", userUuid);
		}
		if (!StringUtil.isEmpty(deviceNumber)) {
			q.setString("deviceNumber", deviceNumber);
		}
		List<UserEquipmentModel>  obj = q.list();
		if (obj != null &&obj.size()>0) {
			return  obj.get(0);
		}
		return null;
	}

}
