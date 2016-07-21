package com.aebiz.b2b2c.basicdata.region.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.basicdata.region.vo.RegionModel;
import com.aebiz.b2b2c.basicdata.region.vo.RegionQueryModel;

@Repository
public class RegionH4Impl extends BaseH4Impl<RegionModel, RegionQueryModel>
		implements RegionDAO {

	/**
	 * 和省份表联合查询
	 * 
	 * 查询获得市所属省份名称，在列表中展示
	 * 
	 */
	protected String getMultiSelect() {
		return ",province.provinceName ,city.cityName";
	}

	/**
	 * 和省份表联合查询
	 * 
	 * 查询获得市所属省份名称，在列表中展示
	 * 
	 */
	protected String getMultiModel() {
		return ",ProvinceModel province ,CityModel city ";
	}

	/**
	 * 拼接查询条件：<br >
	 * 1.province.provinceUuid=o.provinceUuid <br />
	 * 2.按照省份进行查询 <br />
	 * 
	 */
	@Override
	protected String getAppendHql(RegionQueryModel qm) {
		StringBuffer sb = new StringBuffer();

		sb.append(" and province.uuid=city.provinceUuid ");
		sb.append(" and city.uuid=o.cityUuid");

		if (!StringUtil.isEmpty(qm.getProvinceUuid())) {
			sb.append(" and province.uuid=:provinceUuid ");
		}

		if (!StringUtil.isEmpty(qm.getCityUuid())) {
			sb.append(" and city.uuid=:cityUuid ");
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
	protected void setAppendHqlValue(RegionQueryModel qm, Query q) {
		if (!StringUtil.isEmpty(qm.getProvinceUuid())) {
			q.setString("provinceUuid", qm.getProvinceUuid());
		}

		if (!StringUtil.isEmpty(qm.getCityUuid())) {
			q.setString("provinceUuid", qm.getCityUuid());
		}

	}

	/**
	 * 转换数据 ，将查出来的省份数据设置到city对象中，再前台进行展示
	 * 
	 * 
	 */
	protected List<RegionModel> exeResultList(List<Object[]> tempList) {

		List<RegionModel> list = new ArrayList<RegionModel>();
		if (tempList != null && tempList.size() > 0) {
			for (Object[] obj : tempList) {

				RegionModel rModel = (RegionModel) obj[0];

				String provinceName = (String) obj[1];
				String cityName = (String) obj[2];

				String pcityName = "";
				// 查询的省份名称设置进去
				if (!StringUtil.isEmpty(provinceName)) {
					pcityName = provinceName;
				}

				if (!StringUtil.isEmpty(cityName)) {
					if (StringUtil.isEmpty(pcityName)) {
						pcityName = cityName;
					} else {
						pcityName = pcityName + ">" + cityName;
					}
				}

				rModel.setPcityName(pcityName);

				list.add(rModel);
			}
		}
		return list;
	}

	/**
	 * 根据市的编号,查询所有地区
	 * 
	 * @param cityUuid
	 * @return
	 */
	public List<RegionModel> getRegionModelListByCityUuid(String cityUuid) {
		StringBuffer hql = new StringBuffer(
				"select o from RegionModel o where 1=1");

		hql.append(" and o.cityUuid=:cityUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("cityUuid", cityUuid);

		return q.list();
	}

	/**
	 * 通过名称得到id
	 * 
	 * @param regionName
	 * @return
	 */
	@Override
	public String getIdByName(String regionName,String cityId) {
		StringBuffer hql = new StringBuffer(
				"select o.uuid from RegionModel o where 1=1");

		hql.append(" and o.regionName=:regionName");
		hql.append(" and o.cityUuid=:cityUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("regionName", regionName);
		q.setString("cityUuid", cityId);

		List list = q.list();
		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}
		return "";
	}
	
	/**
	 * 根据市的编号,查询所有地区
	 * 
	 * @param cityUuid
	 * @return
	 */
	public List<String> getRegionIdsListByCityUuid(String cityUuid) {
		StringBuffer hql = new StringBuffer(
				"select o.uuid from RegionModel o where 1=1");

		hql.append(" and o.cityUuid=:cityUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("cityUuid", cityUuid);

		return q.list();
	}

	@Override
	public String getWholeAreameById(String regionId) {
		StringBuffer hql = new StringBuffer(
				"select o.regionName from RegionModel o where 1=1");

		hql.append(" and o.uuid=:uuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("uuid", regionId);
		List list = q.list();
		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}
		return "";
	}
}
