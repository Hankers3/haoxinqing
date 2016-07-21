package com.aebiz.b2b2c.product.productpub.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceModel;
import com.aebiz.b2b2c.product.productpub.vo.ProductModel;
import com.aebiz.b2b2c.product.productpub.vo.ProductQueryModel;

@Repository
public class ProductH4Impl extends BaseH4Impl<ProductModel,ProductQueryModel> implements ProductDAO {
	/**
	 * 拼接查询条件
	 * 
	 */
	protected String getAppendHql(ProductQueryModel qm) {
		String hql="";
		if(!StringUtil.isEmpty(qm.getShopPrice()) && !StringUtil.isEmpty(qm.getShopPrice1()) ){
			hql+= " and p.shopPrice >:shopPrice and p.shopPrice <:shopPrice1 ";
		}
		return hql+=super.getAppendHql(qm);
	}
	
	protected void setAppendHqlValue(ProductQueryModel qm, Query q) {
		if(!StringUtil.isEmpty(qm.getShopPrice()) && !StringUtil.isEmpty(qm.getShopPrice1()) ){
			q.setString("shopPrice", qm.getShopPrice());
			q.setString("shopPrice1", qm.getShopPrice1());
		}
		
		super.setAppendHqlValue(qm, q);
	}
	
	public List<ProductModel> getProductListByCondition( boolean needPage, ProductQueryModel qm, int start, int pageShow){
	    String hql = "select o ,i,p  from  ProductMainModel o ,ProductMainImageModel i,ProductMainBasePriceModel p    where 1=1 ";
	    hql+=" and o.uuid=i.productUuid  and o.uuid = p.productUuid  ";
	    hql = hql + prepareHql(qm);
	    hql = hql + getAppendHql(qm);
	    
	    Query q = getH4Session().createQuery(hql);
	    setValue(qm, q);
	    setAppendHqlValue(qm, q);
	    
	    if (needPage) {
	      q.setFirstResult(start);
	      q.setMaxResults(pageShow);
	    }
      List<Object[]> tempList = q.list();
      return exeResultProductList(tempList);
	 }
	
	
	/**
	 * 根据条件查询商品个数
	 * @param qm 查询条件
	 */
	public int getCount(ProductQueryModel qm) {
		 String hql = "select count(o) from ProductMainModel o ,ProductMainImageModel i,ProductMainBasePriceModel p where 1=1 ";
		 hql+=" and o.uuid=i.productUuid  and o.uuid = p.productUuid   ";
		 hql = hql + prepareHql(qm);
		 hql = hql + getAppendHql(qm);
		 
		 Query q = getH4Session().createQuery(hql);
		 setValue(qm, q);
		 setAppendHqlValue(qm, q);
		 
		 return ((Number)q.uniqueResult()).intValue();
	}
	
	/**
	 * 	转换商品对象
	 * @param tempList 临时对象
	 * @return
	 */
	private List<ProductModel> exeResultProductList (List<Object[]> tempList) {
		List<ProductModel> list = new ArrayList<ProductModel>();
		if (tempList != null && tempList.size() > 0) {
			for (Object[] obj : tempList) {
				ProductModel product = new ProductModel();
				ProductMainModel productMain=(ProductMainModel) obj[0];
				//ProductMainImageModel productImage=(ProductMainImageModel) obj[1];
				ProductMainBasePriceModel productPrice=(ProductMainBasePriceModel) obj[2];
				//ProductAttrStockModel productStock=(ProductAttrStockModel) obj[3];
				product.setProductMain(productMain);
				//product.setProductImage(productImage);

				list.add(product);
			}
		}
		return list;
	}

}
