package com.aebiz.b2b2c.servicestaff.homevisitset.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.servicestaff.homevisitset.dao.HomeVisitSetDAO;
import com.aebiz.b2b2c.servicestaff.homevisitset.service.HomeVisitSetService;
import com.aebiz.b2b2c.servicestaff.homevisitset.vo.HomeVisitSetModel;
import com.aebiz.b2b2c.servicestaff.homevisitset.vo.HomeVisitSetQueryModel;

@Service
@Transactional
public class HomeVisitSetServiceImpl extends
		BaseServiceImpl<HomeVisitSetModel, HomeVisitSetQueryModel> implements
		HomeVisitSetService {
	private HomeVisitSetDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(HomeVisitSetDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(HomeVisitSetModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(HomeVisitSetModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}


	/**
	 * 根据医生id返回出诊信息
	 * 
	 * @param doctorid
	 * @return
	 */
	@Override
	public List<HomeVisitSetModel> getByDoctorUuid(String doctorid) {
		return myDao.getByDoctorUuid(doctorid);
	}
	
	/**
	 * 根据医生id返回出诊信息
	 * 
	 * @param doctorid
	 * @return
	 */
	@Override
	public List<HomeVisitSetModel> getByDoctorUuid(String doctorid,String plusState) {
		return myDao.getByDoctorUuid(doctorid,plusState);
	}
	/**
	 * 根据周几获取设置对象
	 * @param weekDate
	 * @return
	 */
	@Override
	public HomeVisitSetModel getHomeVisitSet(String weekDate,String doctorUuid) {
		
		return myDao.getHomeVisitSet(weekDate, doctorUuid);
	}
	/**
     * 
     * @Description: (获取医生设置的加号的人数)    
     * @author XP  
     * @param doctorUuid
     * @param weekDay
     * @param timeType
     * @return
     * @date 2016-1-24 下午2:43:04
     */
    @Override
    public int getPlusNumByDoctorUuidAndWeekDayAndTimeType(String doctorUuid, String weekDay, String timeType) {
        return myDao.getPlusNumByDoctorUuidAndWeekDayAndTimeType(doctorUuid, weekDay,timeType);
    }
}