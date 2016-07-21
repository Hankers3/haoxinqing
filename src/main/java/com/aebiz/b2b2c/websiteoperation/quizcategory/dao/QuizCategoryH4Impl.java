package com.aebiz.b2b2c.websiteoperation.quizcategory.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryModel;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryQueryModel;

@Repository
public class QuizCategoryH4Impl extends BaseH4Impl<QuizCategoryModel,QuizCategoryQueryModel> implements QuizCategoryDAO {
	/**
	 * 判断分类名 是否存在
	 * @param categoryName
	 * @return
	 */
	@Override
	public String checkCateGoryName(String categoryName) {
		StringBuffer hql = new StringBuffer(" select o.uuid  from QuizCategoryModel  as o ");
		hql.append(" where o.categoryName =:categoryName ");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("categoryName", categoryName);
		
		List  list =q.list();
		if(list !=null && list.size()>0){
			return "1";
		}
		return "0";
	}
	
	/**
	 * 根据uuid获取分类名
	 * @param uuid
	 * @return
	 */
	public String  getCategoryName(String uuid){
		if(StringUtil.isEmpty(uuid)){
			return "";
		}
		
		StringBuffer hql = new StringBuffer(" select o.categoryName from QuizCategoryModel  as o  ");
		hql.append(" where o.uuid =:uuid ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("uuid", uuid);
		List  list =q.list();
		if(list !=null && list.size()>0){
			return (String) list.get(0);
		}
		return "";
	}
	
	/**
	 * 获取所有展示的分类
	 * @return
	 */
	@Override
	public List<QuizCategoryModel> getQuizCategoryModels() {
		StringBuffer hql = new StringBuffer(" select o from QuizCategoryModel  as o  ");
		hql.append(" where o.state =:state ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("state", "1");
		List<QuizCategoryModel>  list = q.list();
		if(list !=null && list.size()>0){
			return list;
		}
		
		return null;
	}
	/**
	 * 获取所有展示的分类Uuid
	 * @return
	 */
	@Override
	public List<String> getQuizCategoryModelUuids() {
		StringBuffer hql = new StringBuffer(" select o.uuid from QuizCategoryModel  as o  ");
		hql.append(" where o.state =:state ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("state", "1");
		List<String>  list = q.list();
		if(list !=null && list.size()>0){
			return list;
		}
		return null;
	}
}
