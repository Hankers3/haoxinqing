package com.aebiz.b2b2c.servicestaff.sysback.web.servicestaffinfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoQueryModel;

@Controller
@RequestMapping("/sysback/servicestaffinfo")
public class ServicestaffinfoController extends
		BaseController<ServicestaffinfoModel, ServicestaffinfoQueryModel> {
	private ServicestaffinfoService myService;

	@Autowired
	public void setMyService(ServicestaffinfoService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public ServicestaffinfoController() {
		super("/servicestaff/sysback/servicestaffinfo", "Servicestaffinfo",
				ServicestaffinfoController.class);
	}

	/**
	 * 根据传入的医生分类信息判断医生分类中是否已经存在医生
	 * 
	 * @param checkIds
	 *            医生分类uuid
	 * @return
	 */
	@RequestMapping(value = { "/checkName" }, method = { RequestMethod.GET })
	@ResponseBody
	public String checkName(@RequestParam("checkIds") List<String> checkIds,
			HttpServletRequest request, HttpServletResponse response) {
		if (myService.checkDoctorCategory(checkIds)) {
			return "true";
		}
		return "false";
	}
}