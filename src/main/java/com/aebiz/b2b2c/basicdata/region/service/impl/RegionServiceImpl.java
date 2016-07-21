package com.aebiz.b2b2c.basicdata.region.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.basicdata.region.dao.RegionDAO;
import com.aebiz.b2b2c.basicdata.region.service.RegionService;
import com.aebiz.b2b2c.basicdata.region.vo.RegionModel;
import com.aebiz.b2b2c.basicdata.region.vo.RegionQueryModel;

@Service
@Transactional
public class RegionServiceImpl extends
		BaseServiceImpl<RegionModel, RegionQueryModel> implements RegionService {
	private RegionDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(RegionDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(RegionModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(RegionModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(RegionModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据市的编号,查询所有地区
	 * 
	 * @param cityUuid
	 * @return
	 */
	public List<RegionModel> getRegionModelListByCityUuid(String cityUuid) {
		return myDao.getRegionModelListByCityUuid(cityUuid);
	}

	/**
	 * 通过名称得到id
	 * 
	 * @param regionName
	 * @return
	 */
	@Override
	public String getIdByName(String regionName,String cityId) {
		return myDao.getIdByName(regionName,cityId);
	}

	@Override
	public String getWholeAreameById(String regionId) {
		
		return myDao.getWholeAreameById(regionId);
	}

	@Override
	public List<String> getRegionIdsListByCityUuid(String cityUuid) {
		return myDao.getRegionIdsListByCityUuid(cityUuid);
	}
}