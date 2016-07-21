package com.aebiz.b2b2c.product.frontproductcategorybrandrel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.vo.FrontProductCategoryBrandRelModel;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.vo.FrontProductCategoryBrandRelQueryModel;

@Repository
public class FrontProductCategoryBrandRelH4Impl
		extends
		BaseH4Impl<FrontProductCategoryBrandRelModel, FrontProductCategoryBrandRelQueryModel>
		implements FrontProductCategoryBrandRelDAO {
	/**
	 * 根据前台分类id获取分类关联的品牌id集合
	 * 
	 * @param cateUuid
	 * @return
	 */
	public List<String> getBrandIdsByCateUuid(String cateUuid) {
		StringBuffer hql = new StringBuffer(
				"select o.brandUuid from FrontProductCategoryBrandRelModel o where o.categoryUuid=:categoryUuid");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("categoryUuid", cateUuid);

		return q.list();
	}

	/**
	 * 根据分类id删除分类和品牌的关联关系
	 * 
	 * @param cateUuid
	 */
	public void deleteByCateUuid(String cateUuid) {
		StringBuffer hql = new StringBuffer(
				"delete from FrontProductCategoryBrandRelModel o where o.categoryUuid=:categoryUuid");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("categoryUuid", cateUuid);

		q.executeUpdate();
	}

	/**
	 * 根据品牌uuid集合和分类uuid,删除关系数据
	 * 
	 * @param brandUuids
	 *            品牌uuid集合
	 * @param categoryUuid
	 *            分类uuid
	 */
	public void deleteByBrandUuidsAndCategoryUuid(List<String> brandUuids,
			String categoryUuid) {
		if (brandUuids == null || brandUuids.size() == 0) {
			return;
		}
		if (StringUtil.isEmpty(categoryUuid)) {
			return;
		}
		String hql = "delete from FrontProductCategoryBrandRelModel o where o.brandUuid in (:uuids) and o.categoryUuid=:categoryUuid ";

		Map<String, Object> mapParams = new HashMap();
		mapParams.put("uuids", brandUuids.toArray());
		mapParams.put("categoryUuid", categoryUuid);

		exeUpdate(hql, mapParams);
	}
}
