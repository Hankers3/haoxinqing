package com.aebiz.b2b2c.servicestaff.telephonecounse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.servicestaff.telephonecounse.dao.TelephoneCounseDAO;
import com.aebiz.b2b2c.servicestaff.telephonecounse.service.TelephoneCounseService;
import com.aebiz.b2b2c.servicestaff.telephonecounse.vo.TelephoneCounseModel;
import com.aebiz.b2b2c.servicestaff.telephonecounse.vo.TelephoneCounseQueryModel;

@Service
@Transactional
public class TelephoneCounseServiceImpl extends BaseServiceImpl<TelephoneCounseModel,TelephoneCounseQueryModel> implements TelephoneCounseService {
	private TelephoneCounseDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(TelephoneCounseDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(TelephoneCounseModel m) {
		m.setUuid(us.getNextUuid("TelephoneCounse",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(TelephoneCounseModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(TelephoneCounseModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	@Override
	public void deleteTeleCounse(TelephoneCounseModel qm) {
		super.delete(qm);
		
	}
	
	/**
	 * 获取所有该医生的所有已设置的电话咨询
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<TelephoneCounseModel> getAllTelephoneCounseModels(
			String doctorUuid) {
		
		return myDao.getAllTelephoneCounseModels(doctorUuid);
	}
	
	/**
	 * 根据星期获取电话咨询设置
	 * @param weekDate
	 * @return
	 */
	@Override
	public TelephoneCounseModel getTeleCounse(String weekDate,String doctorUuid) {
		
		return myDao.getTeleCounse(weekDate,doctorUuid);
	}
	/**
	 * 
	 * @Description: (多条件查询电话咨询设置)    
	 * @author XP  
	 * @param doctorUuid
	 * @param weekDate
	 * @param startTime
	 * @param endTime
	 * @param teleTimeUuid
	 * @param teleCostUuid
	 * @return
	 * @date 2016-1-11 上午11:19:25
	 */
        @Override
        public TelephoneCounseModel getTeleCounse(String doctorUuid, String weekDate, String startTime,
                String endTime, String teleTimeUuid, String teleCostUuid) {
            return myDao.getTeleCounse(doctorUuid,  weekDate,  startTime,
                    endTime, teleTimeUuid,  teleCostUuid) ;
        }


}