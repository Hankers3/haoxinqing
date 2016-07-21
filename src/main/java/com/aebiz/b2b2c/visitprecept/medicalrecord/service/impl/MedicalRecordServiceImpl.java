package com.aebiz.b2b2c.visitprecept.medicalrecord.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.visitprecept.medicalrecord.dao.MedicalRecordDAO;
import com.aebiz.b2b2c.visitprecept.medicalrecord.service.MedicalRecordService;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordModel;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordQueryModel;

@Service
@Transactional
public class MedicalRecordServiceImpl extends
		BaseServiceImpl<MedicalRecordModel, MedicalRecordQueryModel> implements
		MedicalRecordService {
	private MedicalRecordDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(MedicalRecordDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(MedicalRecordModel m) {
		m.setUuid(us.getNextUuid("MedicalRecord", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(MedicalRecordModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(MedicalRecordModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过患者id得到病历信息
	 * 
	 * @param hid
	 * @return
	 */
	@Override
	public List<MedicalRecordModel> getByCustomerUuid(String hid) {
		return myDao.getByCustomerUuid(hid);
	}

	/**
	 * 获取所有给这个患者医嘱的医生的Uuid
	 * 
	 * @param customerUuid
	 * @return
	 */
	@Override
	public List<String> getAllDoctorUuidByCustomerUuid(String customerUuid) {
		return myDao.getAllDoctorUuidByCustomerUuid(customerUuid);
	}

	/**
	 * 根据患者和医生id获取病例
	 * 
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 */
	@Override
	public List<MedicalRecordModel> getMedicalRecordListByCustomerUuidAndDoctorUuid(
			String customerUuid, String doctorUuid) {
		return myDao.getMedicalRecordListByCustomerUuidAndDoctorUuid(
				customerUuid, doctorUuid);
	}

	/**
	 * 根据患者id得到最新的一天数据
	 * 
	 * @param customerUuid
	 * @return
	 */
	@Override
	public MedicalRecordModel getNewestByCustomerUuidAndDoctorUuid(String customerUuid,String doctorUuid) {
		return myDao.getNewestByCustomerUuidAndDoctorUuid(customerUuid,doctorUuid);
	}
}