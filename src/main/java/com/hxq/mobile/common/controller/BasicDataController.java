package com.hxq.mobile.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.hxq.mobile.common.service.BasicDataService;
import com.hxq.mobile.common.service.AdvertisementService;
import com.hxq.mobile.entity.common.BasicdataAreaCity;
import com.hxq.mobile.entity.common.BasicdataAreaProvince;
import com.hxq.mobile.entity.common.BasicdataAreaRegion;
import com.hxq.mobile.entity.common.DepartmentInfo;
import com.hxq.mobile.entity.common.HospitalInfo;
import com.hxq.mobile.entity.common.Tags;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.ObjectUtils;

/**
 * Created by Alice on 2016/5/3 0003.
 */
@Controller
public class BasicDataController {
	Logger log = LoggerFactory.getLogger(BasicDataController.class);

	@Resource(name = "com.hxq.mobile.common.service.BasicDataService")
	private BasicDataService basicDataService;
	@Resource(name = "com.hxq.mobile.common.service.AdvertisementService")
	private AdvertisementService advertisementService;

	/**
	 * 获取省份
	 * 
	 * @return ApiResult
	 */
	@RequestMapping("/app/service/doctor/2.0/getProvince")
	public @ResponseBody ApiResult getProvince_v2() {
		try {
			Map<String, Object> mapReturn = null;
			List<Map<String, Object>> relist = new ArrayList<>();
			List<BasicdataAreaProvince> rows = basicDataService.selectAllProvince();
			if (rows != null && rows.size() > 0) {
				for (BasicdataAreaProvince pm : rows) {
					mapReturn = new HashMap<String, Object>();
					mapReturn.put("provinceName", pm.getProvinceName());
					mapReturn.put("code", pm.getUuid());
					relist.add(mapReturn);
				}
			}
			return ApiResult.right(relist);
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * 获取市
	 * @param provinceUuid
	 *            省份id
	 * @return
	 */
	@RequestMapping(value = "/app/service/doctor/2.0/getCity", method = RequestMethod.GET)
	public @ResponseBody ApiResult getCity_v2(@RequestParam("provinceUuid") String provinceUuid) {
		if (ObjectUtils.isEmpty(provinceUuid)) return ApiResult.error(ApiCode.BAD_REQUEST, "请指定省份id");
		try {
			Map<String, Object> mapReturn = null;
			List<BasicdataAreaCity> rows = basicDataService.selectCityByProvinceUuid(provinceUuid);
			List<Map<String, Object>> relist = new ArrayList<>(); // 要返回的List
			if (rows != null && rows.size() > 0) {
				for (BasicdataAreaCity cm : rows) {
					mapReturn = new HashMap<String, Object>();
					mapReturn.put("cityName", cm.getCityName());
					mapReturn.put("code", cm.getUuid());
					relist.add(mapReturn);
				}
			}
			return ApiResult.right(relist);
		} catch (Exception e) {
			log.error(provinceUuid, e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * 得到区/县的集合
	 * @param cityUuid
	 *            城市id
	 * @return ApiResult
	 */
	@RequestMapping(value = "/app/service/doctor/2.0/getRegion", method = RequestMethod.GET)
	public @ResponseBody ApiResult getRegion_v2(@RequestParam("cityUuid") String cityUuid) {
		if (ObjectUtils.isEmpty(cityUuid)) return ApiResult.error(ApiCode.BAD_REQUEST, "请指定城市id");
		try {
			Map<String, Object> mapReturn = null;
			List<BasicdataAreaRegion> rows = basicDataService.selectRegionByCityId(cityUuid);
			List<Map<String, Object>> relist = new ArrayList<>();
			if (rows != null && rows.size() > 0) {
				for (BasicdataAreaRegion rm : rows) {
					mapReturn = new HashMap<String, Object>();
					mapReturn.put("regionName", rm.getRegionName());
					mapReturn.put("code", rm.getUuid());
					relist.add(mapReturn);
				}
			}
			return ApiResult.right(relist);
		} catch (Exception e) {
			log.error(cityUuid, e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * 获取医院列表接口
	 */
	@RequestMapping(value = "/app/service/doctor/2.0/getHospital", method = RequestMethod.GET)
	public @ResponseBody ApiResult getHospital_v2(
			@RequestParam(value="provinceUuid",required=false) String provinceUuid,
			@RequestParam(value="cityUuid",required=false) String cityUuid,
			@RequestParam(value="regionUuid",required=false) String regionUuid) {
		try {
			Map<String, Object> mapReturn = null;
			List<HospitalInfo> rows = basicDataService.selectHospital(provinceUuid, cityUuid, regionUuid);
			List<Map<String, Object>> relist = new ArrayList<>();
			if (rows != null && rows.size() > 0) {
				for (HospitalInfo him : rows) {
					mapReturn = new HashMap<String, Object>();
					mapReturn.put("hospitalName", him.getHospitalName());
					mapReturn.put("id", him.getUuid());
					relist.add(mapReturn);
				}
			}
			// FIXME: 2016/3/4 0004 添加其他医院 临时字符串为1
			if (!StringUtil.isEmpty(cityUuid)) {
				mapReturn = new HashMap<String, Object>();
				mapReturn.put("hospitalName", "其它医院");
				mapReturn.put("id", 1);
				relist.add(mapReturn);
			}
			return ApiResult.right(relist);
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * 获取科室
	 */
	@RequestMapping(value = "/app/service/doctor/2.0/getDepartment", method = RequestMethod.GET)
	public @ResponseBody ApiResult getDepartment() {
		try {
			Map<String, Object> mapReturn = null;
			List<DepartmentInfo> rows = basicDataService.selectAllDepartment();
			List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
			if (rows != null && rows.size() > 0) {
				for (DepartmentInfo info : rows) {
					mapReturn = new HashMap<String, Object>();
					mapReturn.put("departmentName", info.getDepartmentName());
					mapReturn.put("id", info.getUuid());
					relist.add(mapReturn);
				}
			}
			return ApiResult.right(relist);
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * 获取标签
	 */
	@RequestMapping(value = "/app/service/doctor/2.0/getTags", method = RequestMethod.GET)
	public @ResponseBody ApiResult getTags() {
		try {
			Map<String, Object> mapReturn = null;
			List<Tags> tagses = basicDataService.selectAllTags();
			List<Map<String, Object>> relist = new ArrayList<>();
			if (tagses != null && tagses.size() > 0) {
				for (Tags tag : tagses) {
					mapReturn = new HashMap<String, Object>();
					mapReturn.put("tagName", tag.getTagName());
					mapReturn.put("tagUuid", tag.getUuid());
					relist.add(mapReturn);
				}
			}
			return ApiResult.right(relist);
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}
}
