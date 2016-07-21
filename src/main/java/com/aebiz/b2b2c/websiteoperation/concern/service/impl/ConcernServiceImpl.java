package com.aebiz.b2b2c.websiteoperation.concern.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.websiteoperation.concern.service.ConcernService;
import com.aebiz.b2b2c.websiteoperation.concern.dao.ConcernDAO;
import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernModel;
import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernQueryModel;

@Service
@Transactional
public class ConcernServiceImpl extends
		BaseServiceImpl<ConcernModel, ConcernQueryModel> implements
		ConcernService {
	private ConcernDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(ConcernDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ConcernModel m) {
		m.setUuid(us.getNextUuid("Concern", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ConcernModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ConcernModel m) {
		super.delete(m);
	}

	/**
	 * 通过患者id得到患者关注医生表
	 * 
	 * @param costomerUuid
	 * @return
	 */
	@Override
	public List<ConcernModel> getByCustomerUuid(String customerUuid) {
		return myDao.getByCustomerUuid(customerUuid);
	}

	/**
	 * 查看用户是否关注该医生
	 */
	@Override
	public int getConcernType(String doctorId, String customerId) {
		return myDao.getConcernType(doctorId, customerId);
	}

	/**
	 * 获取关注该医生的粉丝数
	 */
	@Override
	public int getConcernNumByDoctorId(String doctorId) {
		return myDao.getConcernNumByDoctorId(doctorId);
	}

	/**
	 * 通过患者id和医生id得到患者关注医生表
	 * 
	 * @param doctorUuid
	 * @param costomerUuid
	 * @return
	 */
	@Override
	public ConcernModel getByCustomerAndDoctorUuid(String customerUuid,
			String doctorUuid) {
		return myDao.getByCustomerAndDoctorUuid(customerUuid,doctorUuid);
	}

}