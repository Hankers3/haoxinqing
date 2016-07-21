package com.aebiz.b2b2c.servicestaff.hospitalinfo.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.basicdata.province.service.ProvinceService;
import com.aebiz.b2b2c.basicdata.region.service.RegionService;
import com.aebiz.b2b2c.servicestaff.doctorimport.util.ExcelUtils;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.dao.HospitalInfoDAO;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.service.HospitalInfoService;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoLevelConst;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoModel;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoQueryModel;
import com.alibaba.fastjson.JSON;
import com.twelvemonkeys.lang.StringUtil;

@Service
@Transactional
public class HospitalInfoServiceImpl extends BaseServiceImpl<HospitalInfoModel, HospitalInfoQueryModel>
		implements HospitalInfoService {
	private HospitalInfoDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(HospitalInfoDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	/* 注入城市service */
	@Autowired
	private CityService cityService;

	/* 注入省份service */
	@Autowired
	private ProvinceService provinceService;

	/* 注入县service */
	@Autowired
	private RegionService regionService;

	@Override
	public String create(HospitalInfoModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(HospitalInfoModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(HospitalInfoModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 获取通过编号医院名
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public String getHospitalNameByUuid(String uuid) {

		return myDao.getHospitalNameByUuid(uuid);
	}

	/**
	 * 获取所有医院信息
	 * 
	 * @return
	 */
	@Override
	public List<HospitalInfoModel> getHospitals() {
		return myDao.getHospitals();
	}

	@Override
	public String getUuidByHospitalName(String hospitalName) {
		return myDao.getUuidByHospitalName(hospitalName);
	}

	/**
	 * 导入医院,并创建数据到表
	 */
	@Override
	public boolean importHospital(MultipartFile file) {
		if (file != null) {

			if (file != null && !file.isEmpty()) {
				HospitalInfoModel model = new HospitalInfoModel();
				Object[] columns = { "hospitalName", "hospitalLevel", "province", "city", "region" };
				try {
					List list = ExcelUtils.importExcel(file, model, columns);
					System.out.println("===========list======="+JSON.toJSONString(list));
					if (list != null && list.size() > 0) {
						for (Object object : list) {
							if (object != null) {
								HospitalInfoModel hospitalInfoModel = (HospitalInfoModel) object;
								
								if (!StringUtil.isEmpty(hospitalInfoModel.getHospitalName())) {
									String address="";
									// 省
									String provinceId = "";
									String provinceName = "";
									provinceName = hospitalInfoModel.getProvince();
									address=address+provinceName;
									provinceId = provinceService.getIdByName(provinceName);
									hospitalInfoModel.setProvince(provinceId);

									// 市
									String cityId = "";
									String cityName = "";
									cityName = hospitalInfoModel.getCity();
									address=address+cityName;
									cityId = cityService.getIdByName(cityName);
									hospitalInfoModel.setCity(cityId);

									// 区，县
									String regionId = "";
									String regionName = "";
									regionName = hospitalInfoModel.getRegion();
									address=address+regionName;
									// 去空格加tab
									// regionName = regionName.replace(" ", "
									// ");
									regionId = regionService.getIdByName(regionName,cityId);
									if(!StringUtil.isEmpty(regionId)){
										hospitalInfoModel.setRegion(regionId);
									}else{
										hospitalInfoModel.setRegion(regionName);
									}
									// 医院级别
									String hospitalLevelName = "";
									String hospitalLevel = "";
									hospitalLevelName = hospitalInfoModel.getHospitalLevel();
									hospitalLevel = this.getHospitalLevelByHospitalLevelName(hospitalLevelName.trim());

									// 把表格中的信息保存到数据库
									hospitalInfoModel.setAddress(address);
									hospitalInfoModel.setHospitalLevel(hospitalLevel);
									
									//获取是否存在
									String uuid = getUuidByHospitalName(hospitalInfoModel.getHospitalName());
									//如果表中已存在修改 
									if(StringUtil.isEmpty(uuid)){
										// 创建时间
										hospitalInfoModel.setCreateTime(DateFormatHelper.getNowTimeStr());
										// 创建医院
										this.create(hospitalInfoModel);
									}else{
										this.update(hospitalInfoModel);
									}
								}
							}
						}
					}
					return true;
				} catch (IOException e) {
					e.printStackTrace();
				} finally {

				}
			}
		}
		return false;
	}

	/**
	 * 根据医院级别汉字，得到编号
	 * 
	 * @param hospitalLevelName
	 * @return
	 */
	private String getHospitalLevelByHospitalLevelName(String hospitalLevelName) {
		switch (hospitalLevelName) {
		case "一级综合":
			return HospitalInfoLevelConst.first;
		case "二级综合":
			return HospitalInfoLevelConst.second;
		case "三级综合":
			return HospitalInfoLevelConst.third;
		case "一级精神专科":
			return HospitalInfoLevelConst.first_js;
		case "二级精神专科":
			return HospitalInfoLevelConst.second_js;
		case "三级精神专科":
			return HospitalInfoLevelConst.third_js;
		case "综合":
			return HospitalInfoLevelConst.zh;
		case "精神专科":
			return HospitalInfoLevelConst.js;
		}
		return "";
	}

	/**
	 * 通过区id得到医院
	 * 
	 * @param regionUuid
	 * @return
	 */
	@Override
	public List<HospitalInfoModel> getByRegionUuid(String regionUuid) {
		return myDao.getByRegionUuid(regionUuid);
	}

	/**
	 * 
	 * @Description: (根据医院的名称获取医院的uuids)
	 * @author XP
	 * @param hospitalName
	 * @return
	 * @date 2015-12-31 下午6:37:38
	 */
	@Override
	public List<String> getAllUuidsByHospitalName(String hospitalName) {
		return myDao.getAllUuidsByHospitalName(hospitalName);
	}

	@Override
	public List<HospitalInfoModel> getByCityUuid(String regionUuid) {
		return myDao.getByCityUuid(regionUuid);
	}

	@Override
	public List<HospitalInfoModel> getByProvinceUuid(String provinceUuid,String cityUuid,String regionUuid) {
		return myDao.getByProvinceUuid(provinceUuid, cityUuid, regionUuid);
	}

}