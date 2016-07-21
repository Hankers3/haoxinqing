package com.aebiz.b2b2c.visitprecept.healthguide.service.impl;

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
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceModel;
import com.aebiz.b2b2c.visitprecept.healthguide.dao.HealthGuideDAO;
import com.aebiz.b2b2c.visitprecept.healthguide.service.HealthGuideService;
import com.aebiz.b2b2c.visitprecept.healthguide.vo.HealthGuideModel;
import com.aebiz.b2b2c.visitprecept.healthguide.vo.HealthGuideQueryModel;

@Service
@Transactional
public class HealthGuideServiceImpl extends BaseServiceImpl<HealthGuideModel,HealthGuideQueryModel> implements HealthGuideService {
	private HealthGuideDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(HealthGuideDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 创建
	 */
	@Override
	public String create(HealthGuideModel m) {
		m.setUuid(IdentityHelper.uuid2());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	

	/**
	 * 更新
	 */
	@Override
	public void update(HealthGuideModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	

	/**
	 * 删除
	 */
	@Override
	public void delete(HealthGuideModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
         * 获取所有给这个患者医嘱的医生的Uuid
         * @param customerUuid
         * @return
         */
        @Override
        public List<String> getAllDoctorUuidByCustomerUuid(String customerUuid) {
            return myDao.getAllDoctorUuidByCustomerUuid(customerUuid);
        }
        /**
         * 根据医生和患者的id获取健康指导的对象
         * @param customerUuid
         * @param doctorId
         * @return
         */
        @Override
        public HealthGuideModel getHealthGuideIdAndCustomerUuid(String customerUuid, String doctorId) {
            return myDao.getHealthGuideIdAndCustomerUuid(customerUuid,doctorId);
        }
        /**
         * 根据医生和患者获取健康指导的列表
         * @param doctorUuid
         * @param customerUuid
         * @return
         */
        @Override
        public List<HealthGuideModel> getHealthGuideListByCustomerUuidAndDoctorUuid(String doctorUuid,
                String customerUuid) {
            return myDao.getHealthGuideListByCustomerUuidAndDoctorUuid(doctorUuid,customerUuid);
        }
        /**
         * 
         * @Description: (根据医生id和随访记录的id获取医生健康指导)    
         * @author XP  
         * @param doctorUuid
         * @param visitRecordUuid
         * @return
         * @date 2016-1-22 下午2:04:00
         */
        @Override
        public HealthGuideModel getHealthGuideByDoctorUuidAndVisitRecordUuid(String doctorUuid,
                String visitRecordUuid) {
            return myDao.getHealthGuideByDoctorUuidAndVisitRecordUuid(doctorUuid,visitRecordUuid);
        }

	@Override
	public List<HealthGuideModel> getHealthGuideByDoctorUuidAndVisitUuid(String doctorUuid,
																		 String visitRecordUuid) {
		return myDao.getHealthGuideByDoctorUuidAndVisitUuid(doctorUuid,visitRecordUuid);
	}


	/**
         * 根据医生和患者获取健康指导的列表
         * @return
         */
        public List<HealthGuideModel> getHealthGuideListByVisitRecordUuid(String visitRecordUuid){
        	return myDao.getHealthGuideListByVisitRecordUuid(visitRecordUuid);
        }
}