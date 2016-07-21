package com.aebiz.b2b2c.basicdata.province.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceModel;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceQueryModel;

public interface ProvinceDAO extends BaseDAO<ProvinceModel, ProvinceQueryModel> {

	/**
	 * 通过省份编号获得省名称
	 * 
	 * @param province
	 * @return
	 */
	public String getProvinceNameById(String province);

	/**
	 * 通过名字得到id
	 * 
	 * @param provinceName
	 * @return
	 */
	public String getIdByName(String provinceName);
}