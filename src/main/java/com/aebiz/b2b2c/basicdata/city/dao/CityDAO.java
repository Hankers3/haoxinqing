package com.aebiz.b2b2c.basicdata.city.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.basicdata.city.vo.CityModel;
import com.aebiz.b2b2c.basicdata.city.vo.CityQueryModel;

public interface CityDAO extends BaseDAO<CityModel, CityQueryModel> {

	/**
	 * 根据省的编号
	 * 
	 * @param provinceUuid
	 * @return
	 */
	public List<CityModel> getCityModelListByProvinceUuid(String provinceUuid);

	/**
	 * 根据省的编号
	 * 
	 * @param provinceUuid
	 * @return
	 */
	public List<String> getCityIdsListByProvinceUuid(String provinceUuid);

	/**
	 * 通过市编号获得市名称
	 * 
	 * @param city
	 * @return
	 */
	public String getCityNameById(String city);

	/**
	 * 根据名字获得id
	 * 
	 * @param provinceName
	 * @return
	 */
	public String getIdByName(String cityName);
	
}
