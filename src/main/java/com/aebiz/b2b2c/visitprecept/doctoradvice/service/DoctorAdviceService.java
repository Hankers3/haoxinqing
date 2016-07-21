package com.aebiz.b2b2c.visitprecept.doctoradvice.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceModel;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceQueryModel;
import com.hxq.mobile.entity.visit.DoctorAdvice;
import com.hxq.mobile.entity.visit.DoctorAdviceMain;

public interface DoctorAdviceService extends
		BaseService<DoctorAdviceModel, DoctorAdviceQueryModel> {

	/**
	 * 通过治疗uuid获取治疗方案对象
	 * 
	 * @param uuid
	 * @return
	 */
	public DoctorAdviceModel getByPreceptUuid(String uuid, String type);

	/**
	 * 通过随访记录Uuid获取治疗方案
	 * 
	 * @param uuid
	 * @return
	 */
	public DoctorAdviceModel getByVisitRecordUuid(String visitRecordUuid,
			String type);

	/**
	 * 获取所有给这个患者医嘱的医生的Uuid
	 * 
	 * @param customerUuid
	 * @return
	 */
	public List<String> getAllDoctorUuidByCustomerUuid(String customerUuid);

	/**
	 * 根据医生和患者的id得到医嘱
	 * 
	 * @param customerUuid
	 * @param doctorId
	 * @return
	 */
	public DoctorAdviceModel getModelByDoctorIdAndCustomerUuid(
			String customerUuid, String doctorId);

	/**
	 * 根据医生和患者的id获取医生建议的model
	 * 
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 */
	public List<DoctorAdviceModel> getDoctorAdivceListByCustomerUuidAndDoctorUuid(
			String doctorUuid, String customerUuid);
	/**
	 * 根据医生和患者的id获取医生建议的model
	 * 
	 * @param doctorUuid
	 * @param customerUuid
	 * @return
	 */
	public List<DoctorAdviceModel> getAllDoctorAdivceListByCustomerUuid( String customerUuid); 
	/**
	 * 根据病例的id和type类型获取治疗方案的list
	 * 
	 * @param medicalRecordUuid
	 * @param string
	 * @return
	 */
	public List<DoctorAdviceModel> getAllDoctorAdivceByUuidAndType(
			String medicalRecordUuid, String type);

	List<DoctorAdviceModel> getDoctorAdivceByUuidAndType(String visitRecordUuid, String type);
	/**
	 * 根据表达id得到list
	 * 
	 * @param visitRecordUuid
	 * @return
	 */
	public List<DoctorAdviceModel> getByVisitRecordUuid(String visitRecordUuid);

}
