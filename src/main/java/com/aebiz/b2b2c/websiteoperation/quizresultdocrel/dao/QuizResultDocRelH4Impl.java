package com.aebiz.b2b2c.websiteoperation.quizresultdocrel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelModel;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelQueryModel;

@Repository
public class QuizResultDocRelH4Impl extends BaseH4Impl<QuizResultDocRelModel,QuizResultDocRelQueryModel> implements QuizResultDocRelDAO {

	@Override
	public List<QuizResultDocRelModel> getListByQuizCategoryUuid(
			String quizResultId) {
		StringBuffer hql = new StringBuffer("from QuizResultDocRelModel q where q.quizResultUuid=:quizResultId and q.delFlag='1' order by q.createTime");
		Query query = getH4Session().createQuery(hql.toString());
		query.setParameter("quizResultId",quizResultId);
		return query.list();
	}

	@Override
	public List<QuizResultDocRelModel> getListByserviceStaffInfoIdAndquizResultId(
			String serviceStaffInfoId, String quizResultId) {
		StringBuffer hql = new StringBuffer("from QuizResultDocRelModel q where q.quizResultUuid=:quizResultId and q.serviceStaffInfoId =:serviceStaffInfoId and q.delFlag='1' order by q.createTime");
		Query query = getH4Session().createQuery(hql.toString());
		query.setParameter("quizResultId",quizResultId);
		query.setParameter("serviceStaffInfoId",serviceStaffInfoId);
		return query.list();
	}

        @Override
        public List<String> getAllQuizResultDocRelModelUuids(String serviceStaffInfoId, String quizResultId) {
            StringBuffer hql = new StringBuffer(
                    "select crm.uuid from QuizResultDocRelModel crm where crm.serviceStaffInfoId=:serviceStaffInfoId and crm.quizResultUuid=:quizResultId ");
                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("serviceStaffInfoId", serviceStaffInfoId);
                query.setString("quizResultId", quizResultId);
                List<String> list = query.list();
                    if (list != null && list.size() > 0) {
                            return list;
                    }
                    return null;
        }
    
        @Override
        public List<String> getAllQuizResultDocRelModelUuidsByQuizCategoryUuid(String quizCategoryUuid) {
            StringBuffer hql = new StringBuffer(
                    "select crm.uuid from QuizResultDocRelModel crm where crm.quizResultUuid=:quizCategoryUuid ");
                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("quizCategoryUuid", quizCategoryUuid);
                List<String> list = query.list();
                    if (list != null && list.size() > 0) {
                            return list;
                    }
                    return null;
        }

}
