package com.aebiz.b2b2c.cms.tagscategoyrel.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.cms.tagscategoy.vo.TagsCategoryModel;
import com.aebiz.b2b2c.cms.tagscategoyrel.vo.TagsCategoyRelModel;
import com.aebiz.b2b2c.cms.tagscategoyrel.vo.TagsCategoyRelQueryModel;

@Repository
public class TagsCategoyRelH4Impl extends
		BaseH4Impl<TagsCategoyRelModel, TagsCategoyRelQueryModel> implements
		TagsCategoyRelDAO {

	@Override
	protected String getMultiSelect() {
		return ",tc,t ";
	}

	@Override
	protected String getMultiModel() {
		return " ,TagsCategoryModel tc,TagsModel t ";
	}

	@Override
	protected String getAppendHql(TagsCategoyRelQueryModel qm) {
		String hql = " and o.categoryUuid=tc.uuid  and o.tagUuid = t.uuid ";
		if (qm != null) {
			// 凭借hql的where部分
			hql += this.appHql(qm, hql);
			// 拼接hql的order 部分
			hql += this.appOrderBy(qm, hql);
		} else {
			hql += "order by o.createTime desc ";
		}
		return hql;
	}

	/**
	 * 如有其他子表，拼接hql的其他表的属性
	 * 
	 * @param @param qm
	 * @param @param hql
	 * @param @return
	 * @return String
	 * 
	 */
	private String appHql(TagsCategoyRelQueryModel qm, String hql) {
		if (!StringUtil.isEmpty(qm.getCategoryName())) {
			hql += " and tc.categoryName =:categoryName ";
		}
		if (!StringUtil.isEmpty(qm.getTagName())) {
			hql += " and t.tagName like:tagName ";
		}
		return hql;
	}

	/**
	 * 拼接order By
	 * 
	 * @param @param qm
	 * @param @param hql
	 * @param @return
	 * @return String
	 * 
	 */
	private String appOrderBy(TagsCategoyRelQueryModel qm, String hql) {
		if ("categoryName".equals(qm.getSortName())) {
			hql += "order by tc.categoryName " + qm.getSortType();
		} else if ("tagName".equals(qm.getSortName())) {
			hql += "order by t.tagName " + qm.getSortType();
		} else if ("position".equals(qm.getSortName())) {
			hql += "order by o.position " + qm.getSortType();
		} else {
			hql += "order by o.position asc ";
		}
		return hql;
	}

	@Override
	protected void setAppendHqlValue(TagsCategoyRelQueryModel qm, Query q) {

		if (!StringUtil.isEmpty(qm.getCategoryName())) {
			q.setParameter("categoryName", qm.getCategoryName());
		}

		if (!StringUtil.isEmpty(qm.getTagName())) {
			q.setParameter("tagName", "%" + qm.getTagName() + "%");
		}
	}

	/**
	 * 组合数据成model对象重写方法
	 * 
	 * @param tempList
	 *            hql查询的数组集合
	 */
	@Override
	public List<TagsCategoyRelModel> exeResultList(List<Object[]> tempList) {
		List<TagsCategoyRelModel> list = new ArrayList<TagsCategoyRelModel>();
		if (tempList != null && tempList.size() > 0) {
			for (Object[] obj : tempList) {
				// 标签分类关系
				TagsCategoyRelModel tcRel = new TagsCategoyRelModel();
				tcRel = (TagsCategoyRelModel) obj[0];
				// 标签分类
				TagsCategoryModel tc = new TagsCategoryModel();
				tc = (TagsCategoryModel) obj[1];
				// 标签
				TagsModel t = new TagsModel();
				t = (TagsModel) obj[2];

				tcRel.setCategoryName(tc.getCategoryName());
				tcRel.setTagName(t.getTagName());
				list.add(tcRel);
			}
		}
		return list;
	}

	/**
	 * 根据标签分类uuid查找已关联标签
	 * 
	 * @param categoryUuid
	 *            标签分类uuid
	 * @return
	 */
	public List<String> getRelByCategoryUuid(String categoryUuid) {
		if (StringUtil.isEmpty(categoryUuid)) {
			return null;
		}
		String hql = "select o.tagUuid from TagsCategoyRelModel o where o.categoryUuid=:categoryUuid";

		Query query = this.getH4Session().createQuery(hql);
		query.setString("categoryUuid", categoryUuid);

		List<String> list = query.list();

		return list;
	}

	/**
	 * 根据标签分类uuid删除,标签和标签分类关系表数据
	 * 
	 * @param categoryUuids
	 *            标签分类uuid
	 * @return
	 */
	public void deletesByCategoryUuid(List<String> categoryUuids) {
		String hql = "delete from TagsCategoyRelModel o where o.categoryUuid in (:categoryUuid)";
		Map mapParams = new HashMap();
		mapParams.put("categoryUuid", categoryUuids.toArray());
		exeUpdate(hql, mapParams);

	}

	/**
	 * 根据标签uuid删除,标签和标签分类关系表数据
	 * 
	 * @param tagUuids
	 *            标签uuid
	 * @return
	 */
	public void deletesBytagUuid(List<String> tagUuids) {
		String hql = "delete from TagsCategoyRelModel o where o.tagUuid in (:tagUuid)";
		Map mapParams = new HashMap();
		mapParams.put("tagUuid", tagUuids.toArray());
		exeUpdate(hql, mapParams);

	}
}
