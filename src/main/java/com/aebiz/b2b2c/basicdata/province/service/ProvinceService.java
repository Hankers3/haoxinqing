package com.aebiz.b2b2c.basicdata.province.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceModel;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceQueryModel;

public interface ProvinceService extends
		BaseService<ProvinceModel, ProvinceQueryModel> {

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
	 * 通过省份编号获得省名称
	 * 
	 * @param province
	 * @return
	 */
	public String getProvinceNameById(String province);

	/**
	 * 通过名字得到id
	 * @param provinceName
	 * @return
	 */
	public String getIdByName(String provinceName);
}
