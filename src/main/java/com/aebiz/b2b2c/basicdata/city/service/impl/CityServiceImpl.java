package com.aebiz.b2b2c.basicdata.city.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.basicdata.city.dao.CityDAO;
import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.basicdata.city.vo.CityModel;
import com.aebiz.b2b2c.basicdata.city.vo.CityQueryModel;
import com.aebiz.b2b2c.basicdata.province.dao.ProvinceDAO;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceModel;

@Service
@Transactional
public class CityServiceImpl extends BaseServiceImpl<CityModel, CityQueryModel> implements CityService {
	private CityDAO myDao = null;

	@Autowired
	private UuidService us;

	@Autowired
	private ProvinceDAO provinceDao;

	@Autowired
	public void setMyDao(CityDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CityModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CityModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CityModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据省的编号
	 * 
	 * @param provinceUuid
	 * @return
	 */
	public List<CityModel> getCityModelListByProvinceUuid(String provinceUuid) {
		return myDao.getCityModelListByProvinceUuid(provinceUuid);
	}

	/**
	 * 通过市编号获得市名称
	 * 
	 * @param city
	 * @return
	 */
	public String getCityNameById(String city) {
		return myDao.getCityNameById(city);
	}

	/**
	 * 通过市编号获得省市区名称
	 * 
	 * @param cityUuid
	 * @return
	 */
	public String getWholeAreameById(String cityUuid) {

		CityModel cm = myDao.getByUuid(cityUuid);

		if (cm != null) {
			String province = cm.getProvinceUuid();
			ProvinceModel pm = provinceDao.getByUuid(province);

			if (pm != null) {
				return pm.getProvinceName() + cm.getCityName();
			}
		}

		return "";

		// return myDao.getWholeAreameById(cityUuid);
	}

	/**
	 * 获取所有城市
	 * 
	 * @return
	 */
	public List<CityModel> getAllCity() {
		return myDao.getAll();
	}

	@Override
	public String getIdByName(String cityName) {
		return myDao.getIdByName(cityName);
	}

	@Override
	public List<String> getCityIdsListByProvinceUuid(String provinceUuid) {
		return myDao.getCityIdsListByProvinceUuid(provinceUuid);
	}
}