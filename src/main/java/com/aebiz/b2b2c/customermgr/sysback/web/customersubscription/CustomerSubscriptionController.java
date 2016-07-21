package com.aebiz.b2b2c.customermgr.sysback.web.customersubscription;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customersubscription.service.CustomerSubscriptionService;
import com.aebiz.b2b2c.customermgr.customersubscription.vo.CustomerSubscriptionModel;
import com.aebiz.b2b2c.customermgr.customersubscription.vo.CustomerSubscriptionQueryModel;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/customersubscription")
public class CustomerSubscriptionController
		extends
		BaseController<CustomerSubscriptionModel, CustomerSubscriptionQueryModel> {
	private CustomerSubscriptionService myService;

	@Autowired
	public void setMyService(CustomerSubscriptionService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerSubscriptionController() {
		super("customermgr/sysback/customersubscription",
				"CustomerSubscription", CustomerSubscriptionController.class);
	}

	/**
	 * 根据会员订阅编号取消会员订阅
	 * 
	 * @param needUpdateUuids
	 */
	@RequestMapping("/updateState")
	public String updateState(
			@RequestParam("selectOne") List<String> needUpdateUuids,
			HttpServletResponse response) throws IOException {
		this.myService.updateSubscriptionState(needUpdateUuids);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rsp", Boolean.valueOf(true));
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(jsonMap));

		return null;
	}
}