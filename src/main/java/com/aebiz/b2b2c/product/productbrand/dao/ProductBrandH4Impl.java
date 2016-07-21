package com.aebiz.b2b2c.product.productbrand.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandModel;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandQueryModel;

/**
 * 商品品牌数据操作
 * 
 * @author huangpinpin
 *
 */
@Repository
public class ProductBrandH4Impl extends BaseH4Impl<ProductBrandModel,ProductBrandQueryModel> implements ProductBrandDAO {
	/**
	 * 拼接sql语句
	 */
	@Override
	protected String getAppendHql(ProductBrandQueryModel qm) {
		StringBuffer hql = new StringBuffer(" ");
		if(qm !=null){
			//凭借hql的where部分
			if (!StringUtil.isEmpty(qm.getParentUuid())) {
				hql.append(" and o.parentUuid =:parentUuid ");
			}else{
				hql.append(" and o.parentUuid ='' ");
			}
			if(qm.SEARCH_IN.equals(qm.getSearchType())&&qm.getBrandUuids()!=null && qm.getBrandUuids().size()>0){
				hql.append(" and o.uuid in (:brandUuids) ");
			}
			
			if(qm.SEARCH_IN.equals(qm.getSearchType())&&(qm.getBrandUuids()==null || qm.getBrandUuids().size()==0)){
				hql.append(" and 1!=1 ");
			}
			
			if(qm.SEARCH_NOT_IN.equals(qm.getSearchType())&&qm.getBrandUuids()!=null && qm.getBrandUuids().size()>0){
				hql.append(" and o.uuid not in (:brandUuids) ");
			}
			if(!StringUtil.isEmpty(qm.getLetter())&&!"in".equals(qm.getLetter())&&!"not".equals(qm.getLetter())){
				hql.append(" and o.brandEnName like:letter ");
			}
			
			//拼接hql的order 部分
			hql.append(super.getAppendHql(qm));
		}else{
			hql.append(super.getAppendHql(qm));
		}
		return hql.toString();
	}
	
	@Override
	protected void setAppendHqlValue(ProductBrandQueryModel qm, Query q) {
		if(!StringUtil.isEmpty(qm.getParentUuid())){
			q.setParameter("parentUuid", qm.getParentUuid());
		}
		if(!StringUtil.isEmpty(qm.getSearchType())&&qm.getBrandUuids()!=null && qm.getBrandUuids().size()>0){
			q.setParameterList("brandUuids", qm.getBrandUuids());
		}
		
		if(!StringUtil.isEmpty(qm.getLetter())&&!"in".equals(qm.getLetter())&&!"not".equals(qm.getLetter())){
			String letter=qm.getLetter().toLowerCase();
			q.setParameter("letter", letter+"%");
		}
	}
	
	/**
	 * 通过父分类uuid查询所属子分类集合
	 * @param productBrandUuid
	 * @return
	 */
	public List<ProductBrandQueryModel> getSubProductBrandsByUuid(String productBrandUuid){
		if(StringUtil.isEmpty(productBrandUuid)){
			return null;
		}
		StringBuffer hql = new StringBuffer("select o from ProductBrandModel o where o.parentUuid=:parentUuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("parentUuid", productBrandUuid);
		List<ProductBrandQueryModel> objs=query.list();
		if(objs!=null && objs.size()>0){
			return objs;
		}
		return null;
	}
	
	/**
	 * 根据品牌uuid,获取品牌名称
	 * @param uuid
	 * @return
	 */
	public String getBrandNameByUuid(String uuid){
		if(StringUtil.isEmpty(uuid)){
			return null;
		}
		StringBuffer hql = new StringBuffer("select o.brandName from ProductBrandModel o where o.uuid=:uuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		
		query.setString("uuid", uuid);
		Object obj=query.uniqueResult();
		if(obj!=null){
			return (String)obj;
		}
		
		return null;
	}
	
	/**
	 * 根据品牌uuid集合,获取品牌名称集合
	 * @param uuid
	 * @return
	 */
	public List<String> getBrandNamesByUuids(List<String> uuids){
		if(uuids==null || uuids.size() ==0){
			return null;
		}
		StringBuffer hql = new StringBuffer("select o.brandName from ProductBrandModel o where o.uuid in(:uuids) ");
		Query query = this.getH4Session().createQuery(hql.toString());
		
		query.setParameterList("uuids", uuids);
		List<String> list=query.list();
		return list;
	}
	
	
	/**
	 * 根据品牌uuid集合,获取品牌集合
	 * @param uuid
	 * @return
	 */
	public List<ProductBrandModel> getByUuids(List<String> uuids){
		if(uuids==null || uuids.size() ==0){
			return null;
		}
		StringBuffer hql = new StringBuffer("select o from ProductBrandModel o where o.uuid in(:uuids) ");
		Query query = this.getH4Session().createQuery(hql.toString());
		
		query.setParameterList("uuids", uuids);
		List<ProductBrandModel> list=query.list();
		return list;
	}
	
}
