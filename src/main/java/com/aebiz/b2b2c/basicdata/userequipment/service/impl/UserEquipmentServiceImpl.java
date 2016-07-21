package com.aebiz.b2b2c.basicdata.userequipment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.basicdata.userequipment.dao.UserEquipmentDAO;
import com.aebiz.b2b2c.basicdata.userequipment.service.UserEquipmentService;
import com.aebiz.b2b2c.basicdata.userequipment.vo.UserEquipmentModel;
import com.aebiz.b2b2c.basicdata.userequipment.vo.UserEquipmentQueryModel;

@Service
@Transactional
public class UserEquipmentServiceImpl extends BaseServiceImpl<UserEquipmentModel, UserEquipmentQueryModel>
		implements UserEquipmentService {
	private UserEquipmentDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(UserEquipmentDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(UserEquipmentModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		// 设置操作时间
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		// 设置创建时间
		m.setCreateTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(UserEquipmentModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(UserEquipmentModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public UserEquipmentModel getByUserUuid(String userUuid, String deviceNumber) {
		return myDao.getByUserUuid(userUuid, deviceNumber);
	}
}