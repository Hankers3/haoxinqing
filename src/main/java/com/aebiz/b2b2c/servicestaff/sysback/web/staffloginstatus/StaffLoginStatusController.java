package com.aebiz.b2b2c.servicestaff.sysback.web.staffloginstatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.service.StaffLoginStatusService;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.vo.StaffLoginStatusModel;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.vo.StaffLoginStatusQueryModel;



@Controller
@RequestMapping("/sysback/staffloginstatus")
public class StaffLoginStatusController extends BaseController<StaffLoginStatusModel,StaffLoginStatusQueryModel>{
	private StaffLoginStatusService myService;
	@Autowired
	public void  setMyService(StaffLoginStatusService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public StaffLoginStatusController(){
		super("basebusiness/systembackmgr/staffloginstatus","StaffLoginStatus",StaffLoginStatusController.class);
	}
}