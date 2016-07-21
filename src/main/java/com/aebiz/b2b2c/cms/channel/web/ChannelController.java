package com.aebiz.b2b2c.cms.channel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;


import com.aebiz.b2b2c.cms.channel.service.ChannelService;
import com.aebiz.b2b2c.cms.channel.vo.ChannelModel;
import com.aebiz.b2b2c.cms.channel.vo.ChannelQueryModel;

@Controller
@RequestMapping("/sysback/channel")
public class ChannelController extends BaseController<ChannelModel,ChannelQueryModel>{
	private ChannelService myService;
	@Autowired
	public void  setMyService(ChannelService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public ChannelController(){
		super("cms/sysback/channel","channel",ChannelController.class);
	}
}