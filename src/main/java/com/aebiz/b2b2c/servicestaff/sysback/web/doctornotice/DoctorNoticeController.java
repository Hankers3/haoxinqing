package com.aebiz.b2b2c.servicestaff.sysback.web.doctornotice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.servicestaff.doctornotice.service.DoctorNoticeService;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeModel;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcomb.vo.ServicestaffcombModel;

@Controller
@RequestMapping("/sysback/doctornotice")
public class DoctorNoticeController extends
		BaseController<DoctorNoticeModel, DoctorNoticeQueryModel> {
	private DoctorNoticeService myService;

	@Autowired
	public void setMyService(DoctorNoticeService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public DoctorNoticeController() {
		super("servicestaff/sysback/doctornotice", "DoctorNotice",
				DoctorNoticeController.class);
	}

	/**
	 * 查看医生公告信息
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toView/{uuid}")
	public String toView(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		// 通过id获得model
		DoctorNoticeModel doctorNoticeModel = myService
				.getDoctorNoticeModel(uuid);

		preparedUpdateData(model, request);

		model.addAttribute("m", doctorNoticeModel);
		return "servicestaff/sysback/doctornotice/DoctorNoticeUpdate";
		
		
	}
	
	
	@RequestMapping(value = "/deleteById/{uuid}", method = RequestMethod.GET)
	@ResponseBody
	public String deleteById(
			@PathVariable("uuid") String  uuid){
		
		 myService.deleteById(uuid);
		
		 return "success";
	}
	
	
}