package com.aebiz.b2b2c.product.producttemplateattrrel.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateModel;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrModel;
import com.aebiz.b2b2c.product.producttemplateattrrel.vo.ProductTemplateAttrRelModel;
import com.aebiz.b2b2c.product.producttemplateattrrel.vo.ProductTemplateAttrRelQueryModel;

/**
 * 
 * 商品模板和属性关系数据操作
 * 
 * 一个模板可以关联多个属性，比如模板里面有颜色/尺寸等属性
 * 
 * @author huangpinpin
 *
 */
@Repository
public class ProductTemplateAttrRelH4Impl extends BaseH4Impl<ProductTemplateAttrRelModel,ProductTemplateAttrRelQueryModel> implements ProductTemplateAttrRelDAO {
	@Override
	protected String getMultiSelect() {
		return ",pt,pta ";
	}
	@Override
	protected String getMultiModel() {
		return " ,ProductTemplateModel pt,ProductTemplateAttrModel pta ";
	}
	/**
	 * 拼接sql语句
	 */
	@Override
	protected String getAppendHql(ProductTemplateAttrRelQueryModel qm) {
		StringBuffer hql = new StringBuffer(" and o.templateUuid=pt.uuid  and o.attributeUuid = pta.uuid ");
		if(qm !=null){
			//凭借hql的where部分
			if (!StringUtil.isEmpty(qm.getAttributeName())) {
				hql.append(" and pta.attributeName like:attributeName ");
			}
			//拼接hql的order 部分
			if("attributeName".equals(qm.getSortName())){
				hql.append(" order by pta.attributeName " + qm.getSortType());
			}else if("position".equals(qm.getSortName())){
				hql.append(" order by o.position " + qm.getSortType());
			}else{
				hql.append(" order by o.position asc ");
			}
		}else{
			hql.append( " order by o.position desc ");
		}
		return hql.toString();
	}
	
	@Override
	protected void setAppendHqlValue(ProductTemplateAttrRelQueryModel qm, Query q) {
		if(!StringUtil.isEmpty(qm.getAttributeName())){
			q.setParameter("attributeName","%"+qm.getAttributeName()+"%");
		}
	}
	/**
	 * 组合数据成model对象重写方法
	 * @param tempList hql查询的数组集合
	 */
	public List<ProductTemplateAttrRelModel> exeResultList(List<Object[]> tempList){
		List<ProductTemplateAttrRelModel> list = new ArrayList<ProductTemplateAttrRelModel>();
		if(tempList != null && tempList.size()>0){
			for(Object[] obj:tempList){
				ProductTemplateAttrRelModel rel = new ProductTemplateAttrRelModel();
				
				rel = (ProductTemplateAttrRelModel)obj[0];
				ProductTemplateModel  pt=new ProductTemplateModel();
				pt =(ProductTemplateModel)obj[1];
				
				ProductTemplateAttrModel pta=new ProductTemplateAttrModel();
				pta=(ProductTemplateAttrModel)obj[2];
				
				rel.setTemplateName(pt.getName());
				rel.setAttributeName(pta.getAttributeName());
				
				list.add(rel);
			}
		}
		return list;
	}
	
	
	/**
	 * 根据模板uuid查找已经关联该模板的属性uuid
	 * 用于过滤已经关联该模板属性的列表查询
	 * @param templateUuid 模板uuid
	 * @return
	 */
	public List<String> getAttributeUuidsByUuid(String templateUuid){
		if(StringUtil.isEmpty(templateUuid)){
			return null;
		}
		String hql="select o.attributeUuid from ProductTemplateAttrRelModel o where o.templateUuid=:templateUuid";
		
		Query query = this.getH4Session().createQuery(hql);
		query.setString("templateUuid", templateUuid);
		
		List<String> list = query.list();
		
		return list;
	}
	
	
	/**
	 * 根据模板uuid查找已经关联该模板的属性集合
	 * @param templateUuid 模板
	 * @return
	 */
	public List<ProductTemplateAttrRelModel> getAttributeByUuid(String templateUuid){
		if(StringUtil.isEmpty(templateUuid)){
			return null;
		}
		String hql="select o from ProductTemplateAttrRelModel o where  o.templateUuid =:templateUuid order by o.position ";
		
		Query query = this.getH4Session().createQuery(hql);
		query.setString("templateUuid", templateUuid);
		
		List<ProductTemplateAttrRelModel> list = query.list();
		return list;	
	}
	
	/**
	 * 根据模板uuid查找规格属性集合
	 * @param templateUuid 模板
	 * @return
	 */
	public List<ProductTemplateAttrRelModel> getSpecAttributeUuidsByUuid(String templateUuid){
		if(StringUtil.isEmpty(templateUuid)){
			return null;
		}
		String hql="select o  from ProductTemplateAttrRelModel o where o.templateUuid=:templateUuid and o.canAttribute=:canAttribute order by o.position ";
		
		Query query = this.getH4Session().createQuery(hql);
		query.setString("templateUuid", templateUuid);
		query.setString("canAttribute", ProductTemplateAttrRelModel.ATTRIBUTE_YES);
		
		List<ProductTemplateAttrRelModel> list = query.list();
		return list;
	}
	
	/**
	 * 根据模板uuid查找不是规格属性集合
	 * @param templateUuid 模板
	 * @return
	 */
	public List<ProductTemplateAttrRelModel> getNotSpecAttributeUuidsByUuid(String templateUuid){
		if(StringUtil.isEmpty(templateUuid)){
			return null;
		}
		String hql="select o from ProductTemplateAttrRelModel o where o.templateUuid=:templateUuid and o.canAttribute=:canAttribute order by o.position ";
		
		Query query = this.getH4Session().createQuery(hql);
		query.setString("templateUuid", templateUuid);
		query.setString("canAttribute", ProductTemplateAttrRelModel.ATTRIBUTE_NO);
		
		List<ProductTemplateAttrRelModel> list = query.list();
		return list;
	}
}
