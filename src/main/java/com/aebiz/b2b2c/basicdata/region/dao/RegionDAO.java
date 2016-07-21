package com.aebiz.b2b2c.basicdata.region.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.basicdata.region.vo.RegionModel;
import com.aebiz.b2b2c.basicdata.region.vo.RegionQueryModel;

public interface RegionDAO extends BaseDAO<RegionModel, RegionQueryModel> {

	/**
	 * 根据市的编号,查询所有地区
	 * 
	 * @param cityUuid
	 * @return
	 */
	public List<RegionModel> getRegionModelListByCityUuid(String cityUuid);

	/**
	 * 通过名称得到id
	 * 
	 * @param regionName
	 * @return
	 */
	public String getIdByName(String regionName,String cityId);
	
	/**
	 * 根据市的编号,查询所有地区
	 * 
	 * @param cityUuid
	 * @return
	 */
	public List<String> getRegionIdsListByCityUuid(String cityUuid);
	
	/**
	 * 通过市编号获得省市区名称
	 * 
	 * @param cityUuid
	 * @return
	 */
	public String getWholeAreameById(String regionId);
}