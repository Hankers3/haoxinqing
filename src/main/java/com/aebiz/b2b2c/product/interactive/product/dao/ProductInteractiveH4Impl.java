package com.aebiz.b2b2c.product.interactive.product.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveQueryModel;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceModel;

/**
 * 
 * 商品系统：对外查询接口
 * 
 * @author huangpinpin
 * 
 */
@Repository
public class ProductInteractiveH4Impl extends
		BaseH4Impl<ProductInteractiveModel, ProductInteractiveQueryModel>
		implements ProductInteractiveDAO {

	/**
	 * 拼接sql语句
	 */
	protected String getAppendHql(ProductInteractiveQueryModel qm) {
		StringBuffer hql = new StringBuffer("");
		if (qm != null) {
			// 价格范围
			if (!StringUtil.isEmpty(qm.getShopPrice())
					&& !StringUtil.isEmpty(qm.getShopPrice1())) {
				hql.append(" and p.shopPrice>=:shopPrice and p.shopPrice <:shopPrice1 ");
			}
			// 商户uuid
			if (!StringUtil.isEmpty(qm.getStoreUuid())) {
				hql.append(" and o.storeUuid =:storeUuid ");
			}
			// 商品编号-in
			if (qm.getProductUuids() != null && qm.getProductUuids().length > 0) {
				hql.append(" and o.uuid not in (:uuids) ");
			}
			// 商品编号-not in
			if (qm.getInProductUuids() != null
					&& qm.getInProductUuids().length > 0) {
				hql.append(" and o.uuid in (:inUuids) ");
			}
			// 商品名称
			if (!StringUtil.isEmpty(qm.getProductName())) {
				hql.append(" and o.productName like:productName ");
			}
			// 商品编号
			if (!StringUtil.isEmpty(qm.getProductNo())) {
				hql.append(" and o.productNo like:productNo ");
			}

		}
		// 排序
		return hql.append(orderBy(qm)).toString();
	}

	/**
	 * 排序字段
	 * 
	 * @param qm
	 * @return
	 */
	protected String orderBy(ProductInteractiveQueryModel qm) {
		if (qm != null) {
			// 商品名称
			if ("productMain.productName".equals(qm.getSortName())) {
				return "order by o.productName " + qm.getSortType();
			}
			// 商品价格
			if ("productPrice.shopPrice".equals(qm.getSortName())) {
				return "order by p.shopPrice " + qm.getSortType();
			}
			// 商品编号
			if ("productMain.productNo".equals(qm.getSortName())) {
				return "order by o.productNo " + qm.getSortType();
			}
		}
		return "order by o.opeTime desc ";
	}

	/**
	 * 设置查询条件
	 */
	protected void setAppendHqlValue(ProductInteractiveQueryModel qm, Query q) {
		if (qm != null) {
			// 价格范围
			if (!StringUtil.isEmpty(qm.getShopPrice())
					&& !StringUtil.isEmpty(qm.getShopPrice1())) {
				q.setString("shopPrice", qm.getShopPrice());
				q.setString("shopPrice1", qm.getShopPrice1());
			}
			// 商户uuid
			if (!StringUtil.isEmpty(qm.getStoreUuid())) {
				q.setString("storeUuid", qm.getStoreUuid());
			}
			// 商品编号-not in
			if (qm.getProductUuids() != null && qm.getProductUuids().length > 0) {
				q.setParameterList("uuids", qm.getProductUuids());
			}
			// 商品编号-in
			if (qm.getInProductUuids() != null
					&& qm.getInProductUuids().length > 0) {
				q.setParameterList("inUuids", qm.getInProductUuids());
			}
			// 商品名称
			if (!StringUtil.isEmpty(qm.getProductName())) {
				q.setString("productName", "%" + qm.getProductName() + "%");
			}
			// 商品编号
			if (!StringUtil.isEmpty(qm.getProductNo())) {
				q.setString("productNo", "%" + qm.getProductNo() + "%");
			}
		}
	}

	/**
	 * 对外接口：根据uuid查询商品对象
	 * 
	 * @param uuid
	 * @return
	 */
	public ProductInteractiveModel getProductByUuid(String uuid) {
		if (StringUtil.isEmpty(uuid)) {
			return null;
		}

		ProductInteractiveModel out = new ProductInteractiveModel();
		StringBuffer hql = new StringBuffer(
				"select o,p,m,t from ProductMainModel o,ProductMainBasePriceModel p ,ProductMainImageModel m,ProductMainTransferModel t where o.uuid=p.productUuid and o.uuid=m.productUuid and o.uuid=t.productUuid and o.uuid=:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		Object[] obj = (Object[]) query.uniqueResult();
		if (obj != null) {
			ProductMainModel productMain = (ProductMainModel) obj[0];
			ProductMainBasePriceModel productPrice = (ProductMainBasePriceModel) obj[1];
			out.setProductPrice(productPrice);
			out.setProductMain(productMain);
			return out;
		}
		return null;
	}

	/**
	 * 根据条件查询商品
	 * 
	 * @param onlyUuids
	 * @param needPage
	 * @param qm
	 * @param start
	 * @param pageShow
	 * @return
	 */
	public List<ProductInteractiveModel> getProductByCondition(
			boolean onlyUuids, boolean needPage,
			ProductInteractiveQueryModel qm, int start, int pageShow) {
		String hql = "select o,p,m from ProductMainModel o,ProductMainBasePriceModel p ,ProductMainImageModel m where o.uuid=p.productUuid and o.uuid=m.productUuid ";

		if (onlyUuids) {
			hql = "select o.uuid from ProductMainModel o,ProductMainBasePriceModel p ,ProductMainImageModel m where o.uuid=p.productUuid and o.uuid=m.productUuid ";
		}

		hql = hql + prepareHql(qm);
		hql = hql + getAppendHql(qm);

		Query q = getH4Session().createQuery(hql);
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		if (needPage) {
			q.setFirstResult(start);
			q.setMaxResults(pageShow);
		}

		if (onlyUuids) {
			return q.list();
		}

		List<Object[]> tempList = q.list();
		if (tempList != null && tempList.size() > 0) {
			return convertType(tempList);
		}
		return null;
	}

	/**
	 * 对外接口：获取某个商户下可以关联促销的商品集合
	 * 
	 * @param uuids
	 *            过滤商品uuids数组
	 * @param storeUuid
	 *            商户uuid
	 * @param start
	 * @param page
	 * @return
	 */
	public List<ProductInteractiveModel> searchProduct(
			ProductInteractiveQueryModel qm, int start, int page) {
		ProductInteractiveModel out = new ProductInteractiveModel();
		String hql = "select o,p,m from ProductMainModel o,ProductMainBasePriceModel p ,ProductMainImageModel m where o.uuid=p.productUuid and o.uuid=m.productUuid ";

		hql = hql + getAppendHql(qm);

		Query query = this.getH4Session().createQuery(hql);
		setAppendHqlValue(qm, query);

		query.setFirstResult(start);
		query.setMaxResults(page);
		List<Object[]> list = query.list();
		if (list != null && list.size() > 0) {
			List<ProductInteractiveModel> outList = convertType(list);
			return outList;
		}

		return null;
	}

	/**
	 * 对外接口：获取某个商户下可以关联促销的商品集合个数
	 * 
	 * @param uuids
	 *            过滤商品uuid数组
	 * @param storeUuid
	 *            商户uuid
	 * @return
	 */
	public int getCountByProduct(ProductInteractiveQueryModel qm) {
		String hql = "select count(*) from ProductMainModel o,ProductMainBasePriceModel p ,ProductMainImageModel m where o.uuid=p.productUuid and o.uuid=m.productUuid ";
		hql = hql + getAppendHql(qm);

		Query query = this.getH4Session().createQuery(hql);
		setAppendHqlValue(qm, query);

		return ((Number) query.uniqueResult()).intValue();
	}

	/**
	 * 转换类型成对外model
	 * 
	 * @param tempList
	 * @return
	 */
	public List<ProductInteractiveModel> convertType(List<Object[]> tempList) {
		List<ProductInteractiveModel> list = new ArrayList<ProductInteractiveModel>();
		if (tempList != null && tempList.size() > 0) {
			for (Object[] obj : tempList) {

				ProductInteractiveModel product = new ProductInteractiveModel();
				ProductMainModel productMain = new ProductMainModel();
				productMain = (ProductMainModel) obj[0];

				ProductMainBasePriceModel productPrice = new ProductMainBasePriceModel();
				productPrice = (ProductMainBasePriceModel) obj[1];


				product.setProductMain(productMain);
				product.setProductPrice(productPrice);

				list.add(product);
			}
		}
		return list;
	}

	/**
	 * 
	 * 通过商品编号查询商品的基础信息<br />
	 * 此方法暂时被前台添加商品到购物车调用<br />
	 * 
	 * 需要的数据有:<br />
	 * 1.商品名称<br />
	 * 2.商品图片<br />
	 * 3.店铺编号<br />
	 * 4.商品状态<br />
	 * 5.审核状态<br />
	 * 
	 * @param productUuid
	 * @return
	 * @author duandeyi 2014-12-19
	 */
	public Map<String, String> getProductParamsByProductUuid(String productUuid) {

		StringBuffer sb = new StringBuffer(
				"select p.productName ,p.storeUuid ,p.auditState ,p.state ,img.centerImage from ProductMainModel p ,ProductMainImageModel img where 1=1");
		sb.append(" and p.uuid=img.productUuid");
		sb.append(" and p.uuid=:productUuid");

		Query query = this.getH4Session().createQuery(sb.toString());
		query.setString("productUuid", productUuid);

		Map<String, String> paramMap = new HashMap<String, String>();

		List plist = query.list();
		if (plist != null && plist.size() > 0) {
			Object[] obj = (Object[]) plist.get(0);
			String productName = (String) obj[0];
			String storeUuid = (String) obj[1];
			String auditState = (String) obj[2];
			String state = (String) obj[3];
			String centerImage = (String) obj[4];

			paramMap.put("productName", productName);
			paramMap.put("storeUuid", storeUuid);
			paramMap.put("auditState", auditState);
			paramMap.put("state", state);
			paramMap.put("centerImage", centerImage);

			return paramMap;
		} else {
			return null;
		}
	}
}
