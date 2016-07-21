package com.aebiz.b2b2c.basicdata.sysback.web.city;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.basicdata.city.vo.CityModel;
import com.aebiz.b2b2c.basicdata.city.vo.CityQueryModel;
import com.aebiz.b2b2c.basicdata.province.service.ProvinceService;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceModel;
@Controller
@RequestMapping("/sysback/city")
public class CityController<M> extends BaseController<CityModel, CityQueryModel> {
	private CityService myService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	public void setMyService(CityService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CityController() {
		super("basicdata/sysback/city", "City", CityController.class);
	}

	
	/**
	 * 将省份数据发送到页面，供选择查询
	 */
	protected void preparedListData(Model model, HttpServletRequest request) {
		List<ProvinceModel> pmList = provinceService.getAll();
		model.addAttribute("pmList", pmList);
	}

	/**
	 * 跳转到城市启用设置列表页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cList", method = RequestMethod.GET)
	public String setCityList(@RequestParam(value="uuid",required=false) String uuid,
			Model model, HttpServletRequest request) {

		
		model.addAttribute("provinceUuid", uuid);
		return "basicdata/sysback/city/CitySetList";

	}
	
	
		/**
		 * 获得对应城市名称
		 * 
		 * @param cityUuid
		 * @param response
		 * @return
		 * @throws IOException
		 */
		@RequestMapping("/getCityName")
		@ResponseBody
		public String getCityName(
				@RequestParam("cityUuid") String cityUuid,
				HttpServletResponse response) throws IOException {
			
			String cityName = myService.getCityNameById(cityUuid);
			if(!StringUtil.isEmpty(cityName)){
				return cityName;
			}else{
				return "";
			}
			
		}
	
}