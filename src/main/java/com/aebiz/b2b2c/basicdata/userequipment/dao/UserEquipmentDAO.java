package com.aebiz.b2b2c.basicdata.userequipment.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.basicdata.userequipment.vo.UserEquipmentModel;
import com.aebiz.b2b2c.basicdata.userequipment.vo.UserEquipmentQueryModel;

public interface UserEquipmentDAO extends BaseDAO<UserEquipmentModel, UserEquipmentQueryModel> {
	/**
	 * 通过用户id和设备号得到对象
	 * 
	 * @param userUuid
	 * @param deviceNumber
	 * @return
	 */
	public UserEquipmentModel getByUserUuid(String userUuid, String deviceNumber);

}