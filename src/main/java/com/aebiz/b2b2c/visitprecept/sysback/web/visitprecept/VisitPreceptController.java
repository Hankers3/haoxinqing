package com.aebiz.b2b2c.visitprecept.sysback.web.visitprecept;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.visitprecept.visitapply.service.VisitApplyService;
import com.aebiz.b2b2c.visitprecept.visitprecept.service.VisitPreceptService;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptModel;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptQueryModel;

@Controller
@RequestMapping("/sysback/visitprecept")
public class VisitPreceptController extends
		BaseController<VisitPreceptModel, VisitPreceptQueryModel> {
	private VisitPreceptService myService;

	@Autowired
	public void setMyService(VisitPreceptService bs) {
		this.myService = bs;
		super.setBs(bs);
	}
	// 注入随访记录的service
	@Autowired
	private VisitApplyService visitApplyService;

	// 注入患者的service
	@Autowired
	private CustomerService customerService;
	
	// 注入医生的service
	@Autowired
	private ServicestaffService staffService;

	public VisitPreceptController() {
		super("/visitprecept/sysback/visitprecept", "VisitPrecept",
				VisitPreceptController.class);
	}

	/**
	 * 查看方案管理详细信息
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toDetail/{uuid}")
	public String toView(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		// 通过id获得model
		VisitPreceptModel visitPreceptModel = myService.getByUuid(uuid);
		model.addAttribute("m", visitPreceptModel);

		//获取医生编号 
		String doctorUuid = visitPreceptModel.getServiceStaffUuid();
		ServicestaffModel staffModel =  staffService.getByUuid(doctorUuid);
		model.addAttribute("staff", staffModel);
		// 根据记录的uuid值来查询随访表单记录 获取客户信息的ID值得list
		List<String> customerIds = visitApplyService.getCustomerIds(uuid);

		// 根据患者的ids来获取患者的所有信息并将信息存放到customerList返回到页面
		if (customerIds != null && customerIds.size() > 0) {
			List<CustomerModel> customerList = customerService.getAllCustomerByUuids(customerIds);
			model.addAttribute("customerList", customerList);
		}

		return "visitprecept/sysback/visitprecept/VisitPreceptDetail";
	}
}