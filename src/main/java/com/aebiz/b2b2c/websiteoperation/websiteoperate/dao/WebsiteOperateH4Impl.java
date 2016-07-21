package com.aebiz.b2b2c.websiteoperation.websiteoperate.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateQueryModel;

@Repository
public class WebsiteOperateH4Impl extends
		BaseH4Impl<WebsiteOperateModel, WebsiteOperateQueryModel> implements
		WebsiteOperateDAO {
	/**
	 * 校验编号是否重复
	 */
	@Override
	public boolean checkKeyExist(String resourceKey) {
		StringBuffer hql = new StringBuffer(
				"select count(*) from WebsiteOperateModel where resourceKey=:resourceKey ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("resourceKey", resourceKey);

		return ((Number) query.uniqueResult()).intValue() > 0 ? true : false;
	}

	/**
	 * 
	 * 通过运营参数的值，获得运营对象
	 * 
	 * @param resourceKey
	 * @return
	 */
	public WebsiteOperateModel getByResourceKey(String resourceKey) {
		StringBuffer hql = new StringBuffer(
				"from WebsiteOperateModel where resourceKey=:resourceKey ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("resourceKey", resourceKey);

		List list = query.list();
		if (list != null && list.size() > 0) {
			return (WebsiteOperateModel) list.get(0);
		}

		return null;
	}

	/**
	 * 通过运营参数key值获取value值
	 */
	@Override
	public String getValueByKey(String key) {
		StringBuffer hql = new StringBuffer(
				" select paramValue from WebsiteOperateModel where resourceKey=:resourceKey ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("resourceKey", key);

		Object obj = query.uniqueResult();
		if (obj != null) {
			return (String) obj;
		}
		return "";
	}

	/**
	 * 通过运营参数key更新value值
	 */
	@Override
	public void updateValueByKey(String key, String value) {
		StringBuffer hql = new StringBuffer(
				" update WebsiteOperateModel set paramValue=:paramValue where resourceKey=:resourceKey");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("paramValue", value);
		query.setString("resourceKey", key);

		query.executeUpdate();
	}

	/**
	 * 通过模块名称获取参数列表
	 */
	@Override
	public List<WebsiteOperateModel> getWebsiteOperateModelListByModuleName(
			String moduleName) {
		StringBuffer hql = new StringBuffer(
				" from WebsiteOperateModel where moduleName =:moduleName order by position ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("moduleName", moduleName);

		List<WebsiteOperateModel> womList = query.list();

		return womList;
	}
}
