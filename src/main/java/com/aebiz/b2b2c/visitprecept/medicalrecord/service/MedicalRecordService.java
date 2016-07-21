package com.aebiz.b2b2c.visitprecept.medicalrecord.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordModel;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordQueryModel;

public interface MedicalRecordService extends
		BaseService<MedicalRecordModel, MedicalRecordQueryModel> {
	/**
	 * 通过患者id得到病历信息
	 * 
	 * @param hid
	 * @return
	 */
	public List<MedicalRecordModel> getByCustomerUuid(String hid);

	/**
	 * 获取所有给这个患者医嘱的医生的Uuid
	 * 
	 * @param customerUuid
	 * @return
	 */
	public List<String> getAllDoctorUuidByCustomerUuid(String customerUuid);

	/**
	 * 根据患者和医生id获取病例
	 * 
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 */
	public List<MedicalRecordModel> getMedicalRecordListByCustomerUuidAndDoctorUuid(
			String customerUuid, String doctorUuid);

	/**
	 * 根据患者id得到最新的一天数据
	 * @param customerUuid
	 * @param doctorUuid 
	 * @return
	 */
	public MedicalRecordModel getNewestByCustomerUuidAndDoctorUuid(String customerUuid, String doctorUuid);

}
