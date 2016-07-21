package com.aebiz.b2b2c.product.productordinary.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceModel;
import com.aebiz.b2b2c.product.productpub.vo.ProductModel;
import com.aebiz.b2b2c.product.productpub.vo.ProductQueryModel;

@Repository
public class ProductOrdinaryH4Impl extends BaseH4Impl<ProductModel,ProductQueryModel> implements ProductOrdinaryDAO {
	

	/**
	 * 拼接排序sql
	 * @param qm
	 * @return
	 */
	protected String getAppendHql(ProductQueryModel qm) {
		StringBuffer hql= new StringBuffer("");
		
		//商品名称
		if(!StringUtil.isEmpty(qm.getProductName())){
			hql.append(" and o.productName like:productName ") ;
		}
		//商品编号
		if(!StringUtil.isEmpty(qm.getProductEnglishName())){
			hql.append(" and o.productEnglishName like:englishName ") ;
		}
		
		hql.append(getOrderByHql(qm));
		return hql.toString();
	}
	
	/**
	 * 拼接排序sql
	 * @param qm
	 * @return
	 */
	protected String getOrderByHql(ProductQueryModel qm){
		StringBuffer hql= new StringBuffer("");
		if (qm != null) {
				// 按商品名称排序
			if ("productName".equals(qm.getSortName())) {
				hql.append(" order by o.productName " + qm.getSortType());
				// 按商品编号排序
			}  else if ("shopPrice".equals(qm.getSortName())) {
				hql.append(" order by o.shopPrice " + qm.getSortType());
				// 默认按操作时间升序
			} else {
				hql.append(" order by o.opeTime desc ");
			}
			return hql.toString();
		}
		
		return " order by o.opeTime desc ";
	}
	
	protected void setAppendHqlValue(ProductQueryModel qm, Query q) {
		//商品名称
		if(!StringUtil.isEmpty(qm.getProductName())){
			q.setString("productName","%"+ qm.getProductName()+"%");
		}
		//商品英文
		if(!StringUtil.isEmpty(qm.getProductEnglishName())){
			q.setString("englishName","%"+ qm.getProductEnglishName()+"%");
		}
	
		
		
	}
	
	
	public List<ProductModel> getProductListByCondition( boolean needPage, ProductQueryModel qm, int start, int pageShow){
	    String hql = "select o  from  ProductMainModel as o where 1=1 and delFlag =1 ";
	   
	    hql = hql + getAppendHql(qm);
	    
	    Query q = getH4Session().createQuery(hql);
	    setAppendHqlValue(qm, q);
	    
	    if (needPage) {
	      q.setFirstResult(start);
	      q.setMaxResults(pageShow);
	    }
	    List<ProductMainModel> tempList = q.list();
	    return exeResultProductList(tempList);
	 }
	
	public int getCount(ProductQueryModel qm) {
		 String hql = "select count(o) from ProductMainModel o where 1=1 and delFlag =1  ";
		 hql = hql + getAppendHql(qm);
		 
		 Query q = getH4Session().createQuery(hql);
		 setAppendHqlValue(qm, q);
		 
		 return ((Number)q.uniqueResult()).intValue();
	}
	
	/**
	 * 	转换商品对象
	 * @param tempList 
	 * @return
	 */
	private List<ProductModel> exeResultProductList (List<ProductMainModel> tempList) {
		List<ProductModel> list = new ArrayList<ProductModel>();
		if (tempList != null && tempList.size() > 0) {
			for (ProductMainModel obj : tempList) {
				ProductModel product = new ProductModel();
				product.setProductMain(obj);
				list.add(product);
			}
		}
		return list;
	}
	
	

   public void deletes(List<String> needDeleteUuids) {
    String hql = "delete from  ProductMainModel  o where o.uuid in (:uuids)";
     
    Map<String, Object> mapParams = new HashMap();
      mapParams.put("uuids", needDeleteUuids.toArray());
      
     exeUpdate(hql, mapParams);
    }
   
   
  
}
