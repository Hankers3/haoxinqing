package com.aebiz.b2b2c.servicestaff.sysback.web.servicestaffentry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffentry.service.ServicestaffentryService;
import com.aebiz.b2b2c.servicestaff.servicestaffentry.vo.ServicestaffentryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffentry.vo.ServicestaffentryQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.EducationDegreeEnum;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;

@Controller
@RequestMapping("/sysback/servicestaffentry")
public class ServicestaffentryController extends BaseController<ServicestaffentryModel,ServicestaffentryQueryModel>{
	private ServicestaffentryService myService;
	/*家政员Service*/
	@Autowired
	private ServicestaffService servicestaffService;
	
	/*家政员基础信息Service*/
	@Autowired
	private  ServicestaffinfoService  servicestaffinfoService;
	
	
	@Autowired
	public void  setMyService(ServicestaffentryService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public ServicestaffentryController(){
		super("/servicestaff/sysback/servicestaffentry","Servicestaffentry",ServicestaffentryController.class);
	}
	
	
	@Override
   @RequestMapping(value = "/toUpdate/{uuid}", method = RequestMethod.GET)
   public String toUpdate(@PathVariable("uuid") String uuid, Model model,
		HttpServletRequest request) {
	
	   ServicestaffentryModel servicestaffentryModel=myService.getServicestaffentryModelByServicestaffUuid(uuid);
	   
	   String uuida =servicestaffentryModel.getUuid();
	   
	   return super.toUpdate(uuida, model, request);
   }
	
	/**
	 * 跳转到入职信息页面
	 * 
	 * @param model
	 * @param orderMainUuid
	 * @return
	 */
	@RequestMapping(value = "/toUpdateStaff/{uuid}")
	public String toUpdateStaff(Model model,
			@PathVariable("uuid") String servicestaffUuid,
			HttpServletRequest request) {
		
		ServicestaffentryModel m= myService.getServicestaffentryModelByServicestaffUuid(servicestaffUuid);
		
		model.addAttribute("m", m);
		return "servicestaff/sysback/servicestaffentry/EntryToUpdate";

	}
	
	@RequestMapping(value = "/updateSe", method = RequestMethod.POST)
	public String update(Model model,
			@ModelAttribute("m") ServicestaffentryModel m,
			HttpServletRequest request,
			@RequestParam(value = "imgFile", required = false) MultipartFile[] imgFiles,
			@RequestParam(value = "imgFile1", required = false) MultipartFile[] contractImgFiles,
			@RequestParam(value = "whethercomplete", required = false) String[] whethercompletes) {
		
		
		
		myService.updateServicestaffentry(m, imgFiles,contractImgFiles,whethercompletes);
		
		return "servicestaff/sysback/servicestaff/ServicestaffEntryList";
	}
	
	
	/**
	 * 将教育程度发送到新增页面面展示
	 * hedongfei
	 * @param model
	 */
	private void sendEducationShowName(Model model) {
		List<DataTablesPageParam> educationList = new ArrayList<DataTablesPageParam>();

		for (EducationDegreeEnum ed : EducationDegreeEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(ed.getKey());
			dpp.setValue(ed.getValue());
			educationList.add(dpp);
		}
		model.addAttribute("educationList", educationList);
	}

	/**个人档案
	 * 根据用户uuid查看此用户档案
	 * hedongfei
	 * @param 
	 * @param 
	 * @return
	 * @throws
	 */
	@RequestMapping("/checkPersonalFile/{uuid}")
	public String frozen(
				@PathVariable("uuid") String uuid,
				Model model,
			HttpServletResponse response) throws IOException {
		// 会员学历展示数据
		sendEducationShowName(model);
		
	   /*家政员对象*/
		ServicestaffModel sm=servicestaffService.getServicestaffModelByUuid(uuid);
		model.addAttribute("d", sm);
		
		ServicestaffinfoModel sim=servicestaffinfoService.getServicestaffinfoModelByServicestaffUuid(uuid);
		model.addAttribute("c", sim);
		
		
		return "/servicestaff/sysback/servicestaffentry/PersonalFile";
	}
	
}