package com.aebiz.b2b2c.websiteoperation.quizresult.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultQueryModel;

@Repository
public class QuizResultH4Impl extends BaseH4Impl<QuizResultModel,QuizResultQueryModel> implements QuizResultDAO {
	/**
	 * 根据测试分类ID获取测试分类结果
	 * @return
	 * @2015-11-20
	 * @author :SZH
	 */
	@Override
	public List<QuizResultModel> getListByQuizCategoryUuid(String quizCategoryUuid) {
		if(StringUtil.isEmpty(quizCategoryUuid)){
			return null;
		}
		StringBuffer hql = new StringBuffer("from QuizResultModel qrm where qrm.quizCategoryUuid =:quizCategoryUuid and qrm.delFlag='1' order by qrm.startScore");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("quizCategoryUuid", quizCategoryUuid);
		return query.list();
	}

        @Override
        public List<String> getAllQuizCategoryModelUuids(String quizCategoryUuid) {
            StringBuffer hql = new StringBuffer(
                    "select crm.uuid from QuizResultModel crm where crm.quizCategoryUuid=:quizCategoryUuid ");
                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("quizCategoryUuid", quizCategoryUuid);
                List<String> list = query.list();
                if (list != null && list.size() > 0) {
                        return list;
                }
                return null;
        }
    	/**
    	 * 
    	 * 根据测试分类ID和分数 获取测试分类结果
    	 * @param quizCategoryUuid
    	 * @param score
    	 * @return
    	 */
		@Override
		public QuizResultModel getByQuizCategoryUuid(String quizCategoryUuid,
				String score) {
			if(StringUtil.isEmpty(quizCategoryUuid)){
				return null;
			}
			
			StringBuffer hql = new StringBuffer("from QuizResultModel qrm where qrm.quizCategoryUuid =:quizCategoryUuid and qrm.delFlag='1' ");
			hql.append(" and qrm.startScore<=:score");
			hql.append(" and qrm.endScore>:score");
           
			Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("quizCategoryUuid", quizCategoryUuid);
            query.setString("score", score);
            List<QuizResultModel> list = query.list();
            if (list != null && list.size() > 0) {
            	return list.get(0);
            }
			return null;
		}

}
