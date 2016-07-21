package com.aebiz.b2b2c.servicestaff.telephonecounse.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.telephonecounse.vo.TelephoneCounseModel;
import com.aebiz.b2b2c.servicestaff.telephonecounse.vo.TelephoneCounseQueryModel;

@Repository
public class TelephoneCounseH4Impl extends BaseH4Impl<TelephoneCounseModel,TelephoneCounseQueryModel> implements TelephoneCounseDAO {
	/**
	 * 获取所有该医生的所有已设置的电话咨询
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<TelephoneCounseModel> getAllTelephoneCounseModels(
			String doctorUuid) {
		if(StringUtil.isEmpty(doctorUuid)){
			return null;
		}
		StringBuffer hql  = new StringBuffer(" from TelephoneCounseModel as o where o.doctorUuid =:doctorUuid ");
		hql.append(" order by o.weekDate asc ");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		
		List<TelephoneCounseModel> list =q.list();
		if(list !=null && list.size()>0){
			return list;
		}
		
		return null;
	}
	/**
	 * 根据星期获取电话咨询设置
	 * @param weekDate
	 * @return
	 */
	@Override
	public TelephoneCounseModel getTeleCounse(String weekDate,String doctorUuid) {
		if(StringUtil.isEmpty(weekDate)){
			return null;
		}
		StringBuffer hql  = new StringBuffer(" from TelephoneCounseModel as o where o.weekDate =:weekDate ");
		hql.append(" and o.doctorUuid =:doctorUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("weekDate", weekDate);
		q.setString("doctorUuid", doctorUuid);
		
		List<TelephoneCounseModel> list =q.list();
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}
	
	/**
	 * 根据医生的id获取uuids的集合
	 * @Description: (根据医生的id获取uuids的集合)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2015-12-29 下午3:20:13
	 */
    @Override
    public List<String> getUuidsByDoctorUuid(String doctorUuid) {
        StringBuffer hql = new StringBuffer(
                "select crm.uuid from TelephoneCounseModel crm where crm.doctorUuid=:doctorUuid ");
        	hql.append(" order by crm.weekDate asc ");
        	
            Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("doctorUuid", doctorUuid);
            List<String> list = query.list();
            if (list != null && list.size() > 0) {
                    return list;
            }
            return null;
    }
    
    /**
     * 
     * @Description: (多条件查询电话咨询的对象)    
     * @author XP  
     * @param doctorUuid
     * @param weekDate
     * @param startTime
     * @param endTime
     * @param teleTimeUuid
     * @param teleCostUuid
     * @return
     * @date 2016-1-11 上午11:22:41
     */
    @Override
    public TelephoneCounseModel getTeleCounse(String doctorUuid, String weekDate, String startTime,
            String endTime, String teleTimeUuid, String teleCostUuid) {
        StringBuffer hql  = new StringBuffer(" from TelephoneCounseModel as o where o.weekDate =:weekDate");
        //hql.append(" and o.startTime =:startTime and o.endTime =:endTime ");
        hql.append(" and o.teleTimeUuid =:teleTimeUuid ");
        hql.append(" and o.doctorUuid =:doctorUuid ");
        //hql.append(" and o.teleCostUuid =:teleCostUuid ");
        
        Query q = this.getH4Session().createQuery(hql.toString());
        q.setString("weekDate", weekDate);
        //q.setString("startTime", startTime);
        //q.setString("endTime", endTime);
        //q.setString("teleCostUuid", teleCostUuid);
        q.setString("teleTimeUuid", teleTimeUuid);
        q.setString("doctorUuid", doctorUuid);
        
        List<TelephoneCounseModel> list =q.list();
        if(list !=null && list.size()>0){
                return list.get(0);
        }
        return null;
    }

}
