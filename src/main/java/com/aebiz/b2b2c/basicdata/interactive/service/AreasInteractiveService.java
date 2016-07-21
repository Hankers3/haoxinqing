package com.aebiz.b2b2c.basicdata.interactive.service;

import java.util.List;
import java.util.Map;

import com.aebiz.b2b2c.basicdata.city.vo.CityModel;
import com.aebiz.b2b2c.basicdata.city.vo.CityQueryModel;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceModel;

public interface AreasInteractiveService {
	/**
	 * 
	 * 通过省市区编号获得省市区名称
	 * 
	 * 如果city 和 region 为空，则返回province的名称 <br />
	 * 如果 region 为空，则返回province>city的名称 <br />
	 * 如果都不为空，则返回province>city>region的名称 <br />
	 * 
	 * @param province
	 * @param city
	 * @param region
	 * @return
	 */
	public String getAreaName(String province, String city, String region);

	/**
	 * 
	 * 获得所有的省和二级地区
	 * 
	 * @return
	 */
	public Map<ProvinceModel, List<CityModel>> getAllProvines();
	
	/**
	 * 根据启用状态搜索对应所有的城市
	 * 
	 * @param state
	 * @return
	 */
	public List getCityByState(String state);
	
	
	/**
	 * 查询城市列表
	 * @param needPage
	 * @param qm
	 * @param start
	 * @param pageShow
	 * @return
	 */
	public List<CityModel> getCityListByCondition(CityQueryModel qm, int start, int pageShow);
	
	public int getCount(CityQueryModel qm);
}
