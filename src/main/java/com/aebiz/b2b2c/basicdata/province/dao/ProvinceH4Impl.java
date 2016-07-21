package com.aebiz.b2b2c.basicdata.province.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceModel;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceQueryModel;

@Repository
public class ProvinceH4Impl extends
		BaseH4Impl<ProvinceModel, ProvinceQueryModel> implements ProvinceDAO {

	@Override
	protected String getAppendHql(ProvinceQueryModel qm) {
		return "order by o.uuid asc ";
	}

	/**
	 * 通过省份编号获得省名称
	 * 
	 * @param province
	 * @return
	 */
	public String getProvinceNameById(String province) {
		StringBuffer hql = new StringBuffer(
				"select o.provinceName from ProvinceModel o where 1=1");

		hql.append(" and o.uuid=:uuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("uuid", province);

		return (String) q.uniqueResult();
	}


	/**
	 * 通过名字得到id
	 * 
	 * @param provinceName
	 * @return
	 */
	@Override
	public String getIdByName(String provinceName) {
		StringBuffer hql = new StringBuffer(
				"select o.uuid from ProvinceModel o where 1=1");

		hql.append(" and o.provinceName=:provinceName");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("provinceName", provinceName);

		return (String) q.uniqueResult();
	}
}
