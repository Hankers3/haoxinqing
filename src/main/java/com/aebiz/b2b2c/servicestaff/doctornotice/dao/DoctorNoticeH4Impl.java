package com.aebiz.b2b2c.servicestaff.doctornotice.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeModel;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeQueryModel;

@Repository
public class DoctorNoticeH4Impl extends BaseH4Impl<DoctorNoticeModel,DoctorNoticeQueryModel> implements DoctorNoticeDAO {

	
	@Override
	protected String getMultiModel() {
		
		return "  , ServicestaffModel as s";
	}
	/**
	 * 拼接医生表和医生公告表进行多条件的查询
	 * @param qm
	 * @return
	 */
	@Override
	protected String getAppendHql(DoctorNoticeQueryModel qm) {
		StringBuffer hql = new StringBuffer(" and o.serviceStatffUuid = s.uuid and o.delFlag =:delFlag ");
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			hql.append(" order by o." + qm.getSortName() + " " + qm.getSortType());
		}else{
			hql.append(" order by o.createTime desc");
		}
		if(!StringUtil.isEmpty(qm.getMobile())){
			hql.append(" and s.mobile like:mobile");
		}
		return hql.toString();
	}
	
	@Override
	protected void setAppendHqlValue(DoctorNoticeQueryModel qm, Query q) {
		if(!StringUtil.isEmpty(qm.getMobile())){
			q.setString("mobile", "%"+qm.getMobile()+"%");
		}
		q.setString("delFlag", "1");
	}
	
	/**
	 * 根据uuid值来删除医生公告表的相应的信息
	 */
	@Override
	public void deleteById(String uuid) {
  
		String hql = "update DoctorNoticeModel set delFlag =:delFlag  where uuid =:uuid";
		
		Query q = this.getH4Session().createQuery(hql);
		q.setString("delFlag", "2");
		q.setString("uuid", uuid);
		q.executeUpdate();
		
	}
	/**
	 * 根据医生uuid值来获取医生公告表的相应的信息
	 */

    @Override
    public DoctorNoticeModel getDoctorNoticeModelByDoctorUuid(String doctorId) {
        StringBuffer hql = new StringBuffer(" from DoctorNoticeModel as o where o.serviceStatffUuid =:doctorId");
        hql.append(" order by o.createTime desc ");
        Query q = this.getH4Session().createQuery(hql.toString());
        if(!StringUtil.isEmpty(doctorId)){
            q.setString("doctorId", doctorId);
         }
        List<DoctorNoticeModel> list = q.list();
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
         
        return null;
    }
    
    /**
     * 获取医生所有的公告
     * @param doctorUuid
     * @return
     */
    @Override
    public List<DoctorNoticeModel> getAllDoctorNoticeList(String doctorUuid) {
        StringBuffer hql = new StringBuffer(" from DoctorNoticeModel as o where o.serviceStatffUuid =:doctorUuid");
        Query q = this.getH4Session().createQuery(hql.toString());
        if(!StringUtil.isEmpty(doctorUuid)){
            q.setString("doctorUuid", doctorUuid);
         }
           List<DoctorNoticeModel> list = q.list();
           if(list!=null&&list.size()>0){
               return list;
           }
        return null;
    }
    /**
     * 
     * @Description: (根据医生的id获取医生公告的对象)    
     * @author XP  
     * @param doctorId
     * @return
     * @date 2015-12-29 上午11:26:15
     */
    @Override
    public String getDoctorNoticeUuid(String doctorId) {
        StringBuffer hql = new StringBuffer(
                " select o.uuid from DoctorNoticeModel as o where 1=1 ");
        hql.append(" and o.serviceStatffUuid =:doctorUuid ");
        hql.append(" order by  o.createTime desc ");
        
        Query q = this.getH4Session().createQuery(hql.toString());
        q.setString("doctorUuid", doctorId);
    
        List list = q.list();
        if (list != null && list.size() > 0) {
                return (String) list.get(0);
        }
    
        return "";
    }
    /**
     * 
     * @Description: (根据医生的id获取医生公告的uuids)    
     * @author XP  
     * @param doctorUuid
     * @return
     * @date 2015-12-29 上午11:47:12
     */
    @Override
    public List<String> getUuidsByDoctorUuid(String doctorUuid) {
        StringBuffer hql = new StringBuffer(
                " select crm.uuid  from DoctorNoticeModel crm where crm.serviceStatffUuid=:doctorUuid order by crm.createTime desc");
              
        Query query = this.getH4Session().createQuery(hql.toString());
        query.setString("doctorUuid", doctorUuid);
        
        List<String> list = query.list();
        if (list != null && list.size() > 0) {
                return list;
        }
        return null;
    }

	
}
