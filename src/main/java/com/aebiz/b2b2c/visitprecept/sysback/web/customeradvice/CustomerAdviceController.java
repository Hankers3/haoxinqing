package com.aebiz.b2b2c.visitprecept.sysback.web.customeradvice;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.visitprecept.customeradvice.service.CustomerAdviceService;
import com.aebiz.b2b2c.visitprecept.customeradvice.vo.CustomerAdviceModel;
import com.aebiz.b2b2c.visitprecept.customeradvice.vo.CustomerAdviceQueryModel;
/**
 * 后台  意见反馈
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/sysback/customeradvice")
public class CustomerAdviceController extends BaseController<CustomerAdviceModel,CustomerAdviceQueryModel>{
	private CustomerAdviceService myService;
	@Autowired
	public void  setMyService(CustomerAdviceService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public CustomerAdviceController(){
		super("visitprecept/sysback/customeradvice","CustomerAdvice",CustomerAdviceController.class);
	}
	
	@Transient
	private static CustomerService  customerService;
	
	@Autowired
	public void setCustomerService(
			CustomerService  customerService) {
		this.customerService = customerService;
	}
	
	/**
	 * 通过uuid将处理意见保存到数据库
	 * @param customerUuid  当前数据的uuid
	 * @param refundConten   处理意见
	 * @return
	 */
	@RequestMapping("/toUpdate")
	@ResponseBody
	public String toUpdate(
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("refundConten") String refundConten) {

		if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(refundConten)) {
			this.myService.toUpdate(customerUuid,refundConten);
			return "true";
		}
		return "false";
	}
	
	
	
}