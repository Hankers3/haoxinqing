package com.aebiz.b2b2c.order.sysback.web.ordermaincomment;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.order.ordermaincomment.service.OrderMainCommentService;
import com.aebiz.b2b2c.order.ordermaincomment.vo.OrderMainCommentModel;
import com.aebiz.b2b2c.order.ordermaincomment.vo.OrderMainCommentQueryModel;

@Controller
@RequestMapping("/sysback/ordermaincomment")
public class OrdermainCommentController extends BaseController<OrderMainCommentModel,OrderMainCommentQueryModel>{
	private OrderMainCommentService myService;
	@Autowired
	public void  setMyService(OrderMainCommentService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public OrdermainCommentController(){
		super("/order/sysback/ordermaincomment","Ordermaincomment",OrdermainCommentController.class);
	}
	
	

	@RequestMapping(value = "/updateconductIdea", method = RequestMethod.GET)
	@ResponseBody
	public String updateconductIdea(
			@RequestParam("uuid") String uuid,
			@RequestParam("conductIdea") String conductIdea,
			Model model,
			HttpServletRequest request) {
	
		this.myService.updateconductIdea(uuid, conductIdea);
		
		
		return "true";
	}
	
	
	
	
	
}



   