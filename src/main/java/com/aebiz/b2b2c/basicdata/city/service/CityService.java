package com.aebiz.b2b2c.basicdata.city.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.basicdata.city.vo.CityModel;
import com.aebiz.b2b2c.basicdata.city.vo.CityQueryModel;

public interface CityService extends BaseService<CityModel, CityQueryModel> {

	/**
	 * 根据省的编号
	 * 
	 * @param provinceUuid
	 * @return
	 */
	public List<CityModel> getCityModelListByProvinceUuid(String provinceUuid);

	/**
	 * 通过市编号获得市名称
	 * 
	 * @param city
	 * @return
	 */
	public String getCityNameById(String city);

	/**
	 * 通过市编号获得省市区名称
	 * 
	 * @param cityUuid
	 * @return
	 */
	public String getWholeAreameById(String cityUuid);

	/**
	 * 获取所有城市
	 * @return
	 */
	public List<CityModel> getAllCity();
	
	/**
	 * 根据名字获得id
	 * 
	 * @param provinceName
	 * @return
	 */
	public String getIdByName(String cityName);

	public List<String> getCityIdsListByProvinceUuid(String provinceUuid);
}
