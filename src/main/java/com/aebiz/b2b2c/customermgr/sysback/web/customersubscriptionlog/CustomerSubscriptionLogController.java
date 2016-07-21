package com.aebiz.b2b2c.customermgr.sysback.web.customersubscriptionlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.service.CustomerSubscriptionLogService;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo.CustomerSubscriptionLogModel;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo.CustomerSubscriptionLogQueryModel;
import com.aebiz.b2b2c.customermgr.sysback.web.customersubscriptionlog.vo.CustomerSubscriptionLogWebModel;

@Controller
@RequestMapping("/sysback/customersubscriptionlog")
public class CustomerSubscriptionLogController
		extends
		BaseController<CustomerSubscriptionLogModel, CustomerSubscriptionLogQueryModel> {
	private CustomerSubscriptionLogService myService;
	@Autowired
	public UuidService us;

	@Autowired
	public void setMyService(CustomerSubscriptionLogService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public CustomerSubscriptionLogController() {
		super("customermgr/sysback/customersubscriptionlog",
				"CustomerSubscriptionLog",
				CustomerSubscriptionLogController.class);
	}

	/**
	 * 通过会员编号查询出该会员所有订阅的详细信息
	 * 
	 * @param uuid
	 *            会员uuid
	 */
	@RequestMapping(value = { "/toDetail/{uuid}" }, method = { RequestMethod.GET })
	public String queryListByUuid(@PathVariable("uuid") String uuid, Model model) {
		CustomerSubscriptionLogWebModel webModel = new CustomerSubscriptionLogWebModel();
		webModel.setCustomerUuid(uuid);
		model.addAttribute("wm", webModel);
		return "customermgr/sysback/customersubscriptionlog/CustomerSubscriptionLogList";
	}
}