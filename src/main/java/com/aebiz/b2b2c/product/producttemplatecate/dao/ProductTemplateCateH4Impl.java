package com.aebiz.b2b2c.product.producttemplatecate.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.producttemplatecate.vo.ProductTemplateCateModel;
import com.aebiz.b2b2c.product.producttemplatecate.vo.ProductTemplateCateQueryModel;

/**
 * 模板分类数据操作
 * 
 * 由于模板会很多，所以给它个分类，方便维护和查找
 * 
 * @author huangpinpin
 *
 */
@Repository
public class ProductTemplateCateH4Impl extends BaseH4Impl<ProductTemplateCateModel,ProductTemplateCateQueryModel> implements ProductTemplateCateDAO {

	/**
	 * 根据uuid获取模板分类名称
	 * @param uuid 分类uuid
	 * @return
	 */
	public String getCategoryNameByUuid(String uuid){
		/* 分类uuid*/
		if(StringUtil.isEmpty(uuid)){
			return "";
		}
		
		StringBuffer hql=new StringBuffer("select o.categoryName from ProductTemplateCateModel o where o.uuid=:uuid");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		
		String categoryName = (String) query.uniqueResult();
		return categoryName;
	}
}
