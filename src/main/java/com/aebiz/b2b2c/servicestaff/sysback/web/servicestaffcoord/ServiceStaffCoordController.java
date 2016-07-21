package com.aebiz.b2b2c.servicestaff.sysback.web.servicestaffcoord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.service.ServiceStaffCoordService;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo.ServiceStaffCoordModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo.ServiceStaffCoordQueryModel;



@Controller
@RequestMapping("/sysback/servicestaffcoord")
public class ServiceStaffCoordController extends BaseController<ServiceStaffCoordModel,ServiceStaffCoordQueryModel>{
	private ServiceStaffCoordService myService;
	@Autowired
	public void  setMyService(ServiceStaffCoordService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public ServiceStaffCoordController(){
		super("basebusiness/systembackmgr/servicestaffcoord","ServiceStaffCoord",ServiceStaffCoordController.class);
	}
}