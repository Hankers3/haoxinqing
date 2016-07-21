package com.aebiz.b2b2c.product.productcategoryplatform.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformModel;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformQueryModel;


/**
 * 平台分类数据操作
 * 1.平台分类用于发布商品时选择分类
 * 2.平台分类用于创建商户时,选择商户所属分类
 * 3.分类还得关联模板/品牌/标签分类
 * 
 * @author huangpinpin
 *
 */
@Repository
public class ProductCategoryPlatformH4Impl extends BaseH4Impl<ProductCategoryPlatformModel,ProductCategoryPlatformQueryModel> implements ProductCategoryPlatformDAO {
	
	/**
	 * 根据平台父分类uuid查询子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<ProductCategoryPlatformModel> getSubProductCategoryByParentUuid(String parentUuid){
		String hql="select o from ProductCategoryPlatformModel o where parentUuid=:parentUuid order by o.position";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", parentUuid);
		List<ProductCategoryPlatformModel> list=query.list();
		return list;
	}
	
	/**
	 * 根据平台父分类uuid查询子分类uuid
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<String> getSubProductCategoryUuidByParentUuid(String parentUuid){
		String hql="select o.uuid from ProductCategoryPlatformModel o where parentUuid=:parentUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("parentUuid", parentUuid);
		List<String> list=query.list();
		return list;
	}
			
	/**
	 * 该方法用于生成fullpath时使用，查询该fullpath是否已经存在
	 * @param fullPath 
	 * @return
	 */
	public List<ProductCategoryPlatformModel> getProductCategoryPlatformModelByFullPath(String fullPath){
		String hql=" select o from ProductCategoryPlatformModel  o where o.fullpath=:fullpath ";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("fullpath", fullPath);
		List<ProductCategoryPlatformModel> list=query.list();
		return list;
	}		
	
	/**
	 * 根据分类uuid获取分类名称
	 * @param uuid 分类uuid
	 * @return
	 */
	public String getNameByCategoryUuid(String uuid){
		if(StringUtil.isEmpty(uuid)){
			return "";
		}
		String hql="select o.categoryName from ProductCategoryPlatformModel o where uuid=:uuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("uuid", uuid);
		String name=(String) query.uniqueResult();
		Object obj= query.uniqueResult();
		if(obj!=null){
			return (String)obj;
		}
		return "";
	}
	
	/**
	 * 根据集合uuids 查出 分类集合
	 * @param uuids
	 * @return
	 */
	public List<ProductCategoryPlatformModel> getCategorysByUuids(List<String> uuids){
		String hql = "select o from ProductCategoryPlatformModel o  where o.uuid in (:uuids)";
		Query query = this.getH4Session().createQuery(hql);
		query.setParameterList("uuids", uuids);
		List<ProductCategoryPlatformModel> list=query.list();
		return list;
	}
}
