package com.aebiz.b2b2c.customermgr.consultrecord.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordQueryModel;

public interface ConsultRecordService extends BaseService<ConsultRecordModel,ConsultRecordQueryModel>{

	/**
	 * 返回未处理消息数量
	 * @param doctorUuid 
	 */
	public int  getMissionCenterCount(String typeOnline, String doctorUuid);

	/**
	 * 返回未回复消息数量
	 * @param doctorUuid 
	 */
	public int getReplyCount(String typeOnline, String doctorUuid);

	/**
	 * 根据消息类别获取消息列表
	 * 
	 * @param messageType
	 * @param doctorUuid 
	 */
	public List<ConsultRecordModel> getInnerMessageListByMessageType(
			String messageType, String doctorUuid,String reply);
	/**
	 * 获取患者在该医生下咨询Uuid
	 * @param customerUuid
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	public List<String> getConsultRecordUuids(String messageType,String doctorUuid,String reply);

	/**
	 * 根据咨询类别获取用户编号
	 * 
	 * @param messageType
	 */
	public List<String> getCustomerUuidByType(String messageType);

	/**
	 * 通过患者id得到ConsultRecordModel的List
	 * @param uuid
	 * @param type 咨询类型  0在线咨询即图文咨询  1电话咨询  2预约加号 
	 * @return
	 */
	public List<ConsultRecordModel> getByCustomerUuid(String uuid, String type);


	/**
	 * 获取医生的咨询总量
	 * @param doctorId
	 * @return
	 */
        public int getConsultNumBydoctorId(String doctorId);
        
        /**
         * 获取医生的加号总量
         * @param doctorId
         * @return
         */
        public int getAllOrderNumByDoctorId(String doctorId);


	/**
	 * 得到已完成的电话咨询数量
	 * @param uuid
	 * @return
	 */
	public int getTeledCount(String uuid);
	
	/**
	 * 得到已完成的就诊（加号）数量
	 * @param uuid
	 * @return
	 */
	public int getSeeDocCount(String doctorUuid);

	/**
	 * 获取是否回复
	 * @param customerUuid
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	public ConsultRecordModel getConsultRecordReply(String customerUuid,String doctorUuid,String type);
	
	public ConsultRecordModel getConsultRecord(String messageType, String doctorUuid,String reply,String customerUuid);

	/**
	 * 获取患者在该医生下所有未回复的信息
	 * @param customerUuid
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	public List<ConsultRecordModel> getConsultRecords(String customerUuid,String doctorUuid,String type);

	
	
	public List getByConditionq(ConsultRecordQueryModel qm, int iDisplayStart, int iDisplayLength);

	public String addTag(String uuid, String tagId);

	public String deleteTag(String uuid, String tagUuid);
	/**
	 * 
	 * @Description: (查询预约加号的人数)    
	 * @author XP  
	 * @param doctorUuid
	 * @param timeType
	 * @param seeDoctorTime
	 * @return
	 * @date 2016-1-24 下午3:11:36
	 */
	public int getPatientNumByDoctorUuidAndCustomerUuidAndTimeType(String doctorUuid, String timeType,
            String seeDoctorTime);
	/**
	 * 
	 * @Description: (根据医生id和医生的咨询的类型获取咨询列表)    
	 * @author XP  
	 * @param doctorUuid
	 * @param consultType
	 * @return
	 * @date 2016-1-29 下午5:13:31
	 */
	public List<String> getByDoctorUuid(String doctorUuid, String consultType);
	
	/**
	 * 获取图文咨询待审核数据总数
	 * @return
	 */
	public int getCount(ConsultRecordQueryModel qm);

	

}
