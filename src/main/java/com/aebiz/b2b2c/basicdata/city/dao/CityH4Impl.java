package com.aebiz.b2b2c.basicdata.city.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.basicdata.city.vo.CityModel;
import com.aebiz.b2b2c.basicdata.city.vo.CityQueryModel;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceModel;

@Repository
public class CityH4Impl extends BaseH4Impl<CityModel, CityQueryModel> implements
		CityDAO {

	/**
	 * 和省份表联合查询
	 * 
	 * 查询获得市所属省份名称，在列表中展示
	 * 
	 */
	protected String getMultiSelect() {
		return ",province ";
	}

	/**
	 * 和省份表联合查询
	 * 
	 * 查询获得市所属省份名称，在列表中展示
	 * 
	 */
	protected String getMultiModel() {
		return ",ProvinceModel province ";
	}

	/**
	 * 拼接查询条件：<br >
	 * 1.province.provinceUuid=o.provinceUuid <br />
	 * 2.按照省份进行查询 <br />
	 * 
	 */
	@Override
	protected String getAppendHql(CityQueryModel qm) {
		StringBuffer sb = new StringBuffer();

		sb.append(" and province.uuid=o.provinceUuid ");

		if (!StringUtil.isEmpty(qm.getProvinceUuid())) {
			sb.append(" and province.uuid=:provinceUuid ");
		}

		if (qm != null) {
			sb.append(" order by o." + qm.getSortName() + " "
					+ qm.getSortType());
		} else {
			sb.append(" order by o.uuid asc ");
		}

		return sb.toString();
	}

	/**
	 * 设置省份查询条件
	 * 
	 */
	@Override
	protected void setAppendHqlValue(CityQueryModel qm, Query q) {
		if (!StringUtil.isEmpty(qm.getProvinceUuid())) {
			q.setString("provinceUuid", qm.getProvinceUuid());
		}
	}

	/**
	 * 转换数据 ，将查出来的省份数据设置到city对象中，再前台进行展示
	 * 
	 * 
	 */
	protected List<CityModel> exeResultList(List<Object[]> tempList) {

		List<CityModel> list = new ArrayList<CityModel>();
		if (tempList != null && tempList.size() > 0) {
			for (Object[] obj : tempList) {
				CityModel cityModel = (CityModel) obj[0];
				ProvinceModel provinceModel = (ProvinceModel) obj[1];

				// 查询的省份名称设置进去
				if (provinceModel != null) {
					cityModel.setProvinceName(provinceModel.getProvinceName());
				}

				list.add(cityModel);
			}
		}
		return list;
	}

	/**
	 * 根据省的编号
	 * 
	 * @param provinceUuid
	 * @return
	 */
	public List<CityModel> getCityModelListByProvinceUuid(String provinceUuid) {

		StringBuffer hql = new StringBuffer(
				"select o from CityModel o where 1=1");

		hql.append(" and o.provinceUuid=:provinceUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("provinceUuid", provinceUuid);

		List list = q.list();

		return list;
	}

	/**
	 * 根据省的编号
	 * 
	 * @param provinceUuid
	 * @return
	 */
	public List<String> getCityIdsListByProvinceUuid(String provinceUuid) {

		StringBuffer hql = new StringBuffer(
				"select o.uuid from CityModel o where 1=1");

		hql.append(" and o.provinceUuid=:provinceUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("provinceUuid", provinceUuid);

		List list = q.list();

		return list;
	}

	/**
	 * 通过市编号获得市名称
	 * 
	 * @param city
	 * @return
	 */
	public String getCityNameById(String city) {
		StringBuffer hql = new StringBuffer(
				"select o.cityName from CityModel o where 1=1");

		hql.append(" and o.uuid=:uuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("uuid", city);

		return (String) q.uniqueResult();
	}

	/**
	 * 根据名字获得id
	 * 
	 * @param provinceName
	 * @return
	 */
	@Override
	public String getIdByName(String cityName) {
		StringBuffer hql = new StringBuffer(
				" select cm.uuid from CityModel as cm where 1=1 ");
		hql.append(" and cm.cityName like:cityName");

		Query q = this.getH4Session().createQuery(hql.toString());

		q.setString("cityName", "%" + cityName + "%");

		List<String> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}


}
