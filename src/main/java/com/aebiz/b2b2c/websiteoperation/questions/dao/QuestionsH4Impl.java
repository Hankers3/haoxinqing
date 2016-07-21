package com.aebiz.b2b2c.websiteoperation.questions.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsModel;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsQueryModel;

@Repository
public class QuestionsH4Impl extends
		BaseH4Impl<QuestionsModel, QuestionsQueryModel> implements QuestionsDAO {
	
	/**
	 * 拼接sql语句
	 */
	@Override
	protected String getAppendHql(QuestionsQueryModel qm) {
		return " order by o.createTime asc ";
	}
	
	
	
	/**
	 * 通过试卷的Uuid获得所有试题的列表
	 * 
	 * @param questionnaireUuid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionsModel> getQuestionsByQuestionnaireUuid(
			String questionnaireUuid) {
		StringBuffer hql = new StringBuffer(
				" select q,qr.position from QuestionsModel q , QuestionsRelModel qr where q.uuid = qr.questionUuid and qr.questionnaireUuid =:questionnaireUuid order by qr.position ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("questionnaireUuid", questionnaireUuid);

		List<Object[]> tempList = query.list();

		return convertObjToModel(tempList);

	}

	public List<QuestionsModel> convertObjToModel(List<Object[]> tempList) {
		List<QuestionsModel> qmList = new ArrayList<QuestionsModel>();
		if (tempList != null && tempList.size() > 0) {
			for (Object[] obj : tempList) {
				QuestionsModel qm = new QuestionsModel();
				qm = (QuestionsModel) obj[0];
				qm.setPosition(Integer.parseInt(String.valueOf(obj[1])));
				qmList.add(qm);
			}
		}
		return qmList;
	}

	/**
	 * 查询出已经激活且没有被选中或者没有被关联试题的数量
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int getQuestionsCountByNotSelected(String[] choosedIds) {
		StringBuffer hql = new StringBuffer(
				" from QuestionsModel qsm where qsm.state=:state ");
		if (choosedIds != null && choosedIds.length > 0) {
			hql.append(" and qsm.uuid not in (:uuid) ");
		}

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("state", QuestionsModel.STARTUSING);

		if (choosedIds != null && choosedIds.length > 0) {
			query.setParameterList("uuid", choosedIds);
		}

		Object obj = query.list();

		return obj != null ? ((List<QuestionsModel>) obj).size() : null;
	}

	/**
	 * 得到没有被选中的questions分三种情况：<br>
	 * 1. 添加问卷的时候，第一次点击选择试题，这个时候还没有试题被选中，需要将已经启用的试题全部展示出来<br>
	 * 2. 添加问卷的时候，第二次点击选择试题，可能已经有试题在第一次点击选择试题的时候被选中，这个是否需要将已经选择的试题排除在外，展示其他的试题<br/>
	 * 3. 在编辑问卷的是否，同样需要将已经选择的试题排除在外<br>
	 * 
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param choosedQuestionIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionsModel> getQuestionsByNotSelected(int iDisplayStart,
			int iDisplayLength, String[] choosedIds) {
		StringBuffer hql = new StringBuffer(
				" from QuestionsModel qsm where qsm.state=:state ");
		if (choosedIds != null && choosedIds.length > 0) {
			hql.append(" and qsm.uuid not in (:uuid) ");
		}

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("state", QuestionsModel.STARTUSING);

		if (choosedIds != null && choosedIds.length > 0) {
			query.setParameterList("uuid", choosedIds);
		}

		// 分页
		query.setFirstResult(iDisplayStart);
		query.setMaxResults(iDisplayLength);

		Object obj = query.list();

		return obj != null ? (List<QuestionsModel>) obj : null;
	}

	/**
	 * 通过测试分类得到题
	 * 
	 * @param quizCategoryUuid
	 * @return
	 */
	@Override
	public List<QuestionsModel> getQuestionsByQuizCategoryUuid(
			String quizCategoryUuid,String state) {
		StringBuffer hql = new StringBuffer(
				" from QuestionsModel qsm where qsm.quizCategoryUuid=:quizCategoryUuid and qsm.state=:state order by qsm.createTime asc ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("state", QuestionsModel.STARTUSING);

		query.setString("quizCategoryUuid", quizCategoryUuid);

		List<QuestionsModel> list = query.list();
		 if (list != null && list.size() > 0) {
             return list;
         }
         return null;
	}

	/**
     * 根据试卷的uuid获取questions的所有的uuids
     * @param questionnaireUuid
     * @return
     */
    @Override
    public List<String> getAllQuestionsModelUuids(String quizUuid) {
        StringBuffer hql = new StringBuffer(
                "select crm.uuid from QuestionsModel crm where crm.quizUuid=:quizUuid order by crm.createTime asc");
            Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("quizUuid", quizUuid);
            List<String> list = query.list();
            if (list != null && list.size() > 0) {
                    return list;
            }
            return null;
    }
    /**
     * 根据测试分类的id和测试分类的状态获取questions的所有的uuids
     * @param quizCategoryUuid
     * @param state
     * @return
     */
    @Override
    public List<String> getAllQuestionsModelUuidsByQuizCategoryUuidAndState(String quizCategoryUuid,
            String state) {
        StringBuffer hql = new StringBuffer(
                "select crm.uuid from QuestionsModel crm where crm.quizCategoryUuid=:quizCategoryUuid and crm.state=:state  order by crm.createTime asc ");
            Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("quizCategoryUuid", quizCategoryUuid);
            query.setString("state", state);
            List<String> list = query.list();
            if (list != null && list.size() > 0) {
                    return list;
            }
            return null;
    }

}
