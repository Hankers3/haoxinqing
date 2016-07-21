package com.aebiz.b2b2c.basicdata.sysback.web.province;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.basicdata.city.vo.CityModel;
import com.aebiz.b2b2c.basicdata.city.vo.CityQueryModel;
import com.aebiz.b2b2c.basicdata.province.service.ProvinceService;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceModel;
import com.aebiz.b2b2c.basicdata.province.vo.ProvinceQueryModel;
import com.aebiz.b2b2c.basicdata.region.service.RegionService;

@Controller
@RequestMapping("/sysback/province")
public class ProvinceController extends
		BaseController<ProvinceModel, ProvinceQueryModel> {
	private ProvinceService myService;

	@Autowired
	private CityService cityService;

	@Autowired
	private RegionService regionService;

	@Autowired
	public void setMyService(ProvinceService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public ProvinceController() {
		super("basicdata/sysback/province", "Province",
				ProvinceController.class);
	}

	@RequestMapping("/testprovince")
	public String test(HttpServletRequest request) {

		List<ProvinceModel> provinceList = myService
				.getByCondition(new ProvinceQueryModel());
		String provinces = "";
		for (ProvinceModel pm : provinceList) {
			provinces = provinces + "province.add('" + pm.getUuid() + "','"
					+ pm.getProvinceName() + "','') ;";
		}

		System.out.println("start=========================start");

		System.out.println(provinces);

		System.out.println("end=========================end");
		return "";
	}

	@RequestMapping("/testcitys")
	public String testcitys(HttpServletRequest request) {

		List<CityModel> provinceList = cityService
				.getByCondition(new CityQueryModel());

		String citys = "";
		for (CityModel pm : provinceList) {
			citys = citys + "city.add('" + pm.getUuid() + "','"
					+ pm.getCityName() + "','" + pm.getProvinceUuid() + "') ;";
		}

		System.out.println("start=========================start");

		System.out.println(citys);

		System.out.println("end=========================end");

		return "";
	}


	@RequestMapping("/test1")
	public String test1(HttpServletRequest request) {
		return "basicdata/sysback/test";
	}
	
	
	@RequestMapping(value = "/pList", method = RequestMethod.GET)
	public String provinceList(Model model, HttpServletRequest request){
		
		return "basicdata/sysback/province/ProvinceSetList";
		
	}
	
	@Override
	@RequestMapping("/queryList")
	public String queryList(HttpServletResponse arg0, HttpServletRequest arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return super.queryList(arg0, arg1);
	}
}