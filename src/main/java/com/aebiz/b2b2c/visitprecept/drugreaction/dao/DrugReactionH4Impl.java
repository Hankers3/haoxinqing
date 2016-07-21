package com.aebiz.b2b2c.visitprecept.drugreaction.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionModel;
import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionQueryModel;

@Repository
public class DrugReactionH4Impl extends BaseH4Impl<DrugReactionModel,DrugReactionQueryModel> implements DrugReactionDAO {

	
	
	//根据随访表单的Uuid获取不良反应的对象
	public DrugReactionModel getLastDrugReactionModelByUuid(String uuid) {
		String hql = new String(
				" from DrugReactionModel as d where d.visitRecordUuid=:uuid ");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("uuid", uuid);
		 List<DrugReactionModel> list =query.list();
		if(list !=null &&list.size()>0){
				return list.get(0);
			}
		return null;
	}
	/**
         * 根据随访方案id获取药物不良反应的列表
         * @param visitRecordUuid
         * @return
         */
        @Override
        public List<DrugReactionModel> getAllDrugReactionByVisitRecordUuid(String visitRecordUuid) {
            String hql = new String(
                    " from DrugReactionModel as d where d.visitRecordUuid=:visitRecordUuid ");
            
                    Query query = this.getH4Session().createQuery(hql);
                    query.setString("visitRecordUuid", visitRecordUuid);
                    
                    List<DrugReactionModel> list =query.list();
                    if(list !=null &&list.size()>0){
                                    return list;
                     }
                    return null;
        }

}
