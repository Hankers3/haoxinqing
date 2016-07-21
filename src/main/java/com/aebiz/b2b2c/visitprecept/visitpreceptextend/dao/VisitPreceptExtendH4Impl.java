package com.aebiz.b2b2c.visitprecept.visitpreceptextend.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendQueryModel;

@Repository

public class VisitPreceptExtendH4Impl extends BaseH4Impl<VisitPreceptExtendModel,VisitPreceptExtendQueryModel> implements VisitPreceptExtendDAO {
    /**
     * 获取其它方案的列表
     * @param visitUuid
     * @return
     */
    @Override
    public List<VisitPreceptExtendModel> getAllVisitPreceptByPreceptUuid(String visitUuid) {
        String hql = new String(
                " from VisitPreceptExtendModel where preceptUuid=:visitUuid ");
        
                Query query = this.getH4Session().createQuery(hql);
                query.setString("visitUuid", visitUuid);
                
                List<VisitPreceptExtendModel> list = query.list();
                if (list != null && list.size() > 0) {
                        return list;
                }
                return null;
    }
       
        



	

	
}
