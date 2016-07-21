package com.aebiz.b2b2c.servicestaff.sysback.web.groupfriends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.servicestaff.groupfriends.service.GroupFriendsService;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsModel;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsQueryModel;

@Controller
@RequestMapping("/sysback/groupfriends")
public class GroupFriendsController extends BaseController<GroupFriendsModel,GroupFriendsQueryModel>{
	
	
	private GroupFriendsService myService;
	@Autowired
	public void  setMyService(GroupFriendsService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public GroupFriendsController(){
		super("servicestaff/sysback/groupfriends","GroupFriends",GroupFriendsController.class);
	}
	
	
	
	
}