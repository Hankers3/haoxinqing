package com.aebiz.b2b2c.visitprecept.doctoradvice.service.impl;

import java.util.List;

import com.wxcommon.util.IdentityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.visitprecept.doctoradvice.dao.DoctorAdviceDAO;
import com.aebiz.b2b2c.visitprecept.doctoradvice.service.DoctorAdviceService;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceModel;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceQueryModel;

@Service
@Transactional
public class DoctorAdviceServiceImpl extends
		BaseServiceImpl<DoctorAdviceModel, DoctorAdviceQueryModel> implements
		DoctorAdviceService {
	private DoctorAdviceDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(DoctorAdviceDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(DoctorAdviceModel m) {
		m.setUuid(IdentityHelper.uuid2());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(DoctorAdviceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(DoctorAdviceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过得到信息
	 * 
	 * @return
	 */
	public DoctorAdviceModel getByPreceptUuid(String visitRecordUuid,
			String type) {
		return myDao.getByPreceptUuid(visitRecordUuid, type);
	}

	/**
	 * 通过随访记录Uuid获取治疗方案
	 * 
	 * @return
	 */
	public DoctorAdviceModel getByVisitRecordUuid(String visitRecordUuid,
			String type) {
		List list = myDao.getByVisitRecordUuid(visitRecordUuid, type);
		return (DoctorAdviceModel) list.get(0);
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
	 * 根据医生和患者的id得到医嘱
	 * 
	 * @param customerUuid
	 * @param doctorId
	 * @return
	 */
	@Override
	public DoctorAdviceModel getModelByDoctorIdAndCustomerUuid(
			String customerUuid, String doctorId) {
		return myDao.getModelByDoctorIdAndCustomerUuid(customerUuid, doctorId);
	}

	/**
	 * 根据医生和患者的id获取医生建议的model
	 * 
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 */
	@Override
	public List<DoctorAdviceModel> getDoctorAdivceListByCustomerUuidAndDoctorUuid(
			String doctorUuid, String customerUuid) {
		return myDao.getDoctorAdivceListByCustomerUuidAndDoctorUuid(doctorUuid,
				customerUuid);
	}

	/**
	 * 根据医生和患者的id获取医生建议的model
	 * 
	 * @param customerUuid
	 * @return
	 */
	public List<DoctorAdviceModel> getAllDoctorAdivceListByCustomerUuid( String customerUuid){
		return myDao.getAllDoctorAdivceListByCustomerUuid(customerUuid);
	} 
	
	/**
	 * 根据病例的id和type类型获取治疗方案的list
	 * 
	 * @param medicalRecordUuid
	 * @return
	 */
	@Override
	public List<DoctorAdviceModel> getAllDoctorAdivceByUuidAndType(
			String medicalRecordUuid, String type) {
		return myDao.getAllDoctorAdivceByUuidAndType(medicalRecordUuid, type);
	}

	@Override
	public List<DoctorAdviceModel> getDoctorAdivceByUuidAndType(
			String visitRecordUuid, String type) {
		return myDao.getDoctorAdivceByUuidAndType(visitRecordUuid, type);
	}

	/**
	 * 根据表达id得到list
	 * 
	 * @param visitRecordUuid
	 * @return
	 */
	@Override
	public List<DoctorAdviceModel> getByVisitRecordUuid(String visitRecordUuid) {
		return myDao.getByVisitRecordUuid(visitRecordUuid, "0");
	}
}