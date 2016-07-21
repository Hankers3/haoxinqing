package com.aebiz.b2b2c.servicestaff.sysback.web.doctorgroup;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.servicestaff.doctorgroup.service.DoctorGroupService;
import com.aebiz.b2b2c.servicestaff.doctorgroup.vo.DoctorGroupModel;
import com.aebiz.b2b2c.servicestaff.doctorgroup.vo.DoctorGroupQueryModel;
import com.aebiz.b2b2c.servicestaff.groupfriends.service.GroupFriendsService;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsModel;

@Controller
@RequestMapping("/sysback/doctorgroup")
public class DoctorGroupController extends BaseController<DoctorGroupModel,DoctorGroupQueryModel>{
	private DoctorGroupService myService;
	
		//	注入医生好友圈的service
	private GroupFriendsService groupFriendsService;
	
	@Autowired
	public void  setGroupFriendsService(GroupFriendsService groupFriendsService){
		this.groupFriendsService = groupFriendsService;
	}
	
	
	@Autowired
	public void  setMyService(DoctorGroupService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public DoctorGroupController(){
		super("servicestaff/sysback/doctorgroup","DoctorGroup",DoctorGroupController.class);
	}
	
	
	
	
	/**
	 * 根据doctorUuid值查询用户好友的列表
	 * @param uuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/toView/{doctorUuid}"})
	public String toView(@PathVariable("doctorUuid") String doctorUuid, Model model){

		model.addAttribute("doctorUuid", doctorUuid);
	
		return "servicestaff/sysback/groupfriends/GroupFriendsList";
		
	}
	
//	/**
//	 * 根据doctorUuid值查询用户好友的列表
//	 * @param uuid
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = { "/toView/{doctorUuid}"})
//	public String toView(@PathVariable("doctorUuid") String doctorUuid, Model model){
//
//		model.addAttribute("doctorUuid", doctorUuid);
//	
//		return "servicestaff/sysback/doctorgroup/DoctorGroupView";
//		
//	}
}