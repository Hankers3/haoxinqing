package com.aebiz.b2b2c.visitprecept.visitrecordextend.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendModel;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendModel;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendQueryModel;

@Repository
public class VisitRecordExtendH4Impl extends BaseH4Impl<VisitRecordExtendModel,VisitRecordExtendQueryModel> implements VisitRecordExtendDAO {

	
	/**
	 * 获取访问字段的扩展对象
	 */
	public List<VisitRecordExtendModel> getAllByVisitRecordUuid(String uuid) {
		String hql = new String(
				" from VisitRecordExtendModel as v where v.visitRecordUuid=:uuid ");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("uuid", uuid);
		 List<VisitRecordExtendModel> list =query.list();
		if(list !=null &&list.size()>0){
				return list;
			}
		return null;
	}

    @Override
    public List<VisitRecordExtendModel> getAllVisitRecordExtendListByPreceptUuid(String preceptUuid,
            String type) {
        String hql = new String(
                " from VisitRecordExtendModel where visitRecordUuid=:preceptUuid and type=:type ");
            
                Query query = this.getH4Session().createQuery(hql);
                query.setString("preceptUuid", preceptUuid);
                query.setString("type", type);
                
                List<VisitRecordExtendModel> list = query.list();
                if (list != null && list.size() > 0) {
                        return list;
                }
                return null;
    }
    

}
