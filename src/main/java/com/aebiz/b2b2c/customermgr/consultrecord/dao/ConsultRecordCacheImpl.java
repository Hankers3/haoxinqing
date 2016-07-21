package com.aebiz.b2b2c.customermgr.consultrecord.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class ConsultRecordCacheImpl extends
		BaseMemcachedCache<ConsultRecordModel, ConsultRecordQueryModel>
		implements ConsultRecordDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ConsultRecordDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public ConsultRecordCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CONSULTRECORD_KEY);
	}

	/**
	 * 返回未处理消息数量
	 * 
	 * @param doctorUuid
	 */
	@Override
	public int getMissionCenterCount(String typeOnline, String doctorUuid) {
		return dao.getMissionCenterCount(typeOnline, doctorUuid);
	}

	/**
	 * 返回未回复消息数量
	 * 
	 * @param doctorUuid
	 */
	@Override
	public int getReplyCount(String typeOnline, String doctorUuid) {
		return dao.getReplyCount(typeOnline, doctorUuid);
	}

	/**
	 * 根据医生的uuid和messageType获取所有咨询记录的uuids
	 * 
	 * @param messageType
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<String> getAllConsultRecordUuids(String messageType,
			String doctorUuid) {
		List<String> uuids= this.dao.getAllConsultRecordUuids(messageType, doctorUuid);
		return uuids;
	}

	/**
	 * 根据消息类别获取消息列表
	 * 
	 * @param messageType
	 * @param doctorUuid
	 */
	@Override
	public List<ConsultRecordModel> getInnerMessageListByMessageType(
			String messageType, String doctorUuid,String reply) {
	
		List<ConsultRecordModel> listM = dao.getInnerMessageListByMessageType(messageType, doctorUuid, reply);
		
		return listM;
	}
	/**
	 * 获取患者在该医生下咨询Uuid
	 * @param customerUuid
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	public List<String> getConsultRecordUuids(String messageType,String doctorUuid,String reply){
		List<String> listM = dao.getConsultRecordUuids(messageType, doctorUuid, reply);
		
		return listM;
	}

	/**
	 * 根据咨询类别获取用户编号
	 * 
	 * @param messageType
	 */
	@Override
	public List<String> getCustomerUuidByType(String type) {
		return dao.getCustomerUuidByType(type);
	}

	/**
	 * 通过患者id得到ConsultRecordModel的List
	 * 
	 * @param uuid
	 * @param type
	 *            咨询类型 0在线咨询即图文咨询 1电话咨询 2预约加号
	 * @return
	 */
	@Override
	public List<ConsultRecordModel> getByCustomerUuid(String customerUuid,
			String type) {
		List<String> uuids = this.getUuidsByCustomerUuid(type, customerUuid);
		List<ConsultRecordModel> listM = new ArrayList<ConsultRecordModel>();
		List<String> noUuids = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc
						.get(CustomerCacheConstant.CUSTOMER_CONSULTRECORD_KEY
								+ uuid);
				if (obj != null) {
					ConsultRecordModel m = (ConsultRecordModel) obj;
					listM.add(m);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					ConsultRecordModel m = dao.getByUuid(uuid);
					if (m != null) {
						this.mcc.set(
								CustomerCacheConstant.CUSTOMER_CONSULTRECORD_KEY
										+ uuid, m);
						listM.add(m);
					}
				}
			}
		}
		return listM;
	}

	/**
	 * 通过患者id得到ConsultRecordModel的List
	 * 
	 * @author szr
	 * @param uuid
	 * @param type
	 *            咨询类型 0在线咨询即图文咨询 1电话咨询 2预约加号
	 * @return
	 */
	@Override
	public List<String> getUuidsByCustomerUuid(String type, String customerUuid) {
		List<String> uuids = this.dao.getUuidsByCustomerUuid(type, customerUuid);
		return uuids;
	}

	/**
	 * 获取医生的咨询总量
	 * 
	 * @param doctorId
	 * @return
	 */
	@Override
	public int getConsultNumBydoctorId(String doctorId) {
		return dao.getConsultNumBydoctorId(doctorId);
	}

	/**
	 * 获取医生的咨询总量
	 * 
	 * @param doctorId
	 * @return
	 */
	@Override
	public int getAllOrderNumByDoctorId(String doctorId) {
		return dao.getAllOrderNumByDoctorId(doctorId);
	}

	/**
	 * 得到已完成的电话咨询数量
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public int getTeledCount(String uuid) {
		return dao.getTeledCount(uuid);
	}

	/**
	 * 得到已完成的就诊（加号）数量
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public int getSeeDocCount(String doctorUuid) {
		return dao.getSeeDocCount(doctorUuid);
	}
	/**
	 * 获取是否回复
	 * @param customerUuid
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	@Override
	public ConsultRecordModel getConsultRecordReply(String customerUuid, String doctorUuid,
			String type) {
		
		return dao.getConsultRecordReply(customerUuid, doctorUuid, type);
	}
	/**
	 * 获取患者在该医生下所有未回复的信息
	 * @param customerUuid
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	@Override
	public List<ConsultRecordModel> getConsultRecords(String customerUuid,String doctorUuid,String type){
		return dao.getConsultRecords(customerUuid, doctorUuid, type);
	}

    @Override
    public List getByConditionq(ConsultRecordQueryModel qm, int iDisplayStart, int iDisplayLength) {
        return dao.getByConditionq(qm, iDisplayStart, iDisplayLength);
    }

    @Override
    public int getPatientNumByDoctorUuidAndCustomerUuidAndTimeType(String doctorUuid, String timeType,
            String seeDoctorTime) {
        return dao.getPatientNumByDoctorUuidAndCustomerUuidAndTimeType(doctorUuid, timeType, seeDoctorTime);
    }

    @Override
    public List<String> getByDoctorUuid(String doctorUuid, String consultType) {
        return dao.getByDoctorUuid( doctorUuid,consultType);
    }

	@Override
	public ConsultRecordModel getConsultRecord(String messageType,
			String doctorUuid, String reply ,String customerUuid) {
		
		return dao.getConsultRecord(messageType, doctorUuid, reply,customerUuid);
	}
	

	/**
	 * 获取图文咨询待审核数据总数
	 * 
	 * @return
	 */

	public int getCount(ConsultRecordQueryModel qm){
		return  dao.getCount(qm);
	}
}
