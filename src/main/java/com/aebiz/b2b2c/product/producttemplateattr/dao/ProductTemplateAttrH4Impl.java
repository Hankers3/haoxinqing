package com.aebiz.b2b2c.product.producttemplateattr.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrModel;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrQueryModel;
import com.aebiz.b2b2c.product.producttemplateattrrel.vo.ProductTemplateAttrRelModel;

/**
 * 
 * 商品模板属性数据操作
 * 
 * 
 * @author huangpinpin
 *
 */
@Repository
public class ProductTemplateAttrH4Impl extends BaseH4Impl<ProductTemplateAttrModel,ProductTemplateAttrQueryModel> implements ProductTemplateAttrDAO {
	
	/**
	 * 拼接sql语句，包括查询条件
	 */
	@Override
	protected String getAppendHql(ProductTemplateAttrQueryModel qm) {
		StringBuffer hql = new StringBuffer(" ");
		if(qm !=null){
			//凭借hql的where部分
			if(qm!=null && qm.getAttributeUuids() != null && qm.getAttributeUuids().size()>0){
				List<String> attributeUuids = qm.getAttributeUuids();
				hql.append(" and o.uuid not in (");
				for(int i=0;i<attributeUuids.size();i++){
					hql.append("'"+attributeUuids.get(i)+"'");
					if(i!=attributeUuids.size()-1){
						hql.append(",");
					}
				}
				hql.append(") ");
			}
			if (!StringUtil.isEmpty(qm.getAttributeName())) {
				hql.append(" and o.attributeName like:attributeName ");
			}
			//拼接hql的order 部分
			hql.append(super.getAppendHql(qm));
		}else{
			//拼接hql的order 部分
			hql.append(super.getAppendHql(qm));
		}
		return hql.toString();
	}
	
	/**
	 * 设置查询条件值
	 */
	@Override
	protected void setAppendHqlValue(ProductTemplateAttrQueryModel qm, Query q) {
		if(!StringUtil.isEmpty(qm.getAttributeName())){
			q.setParameter("attributeName", "%"+qm.getAttributeName()+"%");
		}
	}
	
	/**
	 * 根据模板uuids,获取属性
	 * @param templateUuids
	 * @return
	 */
	public List<ProductTemplateAttrModel> getByUuids(List<String> uuids){
		if(uuids==null || uuids.size()==0){
			return null;
		}
		String hql="select o from ProductTemplateAttrModel o where  o.uuid in(:uuids)";
		
		Query query = this.getH4Session().createQuery(hql);
		query.setParameterList("uuid", uuids);
		
		List<ProductTemplateAttrModel> list = query.list();
		return list;	
	}
	
}
