package com.aebiz.b2b2c.basicdata.province.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.basicdata.province.dao.ProvinceDAO;
import com.aebiz.b2b2c.basicdata.province.service.ProvinceService;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceModel;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceQueryModel;
import com.aebiz.b2b2c.basicdata.region.service.RegionService;

@Service
@Transactional
public class ProvinceServiceImpl extends
		BaseServiceImpl<ProvinceModel, ProvinceQueryModel> implements
		ProvinceService {
	private ProvinceDAO myDao = null;

	@Autowired
	private CityService cityService = null;

	@Autowired
	private RegionService regionService = null;

	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(ProvinceDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProvinceModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ProvinceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ProvinceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

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
	public String getAreaName(String provinceUuid, String cityUuid,
			String regionUuid) {

		if (!StringUtil.isEmpty(cityUuid)) {
			return cityService.getWholeAreameById(cityUuid);
		}

		if (!StringUtil.isEmpty(provinceUuid)) {
			return this.getProvinceNameById(provinceUuid);
		}

		return "";
	}

	/**
	 * 通过省份编号获得省名称
	 * 
	 * @param province
	 * @return
	 */
	public String getProvinceNameById(String province) {
		return myDao.getProvinceNameById(province);
	}

	/**
	 * 通过名字得到id
	 * 
	 * @param provinceName
	 * @return
	 */
	@Override
	public String getIdByName(String provinceName) {
		return myDao.getIdByName(provinceName);
	}
}