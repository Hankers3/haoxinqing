package com.aebiz.b2b2c.visitprecept.visitrecord.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordModel;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordQueryModel;

public interface VisitRecordDAO extends BaseDAO<VisitRecordModel, VisitRecordQueryModel> {
	/**
	 * 根据医生编号 获取随访次数
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public int getCountByDoctorUuid(String doctorUuid);

	/**
	 * 根据随访方案的uuid获取所对应的所有的用户Uuids
	 * 
	 * @param uuid
	 * @return
	 */
	public List<String> getAllByUuid(String uuid);

	/**
	 * 根据患者编号 获取随访list
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<VisitRecordModel> getByCustomerUuid(String uuid);

	/**
	 * 根据医生id 获取随访患者的数量
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public int getVisitRecordNumByDoctorId(String doctorId);

	/**
	 * 根据随访方案获取随访人数
	 * 
	 * @param preceptUuid
	 * @return
	 */
	public int getCustomerNumByPreceptUuid(String doctorUuid,String preceptUuid);

	/**
	 * 
	 * 根据医生ID查找随访申请的list的接口
	 */
	public List<VisitRecordModel> getAllListByDoctorId(String doctorid);

	/**
	 * 根据随访方案id，医生ID，获取患者列表
	 * @param uuid
	 * @param preceptUuid
     * @return list
     */
	List<VisitRecordModel> getCustomerVisitRecordByUuid(String uuid,String preceptUuid);
	
	/**
	 * 根据医生ID查找所有随访患者
	 * @param doctorUuid
	 * @return
	 */
	public List<String> getCustomerUuidByDoctorUuid(String doctorUuid) ;


	/**
	 * 根据医生的ID获取所有的随访表单信息
	 * 
	 * @param doctorid
	 * @return
	 */
	public List<VisitRecordModel> getAllVisitRecordsByDoctorId(String doctorid);

	/**
	 * 根据医生的ID和患者的id获取随访申请的list集合
	 * 
	 * @param customerUuid
	 * @param doctorUuid
	 * @return
	 */
	public List<VisitRecordModel> getVisitRecordByCusAndDoc(String customerUuid, String doctorUuid);
	/**
	 * 获取随访信息
	 * @param customerUuid
	 * @param doctorUuid
	 * @return
	 */
	public VisitRecordModel getVisitRecord(String customerUuid, String doctorUuid);

	/**
	 * 
	 * @Description: (根据随访记录的id获取患者的id)
	 * @author XP
	 * @param visitRecordUuid
	 * @return
	 * @date 2016-1-22 下午2:52:35
	 */
	public String getCustomerUuidByUuid(String visitRecordUuid);

	/**
	 * 删除无用的随访表单
	 */
	public void deleteVisit();

	/**
	 * 根据随访方案id得到患者id集合
	 * 
	 * @param visitPreceptUuid
	 * @return
	 */
	public List<String> getCostomerUuidsByVisitPreceptUuid(String visitPreceptUuid);
}