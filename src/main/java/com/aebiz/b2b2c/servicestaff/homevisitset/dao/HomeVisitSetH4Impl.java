package com.aebiz.b2b2c.servicestaff.homevisitset.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.homevisitset.vo.HomeVisitSetModel;
import com.aebiz.b2b2c.servicestaff.homevisitset.vo.HomeVisitSetQueryModel;

@Repository
public class HomeVisitSetH4Impl extends
		BaseH4Impl<HomeVisitSetModel, HomeVisitSetQueryModel> implements
		HomeVisitSetDAO {
	/**
	 * 根据医生id返回出诊信息 没有查到返回一个新的ArrayList
	 * 
	 * @param doctorid
	 * @return
	 */
	@Override
	public List<HomeVisitSetModel> getByDoctorUuid(String doctorid) {
		StringBuffer hql = new StringBuffer(
				" from HomeVisitSetModel as o where 1=1 ");
		hql.append(" and o.doctorUuid =:doctorUuid ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorid);
		List<HomeVisitSetModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return new ArrayList<HomeVisitSetModel>();
	}
	
	/**
	 * 根据医生id返回出诊信息 没有查到返回一个新的ArrayList
	 * 
	 * @param doctorid
	 * @return
	 */
	@Override
	public List<HomeVisitSetModel> getByDoctorUuid(String doctorid,String plusState) {
		StringBuffer hql = new StringBuffer(
				" from HomeVisitSetModel as o where 1=1 ");
		hql.append(" and o.doctorUuid =:doctorUuid ");
		hql.append(" and o.plusState =:plusState ");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorid);
		q.setString("plusState", plusState);
		List<HomeVisitSetModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	/**
	 * 
	 * @Description: (根据医生的id获取出诊的uuids)    
	 * @author XP  
	 * @param doctorid
	 * @return
	 * @date 2015-12-29 下午1:55:02
	 */
    @Override
    public List<String> getUuidsByDoctorUuid(String doctorid) {
        StringBuffer hql = new StringBuffer(
                "select crm.uuid from HomeVisitSetModel crm where crm.doctorUuid=:doctorUuid ");
                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("doctorUuid", doctorid);
                List<String> list = query.list();
                if (list != null && list.size() > 0) {
                        return list;
                }
                return null;
    }
        
	/**
	 * 根据周几获取设置对象
	 * @param weekDate
	 * @return
	 */
	@Override
	public HomeVisitSetModel getHomeVisitSet(String weekDate,String doctorUuid) {
		StringBuffer hql = new StringBuffer(
				" from HomeVisitSetModel as o where 1=1 ");
		hql.append(" and o.doctorUuid =:doctorUuid ");
		hql.append(" and o.weekDate =:weekDate ");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("doctorUuid", doctorUuid);
		query.setString("weekDate", weekDate);
		 List<HomeVisitSetModel> list = query.list();
         if (list != null && list.size() > 0) {
           return list.get(0);
         }
		return null;
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
        StringBuffer hql = new StringBuffer("select crm.plusNum from HomeVisitSetModel crm where crm.doctorUuid=:doctorUuid ");
        hql.append(" and crm.weekDate =:weekDay ");
        
        if(!StringUtil.isEmpty(timeType)){
        	  hql.append(" and crm.timeType =:timeType ");
        }
      
        Query query = this.getH4Session().createQuery(hql.toString());
        query.setString("doctorUuid", doctorUuid);
        query.setString("weekDay", weekDay);
        if(!StringUtil.isEmpty(timeType)){
        	query.setString("timeType", timeType);
        }
        List list = query.list();
        if(list !=null && list.size()>0){
        	String num = (String) list.get(0);
             if (!StringUtil.isEmpty(num)) {
                     return Integer.parseInt(num);
             }
        }
        return 0;
    }

}
