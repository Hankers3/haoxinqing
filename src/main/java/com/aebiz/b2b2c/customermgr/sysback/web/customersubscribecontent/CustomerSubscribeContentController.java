package com.aebiz.b2b2c.customermgr.sysback.web.customersubscribecontent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.service.CustomerSubscribeContentService;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentModel;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentQueryModel;

@Controller
@RequestMapping("/sysback/customersubscribecontent")
public class CustomerSubscribeContentController<M>
		extends
		BaseController<CustomerSubscribeContentModel, CustomerSubscribeContentQueryModel> {
	private CustomerSubscribeContentService myService;

	@Autowired
	public void setMyService(CustomerSubscribeContentService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerSubscribeContentController() {
		super("customermgr/sysback/customersubscribecontent",
				"CustomerSubscribeContent",
				CustomerSubscribeContentController.class);
	}

	/**
	 * 校验订阅编号是否存在
	 * 
	 * @param subscribeNo
	 *            订阅编号
	 * @param uuid
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/checkSubscribeNo" }, method = { RequestMethod.GET })
	@ResponseBody
	public String checkSubscribeNoExist(
			@RequestParam("subscribeNo") String subscribeNo,
			@RequestParam("uuid") String uuid) {
		if (this.myService.checkSubscribeNoExist(subscribeNo, uuid)) {
			return "true";
		} else {
			return "false";
		}
	}
}