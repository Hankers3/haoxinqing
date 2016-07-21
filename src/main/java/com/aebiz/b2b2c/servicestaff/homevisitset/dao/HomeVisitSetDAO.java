package com.aebiz.b2b2c.servicestaff.homevisitset.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.homevisitset.vo.HomeVisitSetModel;
import com.aebiz.b2b2c.servicestaff.homevisitset.vo.HomeVisitSetQueryModel;

public interface HomeVisitSetDAO extends
		BaseDAO<HomeVisitSetModel, HomeVisitSetQueryModel> {
	/**
	 * 根据医生id返回出诊信息
	 * 
	 * @param doctorid
	 * @return
	 */
	public List<HomeVisitSetModel> getByDoctorUuid(String doctorid);
	
	/**
	 * 根据医生id返回出诊信息
	 * 
	 * @param doctorid
	 * @return
	 */
	public List<HomeVisitSetModel> getByDoctorUuid(String doctorid,String plusState);
	/**
	 * 
	 * @Description: (根据医生的id获取出诊表的uuids)    
	 * @author XP  
	 * @param doctorid
	 * @return
	 * @date 2015-12-29 下午1:47:36
	 */
	public List<String> getUuidsByDoctorUuid(String doctorid);
	
	/**
	 * 根据周几获取设置对象
	 * @param weekDate
	 * @return
	 */
	public HomeVisitSetModel getHomeVisitSet(String weekDate,String doctorUuid);
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
	public int getPlusNumByDoctorUuidAndWeekDayAndTimeType(String doctorUuid, String weekDay, String timeType);

}