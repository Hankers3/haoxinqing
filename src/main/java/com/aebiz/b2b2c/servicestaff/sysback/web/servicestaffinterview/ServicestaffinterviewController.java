package com.aebiz.b2b2c.servicestaff.sysback.web.servicestaffinterview;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.servicestaff.servicestaffentry.service.ServicestaffentryService;
import com.aebiz.b2b2c.servicestaff.servicestaffentry.vo.ServicestaffentryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinterview.service.ServicestaffinterviewService;
import com.aebiz.b2b2c.servicestaff.servicestaffinterview.vo.ServicestaffinterviewModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinterview.vo.ServicestaffinterviewQueryModel;

@Controller
@RequestMapping("/sysback/servicestaffinterview")
public class ServicestaffinterviewController extends BaseController<ServicestaffinterviewModel,ServicestaffinterviewQueryModel>{
	private ServicestaffinterviewService myService;
	@Autowired
	public void  setMyService(ServicestaffinterviewService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public ServicestaffinterviewController(){
		super("/servicestaff/sysback/servicestaffinterview","Servicestaffinterview",ServicestaffinterviewController.class);
	}
	

	
	/* 入职信息表service */
	@Autowired
	private ServicestaffentryService servicestaffentryService;
	
	
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(@RequestParam("uuid") String uuid, Model model,
			HttpServletRequest request) {
		
		ServicestaffinterviewModel servicestaffinterviewModel=myService.getServicestaffinterviewModelByServicestaffUuid(uuid);
		
		String uuida =servicestaffinterviewModel.getUuid();
		
		return super.toUpdate(uuida, model, request);
	}
	
	
	@Override
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model,
			@ModelAttribute("m") ServicestaffinterviewModel m,
			HttpServletRequest request) {
		
		Boolean a = myService.changefrozenSate(m.getServiceStaffUuid());
		
		if(a==true){	
			//根据家政员编号号获取入职信息
			ServicestaffentryModel servicestaffentryModel=servicestaffentryService.getServicestaffentryModelByServicestaffUuid(m.getServiceStaffUuid());
			//如果此家政员的面试信息已经存在，就不做任何改变！
			if(servicestaffentryModel!=null){
				
			}else{
				myService.createServicestaffentry(m.getServiceStaffUuid());
			}
			
			
			 // return super.update(model, m, request);
			myService.update(m);
			return "servicestaff/sysback/servicestaff/ServicestaffEntryList";
			  
			}else{
				return "";	
			}
		
	}
}