package com.aebiz.b2b2c.basicdata.interactive.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.basicdata.city.vo.CityModel;
import com.aebiz.b2b2c.basicdata.city.vo.CityQueryModel;
import com.aebiz.b2b2c.basicdata.interactive.service.AreasInteractiveService;
import com.aebiz.b2b2c.basicdata.province.service.ProvinceService;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceModel;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceQueryModel;

@Service
@Transactional
public class AreasInteractiveServiceImpl implements AreasInteractiveService {

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private CityService cityService;

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
	public String getAreaName(String province, String city, String region) {
		return provinceService.getAreaName(province, city, region);
	}

	/**
	 * 
	 * 获得所有的省和二级地区
	 * 
	 * @return
	 */
	public Map<ProvinceModel, List<CityModel>> getAllProvines() {
		List<ProvinceModel> provinceModel = provinceService
				.getByCondition(new ProvinceQueryModel());

		Map<ProvinceModel, List<CityModel>> provinceList = new HashMap<ProvinceModel, List<CityModel>>();
		for (ProvinceModel pm : provinceModel) {
			List<CityModel> cmList = cityService
					.getCityModelListByProvinceUuid(pm.getUuid());
			provinceList.put(pm, cmList);
		}
		return provinceList;
	}

	public List getCityByState(String state) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 查询城市列表
	 * @param needPage
	 * @param qm
	 * @param start
	 * @param pageShow
	 * @return
	 */
	public List<CityModel> getCityListByCondition(CityQueryModel qm, int start, int pageShow) {
		
		return cityService.getByCondition(qm, start, pageShow);
	}

	public int getCount(CityQueryModel qm) {
		
		return cityService.getCount(qm);
	}
}
