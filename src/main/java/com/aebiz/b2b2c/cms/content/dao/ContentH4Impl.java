package com.aebiz.b2b2c.cms.content.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.content.vo.ContentQueryModel;

@Repository
public class ContentH4Impl extends BaseH4Impl<ContentModel, ContentQueryModel> implements ContentDAO {

	/**
	 * 批量更改内容发布状态
	 * 
	 * @param needUpdateUuids
	 * @param state
	 */
	@Override
	public void updates(List<String> needUpdateUuids, String state) {

		String hql = "update  ContentModel o set o.state =:state  where o.uuid in (:uuids)";

		Map<String, Object> mapParams = new HashMap();
		mapParams.put("uuids", needUpdateUuids.toArray());
		mapParams.put("state", state);
		exeUpdate(hql, mapParams);

	}

	/**
	 * 获取会员分享内容的数据
	 * 
	 * @return
	 */
	@Override
	public ContentModel getShareContent(String contentCategoryUuid) {

		StringBuffer hql = new StringBuffer(
				"from ContentModel cm where cm.contentCategoryUuid=:contentCategoryUuid order by cm.createTime desc ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("contentCategoryUuid", contentCategoryUuid);// 待定
		List<ContentModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据contentCategoryUuid获取ContentModel集合
	 * 
	 * @param contentCategoryUuid
	 * @return
	 */
	@Override
	public List<ContentModel> getContentModelByCategoryUuid(String contentCategoryUuid) {
		StringBuffer hql = new StringBuffer(
				"from ContentModel cm where cm.contentCategoryUuid=:contentCategoryUuid order by cm.createTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("contentCategoryUuid", contentCategoryUuid);
		List<ContentModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	// 重写父类方法拼接sql语句
	@Override
	protected String getMultiModel() {

		return " , ContentCategoryModel as c  ";
	}

	@Override
	protected String getAppendHql(ContentQueryModel qm) {
		StringBuffer sb = new StringBuffer(" and o.contentCategoryUuid = c.uuid ");

		if (!StringUtil.isEmpty(qm.getCategoryTypeq())) {
			// 内容类型
			sb.append(" and c.categoryType=:categoryType");
		}
		
		if (qm != null) {
			 sb.append(" order by o.").append(qm.getSortName()).append(" ").append(qm.getSortType());
		}else {
			 sb.append(" order by o.opeTime desc ");
		}
		
		return sb.toString();
	}

	@Override
	protected void setAppendHqlValue(ContentQueryModel qm, Query query) {
		if (!StringUtil.isEmpty(qm.getCategoryTypeq())) {
			query.setString("categoryType", qm.getCategoryTypeq());
		}

	}

	/**
	 * 根据contentCategoryUuid获取文章
	 * 
	 * @param contentCategoryUuid
	 * @return
	 */
	@Override
	public List<ContentModel> getContentListByCategoryUuid(String contentCategoryUuid) {
		StringBuffer hql = new StringBuffer(
				"from ContentModel cm where cm.contentCategoryUuid=:contentCategoryUuid and cm.state='1' order by cm.createTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("contentCategoryUuid", contentCategoryUuid);
		List<ContentModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 根据患教分类获得患教信息
	 * 
	 * @param categoryId
	 */
	@Override
	public List<ContentModel> getCustomerTeachList(String categoryId) {
		StringBuffer hql = new StringBuffer(
				"from ContentModel cm where cm.contentCategoryUuid=:contentCategoryUuid and  cm.state='1'  order by cm.createTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("contentCategoryUuid", categoryId);
		List<ContentModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * app端搜索资讯信息
	 * 
	 * @param contentCategoryUuid
	 * @param qm
	 * @return
	 */

	public List<ContentModel> getByContentCategoryUuid(String contentCategoryUuid, ContentQueryModel qm) {

		StringBuffer hql = new StringBuffer("from ContentModel cm where cm.state='1' ");

		// 分类编号
		if (!StringUtil.isEmpty(contentCategoryUuid)) {
			hql.append(" and  cm.contentCategoryUuid=:contentCategoryUuid");
		}
		// 内容名称
		if (!StringUtil.isEmpty(qm.getAppContentName())) {
			hql.append(" and  cm.contentTitle like:contentTitle");
		}
		// 疾病编号
		if (!StringUtil.isEmpty(qm.getAppIllnessId()) && !"0".equals(qm.getAppIllnessId())) {
			hql.append(" and  cm.illnessId =:illnessId");
		}
		// 症状编号
		if (!StringUtil.isEmpty(qm.getAppSymptomId()) && !"0".equals(qm.getAppSymptomId())) {
			hql.append(" and  cm.symptomId =:symptomId");
		}
		// 病种
		if (!StringUtil.isEmpty(qm.getAppEntity()) && !"0".equals(qm.getAppEntity())) {
			hql.append(" and  cm.entity like:entity");
		}
		// 作者
		if (!StringUtil.isEmpty(qm.getAppAuthor()) && !"0".equals(qm.getAppAuthor())) {
			hql.append(" and  cm.author =:author");
		}
		// 国家
		if (!StringUtil.isEmpty(qm.getCountry()) && !"0".equals(qm.getCountry())) {
			hql.append(" and  cm.country =:country");
		}
		hql.append(" order by cm.createTime desc");

		Query q = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(contentCategoryUuid)) {
			q.setString("contentCategoryUuid", contentCategoryUuid);
		}
		// 内容名称
		if (!StringUtil.isEmpty(qm.getAppContentName())) {
			q.setString("contentTitle", "%" + qm.getAppContentName() + "%");
		}

		// 疾病编号
		if (!StringUtil.isEmpty(qm.getAppIllnessId()) && !"0".equals(qm.getAppIllnessId())) {
			q.setString("illnessId", qm.getAppIllnessId());
		}
		// 症状编号
		if (!StringUtil.isEmpty(qm.getAppSymptomId()) && !"0".equals(qm.getAppSymptomId())) {
			q.setString("symptomId", qm.getAppSymptomId());
		}
		// 病种
		if (!StringUtil.isEmpty(qm.getAppEntity()) && !"0".equals(qm.getAppEntity())) {
			q.setString("entity", "%" + qm.getAppEntity() + "%");
		}
		// 作者
		if (!StringUtil.isEmpty(qm.getAppAuthor()) && !"0".equals(qm.getAppAuthor())) {
			q.setString("author", qm.getAppAuthor());
		}

		// 国家
		if (!StringUtil.isEmpty(qm.getCountry()) && !"0".equals(qm.getCountry())) {
			q.setString("country", qm.getCountry());
		}

		List<ContentModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}

		return null;

	}

	/**
	 * 根据分类UUID获取内容uuid集合
	 */
	@Override
	public List<String> getContentUuidsByCategoryUuid(String categoryUuid) {
		String hql = "select c.uuid from ContentModel c where c.contentCategoryUuid=:contentCategoryUuid and  c.state='1' order by c.createTime desc";

		Query query = this.getH4Session().createQuery(hql);
		query.setString("contentCategoryUuid", categoryUuid);
		return query.list();
	}

	/**
	 * 
	 * @Description: (返回咨询的list)
	 * @author XP
	 * @return
	 * @date 2015-12-27 上午11:24:51
	 */
	@Override
	public List<ContentModel> getAllContentModelList() {
		StringBuffer hql = new StringBuffer(
				"from ContentModel cm where 1=1 and  cm.state='1'  order by cm.createTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		List<ContentModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
