package com.aebiz.b2b2c.websiteoperation.doctorshare.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.websiteoperation.doctorshare.vo.DoctorShareModel;
import com.aebiz.b2b2c.websiteoperation.doctorshare.vo.DoctorShareQueryModel;

@Repository
public class DoctorShareH4Impl extends BaseH4Impl<DoctorShareModel,DoctorShareQueryModel> implements DoctorShareDAO {
    /**
     * 根据医生的id和文章的id获取该文章是否被分享
     * @param doctorUuid
     * @param uuid
     * @return
     */
    @Override
    public int getShareTypeByDoctorUuidAndContenUuid(String doctorUuid, String contentUuid) {
        StringBuffer hql = new StringBuffer(
                " select count(distinct o.uuid) from DoctorShareModel as o where o.userUuid=:doctorUuid and o.contentUuid=:contentUuid ");
        
            Query q = this.getH4Session().createQuery(hql.toString());
            q.setString("doctorUuid", doctorUuid);
            q.setString("contentUuid", contentUuid);
            
            return ((Number) q.uniqueResult()).intValue();
    }

}
