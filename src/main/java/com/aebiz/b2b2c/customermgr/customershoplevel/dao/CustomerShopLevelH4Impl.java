package com.aebiz.b2b2c.customermgr.customershoplevel.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelQueryModel;

@Repository
public class CustomerShopLevelH4Impl extends
		BaseH4Impl<CustomerShopLevelModel, CustomerShopLevelQueryModel>
		implements CustomerShopLevelDAO {

	/**
	 * 得到平台会员等级model的集合
	 */
	
	public List<CustomerShopLevelModel> getShopLevelModelList() {
		StringBuffer hql = new StringBuffer(
				" from CustomerShopLevelModel where 1=1 ");

		Query query = this.getH4Session().createQuery(hql.toString());
		List<CustomerShopLevelModel> shopLevelModelList = query.list();

		return shopLevelModelList;
	}

	/**
	 * 得到平台会员初始等级编号
	 * 
	 * 等级按照position进行排序，排序序号最小的为最低等级
	 * 
	 * @return
	 */
	public String getInitLevelUuid() {
		StringBuffer hql = new StringBuffer(
				" select uuid from CustomerShopLevelModel order by position asc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		if (query.list() != null && query.list().size() > 0) {
			return (String) query.list().get(0);
		}
		return "";
	}

	/**
	 * 通过编号获取等级名称
	 */
	public String getLevelNameByUuid(String uuid) {
		StringBuffer hql = new StringBuffer(
				" select levelName from CustomerShopLevelModel where uuid=:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		if (query.uniqueResult() != null) {
			return (String) query.uniqueResult();
		}
		return "";
	}

	/**
	 * 得到平台会员等级名称的集合
	 */
	public List<String> getCustomerShopLevelName() {
		StringBuffer hql = new StringBuffer(
				" select levelName from CustomerShopLevelModel where 1=1 order by position ");

		Query query = this.getH4Session().createQuery(hql.toString());
		List<String> levelNameList = query.list();
		return levelNameList;
	}

	/**
	 * 添加平台会员等级的是否需要校验平台会员等级名称是否存在
	 * 
	 * @return
	 */

	public boolean checkLevelNameExist(String levelName, String uuid) {

		StringBuffer hql = new StringBuffer(
				"from CustomerShopLevelModel where levelName=:levelName");

		if (!StringUtil.isEmpty(uuid)) {
			hql.append(" and uuid <> :uuid");
		}

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("levelName", levelName);

		if (!StringUtil.isEmpty(uuid)) {
			query.setString("uuid", uuid);
		}

		if (query.list() != null && query.list().size() > 0) {
			return true;
		}

		return false;
	}
	/**
	 * 通过积分来获取等级名称
	 * 
	 * @param intergralcount
	 * @return
	 */
	@Override
	public String getLevelNameByIntergral(int intergralcount) {
		StringBuffer hql = new StringBuffer(" select o.levelName from  CustomerShopLevelModel as o where 1=1 ");
		hql.append(" and o.minIntegral <=:minIntegral");
		hql.append(" and o.maxIntegral >:maxIntegral");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		
		q.setString("minIntegral", intergralcount+"");
		q.setString("maxIntegral", intergralcount+"");
		
		List<String> list = q.list();
		String levelName="";
		if(list.size()>0){
			levelName = list.get(0);
		}
		return levelName;
	}

	/**
	 * 通过等级名称来获取等级uuid
	 * 
	 * @param levelName
	 * @return
	 */
	public String getUuidByLevelName(String levelName){
		StringBuffer hql = new StringBuffer(" select o.uuid from  CustomerShopLevelModel as o where 1=1 ");
		hql.append(" and o.levelName =:levelName ");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("levelName", levelName);
		
		List<String> list = q.list();
		if(list.size()>0){
			return list.get(0);
		}
		return "";
		
	}

	      @Override
	        public List<String> getShopLevelUuids() {
	                StringBuffer hql = new StringBuffer(
	                                " select uuid from CustomerShopLevelModel order by position asc ");

	                Query query = this.getH4Session().createQuery(hql.toString());

	                return query.list();
	        }
}
