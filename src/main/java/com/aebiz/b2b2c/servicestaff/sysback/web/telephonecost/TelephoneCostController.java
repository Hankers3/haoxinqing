package com.aebiz.b2b2c.servicestaff.sysback.web.telephonecost;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.telephonecost.service.TelephoneCostService;
import com.aebiz.b2b2c.servicestaff.telephonecost.vo.TelephoneCostModel;
import com.aebiz.b2b2c.servicestaff.telephonecost.vo.TelephoneCostQueryModel;
import com.aebiz.b2b2c.servicestaff.telephonetime.vo.TelephoneTimeModel;



@Controller
@RequestMapping("/sysback/telephonecost")
public class TelephoneCostController extends BaseController<TelephoneCostModel,TelephoneCostQueryModel>{
	private TelephoneCostService myService;
	@Autowired
	public void  setMyService(TelephoneCostService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public TelephoneCostController(){
		super("servicestaff/sysback/telephonecost","TelephoneCost",TelephoneCostController.class);
	}
	
	
	
	/**
	 * 新增咨询费用
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = { "/addTelCost" }, method = { RequestMethod.GET })
	@ResponseBody
	public String toInterviewList(@RequestParam("value") String telCost, HttpServletRequest request){
		
		TelephoneCostModel tm = new TelephoneCostModel();
		
		if(!StringUtil.isEmpty(telCost)){
			tm.setTelCost(telCost);
			myService.create(tm);
			}
		
		return "success";
	}
	
}