package com.aebiz.b2b2c.customermgr.consultrecord.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.consultrecord.dao.ConsultRecordDAO;
import com.aebiz.b2b2c.customermgr.consultrecord.service.ConsultRecordService;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;

@Service
@Transactional
public class ConsultRecordServiceImpl extends
		BaseServiceImpl<ConsultRecordModel, ConsultRecordQueryModel> implements
		ConsultRecordService {
	private ConsultRecordDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(ConsultRecordDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ConsultRecordModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ConsultRecordModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ConsultRecordModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 返回未处理消息数量
	 */
	@Override
	public int getMissionCenterCount(String typeOnline, String doctorUuid) {
		return myDao.getMissionCenterCount(typeOnline, doctorUuid);
	}

	/**
	 * 返回未回复消息数量
	 */
	@Override
	public int getReplyCount(String typeOnline, String doctorUuid) {
		return myDao.getReplyCount(typeOnline, doctorUuid);
	}

	/**
	 * 根据消息类别获取消息列表
	 * 
	 * @param messageType
	 * @return
	 */
	@Override
	public List<ConsultRecordModel> getInnerMessageListByMessageType(
			String messageType, String doctorUuid,String reply) {
		return myDao.getInnerMessageListByMessageType(messageType, doctorUuid, reply);
	}
	/**
	 * 获取患者在该医生下咨询Uuid
	 * @param customerUuid
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	@Override
	public List<String> getConsultRecordUuids(String messageType,String doctorUuid,String reply){
		return myDao.getConsultRecordUuids(messageType, doctorUuid, reply);
		
	}

	/**
	 * 根据咨询类别获取用户编号
	 * 
	 * @param messageType
	 */
	@Override
	public List<String> getCustomerUuidByType(String type) {

		return myDao.getCustomerUuidByType(type);
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
	public List<ConsultRecordModel> getByCustomerUuid(String uuid, String type) {
		return myDao.getByCustomerUuid(uuid, type);
	}

	/**
	 * 获取医生的咨询总量
	 * 
	 * @param doctorId
	 * @return
	 */
	@Override
	public int getConsultNumBydoctorId(String doctorId) {
		return myDao.getConsultNumBydoctorId(doctorId);
	}

	/**
	 * 获取医生的咨询总量
	 * 
	 * @param doctorId
	 * @return
	 */
	@Override
	public int getAllOrderNumByDoctorId(String doctorId) {
		return myDao.getAllOrderNumByDoctorId(doctorId);
	}

	/**
	 * 得到已完成的电话咨询数量
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public int getTeledCount(String uuid) {
		return myDao.getTeledCount(uuid);
	}

	/**
	 * 得到已完成的就诊（加号）数量
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public int getSeeDocCount(String doctorUuid) {
		return myDao.getSeeDocCount(doctorUuid);
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
		
		return myDao.getConsultRecordReply(customerUuid, doctorUuid, type);
	}
	
	/**
	 * 获取是否回复
	 * @param customerUuid
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	@Override
	public ConsultRecordModel getConsultRecord(String messageType, String doctorUuid,
			String reply,String customerUuid) {
		return myDao.getConsultRecord(messageType, doctorUuid, reply,customerUuid);
	}
	
	/**
	 * 
	 * @param customerUuid
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	public List<ConsultRecordModel> getConsultRecords(String customerUuid,String doctorUuid,String type){
		return myDao.getConsultRecords(customerUuid, doctorUuid, type);
	}

	
    @Override
    public List getByConditionq(ConsultRecordQueryModel qm, int iDisplayStart, int iDisplayLength) {
        return myDao.getByConditionq( qm,  iDisplayStart, iDisplayLength);
    }
    
    @Override
    public String addTag(String uuid, String tagId) {
        if (StringUtil.isEmpty(uuid)) {
            return "";
        }
        ConsultRecordModel staffModel = this.getByUuid(uuid);
        // 医生标签
        String doctorTag = staffModel.getTagsUuid();
        StringBuffer newtag = new StringBuffer("");
        if (StringUtil.isEmpty(doctorTag)) {
            newtag.append(tagId);
        } else {
        	newtag.append(doctorTag).append(tagId);
        }

        staffModel.setTagsUuid(newtag.toString());
        this.update(staffModel);

        return "success";

    }

    @Override
    public String deleteTag(String uuid, String tagUuid) {
        if (StringUtil.isEmpty(uuid)) {
            return "";
    }
   ConsultRecordModel staffModel = this.getByUuid(uuid);
    // 医生标签
    String doctorTag = staffModel.getTagsUuid();
    String[] tagsUuid = doctorTag.split(";");
    StringBuffer newtag = new StringBuffer("");

    if (tagsUuid != null && tagsUuid.length > 0) {
            for (int i = 0; i < tagsUuid.length; i++) {
                    String tagId = tagsUuid[i];
                    if (!tagUuid.equals(tagId)) {
                            newtag.append(tagId + ";");
                    }
            }
    }

    staffModel.setTagsUuid(newtag.toString());
    this.update(staffModel);

    return "success";
    }
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
    @Override
    public int getPatientNumByDoctorUuidAndCustomerUuidAndTimeType(String doctorUuid, String timeType,
            String seeDoctorTime) {
        return myDao.getPatientNumByDoctorUuidAndCustomerUuidAndTimeType(doctorUuid,timeType,seeDoctorTime);
    }
    /**
     * 
     * @Description: (根据医生id和医生的咨询的类型获取咨询列表)    
     * @author XP  
     * @param doctorUuid
     * @param consultType
     * @return
     * @date 2016-1-29 下午5:13:31
     */
    @Override
    public List<String> getByDoctorUuid(String doctorUuid, String consultType) {
        return myDao.getByDoctorUuid(doctorUuid, consultType);
    }
    
    /**
	 * 获取图文咨询待审核数据总数
	 * @return
	 */
    @Override
	public int getCount(ConsultRecordQueryModel qm){
		return myDao.getCount(qm);
	}

}