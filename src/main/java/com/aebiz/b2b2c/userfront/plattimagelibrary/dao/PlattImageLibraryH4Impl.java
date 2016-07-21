package com.aebiz.b2b2c.userfront.plattimagelibrary.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.userfront.plattimagelibrary.vo.PlattImageLibraryModel;
import com.aebiz.b2b2c.userfront.plattimagelibrary.vo.PlattImageLibraryQueryModel;

@Repository
public class PlattImageLibraryH4Impl extends
		BaseH4Impl<PlattImageLibraryModel, PlattImageLibraryQueryModel>
		implements PlattImageLibraryDAO {

	/**
	 * 根据分类的uuid获取该分类下的所有图片
	 * 
	 * @param categoryUuid
	 * @return List<PlattImageLibraryModel>
	 */
	public List<PlattImageLibraryModel> getPlattImageLibraryModelsByCategoryUuid(
			String categoryUuid, int nowPage, int pageShow) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from PlattImageLibraryModel  p where p.categoryUuid =:categoryUuid order by p.upLoadTime desc");

		Query query = this.getH4Session().createQuery(hql.toString());

		query.setFirstResult(nowPage);
		query.setMaxResults(pageShow);

		query.setString("categoryUuid", categoryUuid);

		return (List<PlattImageLibraryModel>) query.list();
	}

	/**
	 * 根据分类的uuid获取该分类下的所有图片总数
	 * 
	 * @param categoryUuid
	 * @return List<PlattImageLibraryModel>
	 */
	public int getPlattImageLibraryModelsByCategoryUuid(String categoryUuid) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from PlattImageLibraryModel  p where p.categoryUuid =:categoryUuid order by p.upLoadTime desc ");

		Query query = this.getH4Session().createQuery(hql.toString());

		query.setString("categoryUuid", categoryUuid);
		List<PlattImageLibraryModel> list = (List<PlattImageLibraryModel>) query
				.list();
		if (list != null && list.size() > 0) {
			return list.size();
		}

		return 0;
	}

	/**
	 * 根据分类的uuid获取该分类下的所有图片
	 * 
	 * @param categoryUuid
	 * @return List<PlattImageLibraryModel>
	 */
	public List<PlattImageLibraryModel> getPlattImageLibraryModelListByCategoryUuid(
			String categoryUuid) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from PlattImageLibraryModel  p where p.categoryUuid =:categoryUuid order by p.upLoadTime desc");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("categoryUuid", categoryUuid);
		List<PlattImageLibraryModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
