package com.aebiz.b2b2c.servicestaff.sysback.web.telephonetime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.telephonetime.service.TelephoneTimeService;
import com.aebiz.b2b2c.servicestaff.telephonetime.vo.TelephoneTimeModel;
import com.aebiz.b2b2c.servicestaff.telephonetime.vo.TelephoneTimeQueryModel;



@Controller
@RequestMapping("/sysback/telephonetime")
public class TelephoneTimeController extends BaseController<TelephoneTimeModel,TelephoneTimeQueryModel>{
	private TelephoneTimeService myService;
	@Autowired
	public void  setMyService(TelephoneTimeService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public TelephoneTimeController(){
		super("servicestaff/sysback/telephonetime","TelephoneTime",TelephoneTimeController.class);
	}
	
	
	
	/**
	 * 新增咨询时长
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = { "/addTelTime" }, method = { RequestMethod.GET })
	@ResponseBody
	public String toInterviewList(@RequestParam("value") String telTime, HttpServletRequest request){
		
		TelephoneTimeModel tm = new TelephoneTimeModel();
		
		if(!StringUtil.isEmpty(telTime)){
			tm.setTelTime(telTime);
			myService.create(tm);
			}
		
		return "success";
	}
	
	
	
}