package com.aebiz.b2b2c.cms.interactive.content.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.interactive.content.vo.ContentInteractiveModel;
import com.aebiz.b2b2c.cms.interactive.content.vo.ContentInteractiveQueryModel;

@Repository
public class ContentInteractiveH4Impl extends BaseH4Impl<ContentInteractiveModel, ContentInteractiveQueryModel> implements ContentInteractiveDAO {

	/**
	 * 根据qm获取总数
	 */
	public int getCountByContent(ContentInteractiveQueryModel qm) {
		String hql = "select count(*) from ContentModel o  where 1=1 ";
		hql = hql + getAppendHql(qm);

		Query query = this.getH4Session().createQuery(hql);
		setAppendHqlValue(qm, query);

		return ((Number) query.uniqueResult()).intValue();
	}

	/**
	 * 根据qm获取集合
	 */
	public List<ContentInteractiveModel> getByContent(
			ContentInteractiveQueryModel qm, int start, int pageSize) {
		String hql = "select o.uuid,o.contentTitle,o.introduction,o.contentCategoryUuid from ContentModel o where 1=1 ";

		hql = hql + prepareHql(qm);
		hql = hql + getAppendHql(qm);

		Query q = getH4Session().createQuery(hql);
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		
			q.setFirstResult(start);
			q.setMaxResults(pageSize);
	

		List<Object[]> tempList = q.list();
		if (tempList != null && tempList.size() > 0) {
			List<ContentInteractiveModel> list=convert(tempList);
			return list;
		}
		return null;
	}
	
	protected String getAppendHql(ContentInteractiveQueryModel qm) {
		StringBuffer hql = new StringBuffer("");
		if (qm != null) {
			// 内容标题
			if (!StringUtil.isEmpty(qm.getContentTitle())) {
				hql.append(" and o.contentTitle like :contentTitle");
			}
			// 内容分类uuid
			if (!StringUtil.isEmpty(qm.getContentCategoryUuid())) {
				hql.append(" and o.contentCategoryUuid =:contentCategoryUuid ");
			}
			//uuid集合
			if(qm.getInContentUuids()!=null && qm.getInContentUuids().length>0){
				hql.append(" and o.uuid in (:uuids) ");
			}
		}
		// 排序
		return hql.append(" order by o.opeTime desc ").toString();
	}
	
	/**
	 * 设置查询条件
	 */
	protected void setAppendHqlValue(ContentInteractiveQueryModel qm, Query q) {
		if (qm != null) {
			// 内容标题
			if (!StringUtil.isEmpty(qm.getContentTitle())) {
				q.setString("contentTitle", "%"+qm.getContentTitle()+"%");
			}
			// 内容分类uuid
			if (!StringUtil.isEmpty(qm.getContentCategoryUuid())) {
				q.setString("contentCategoryUuid",qm.getContentCategoryUuid() );
			}
			//uuid集合
			if(qm.getInContentUuids()!=null && qm.getInContentUuids().length>0){
				q.setParameterList("uuids", qm.getInContentUuids());
			}
		}
	}
	
	/**
	 * 根据uuid集合查询内容集合
	 * @param contentUuids
	 * @return
	 */
	public List<ContentInteractiveModel> getByUuids(String[] contentUuids){
		StringBuffer hql=new StringBuffer("select o.uuid,o.contentTitle,o.introduction,o.contentCategoryUuid from ContentModel o where 1=1 ");
		hql.append(" and o.uuid in (:uuids) ");
		hql.append(" order by o.opeTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setParameterList("uuids", contentUuids);
		List<Object[]> list=query.list();
		if(list!=null && list.size()>0){
			List<ContentInteractiveModel> temList=convert(list);
			return temList;
		}
		return null;
	}
	
	/**
	 * 转换类型成对外model
	 * 
	 * @param tempList
	 * @return
	 */
	public List<ContentInteractiveModel> convert(List<Object[]> tempList) {
		List<ContentInteractiveModel> list = new ArrayList<ContentInteractiveModel>();
		if (tempList != null && tempList.size() > 0) {
			for (Object[] obj : tempList) {
				String uuid = (String) obj[0];
				String contentTitle = (String) obj[1];
				String introduction = (String) obj[2];
				String contentCategoryUuid = (String) obj[3];
			
				ContentInteractiveModel m=new ContentInteractiveModel();
				m.setUuid(uuid);
				m.setContentTitle(contentTitle);
				m.setIntroduction(introduction);
				m.setContentCategoryUuid(contentCategoryUuid);
				list.add(m);
			}
		}
		return list;
	}
}
