package com.aebiz.b2b2c.virtualaccount.sysback.web.virtualaccount.virtualaccountcustomerlog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service.VirtualAccountCustomerLogService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogQueryModel;

@Controller
@RequestMapping("/sysback/virtualaccountlog")
public class VirtualAccountLogController
		extends
		BaseController<VirtualAccountCustomerLogModel, VirtualAccountCustomerLogQueryModel> {
	private VirtualAccountCustomerLogService myService;

	@Autowired
	public void setMyService(VirtualAccountCustomerLogService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public VirtualAccountLogController() {
		super("virtualaccount/sysback/virtualaccountcustomerlog",
				"VirtualAccountCustomerLog",
				VirtualAccountLogController.class);
	}

	// 注入财务系统的会员账户对外接口
	@Autowired
	private CustomerAccountInteractive accountInteractive;

	// 注入患者的service
	@Autowired
	private CustomerService customerService;

	// 注入患者的service
	@Autowired
	private CustomerInfoService customerInfoService;

	/**
	 * 查询所有消费记录
	 * 
	 * @param wm
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/accountLogslist/{nowPage}/{pageShow}", method = { RequestMethod.GET })
	public String accountLogslist(@ModelAttribute("wm") BaseWebModel wm,
			Model model, HttpServletRequest request,
			@PathVariable("nowPage")int nowPage,
			@PathVariable("pageShow")int pageShow) {

		VirtualAccountCustomerLogQueryModel qm = this.getQueryModel();
		// 判断是否需要初始化查询
		String init = request.getParameter("init");
		if (!"true".equalsIgnoreCase(init)) {
			Object obj = SecurityUtils.getSubject().getSession()
					.getAttribute("VirtualAccountIsQueryQs");
			if ((obj != null) && (obj.toString().equals("true"))) {
				qm = (VirtualAccountCustomerLogQueryModel) SecurityUtils
						.getSubject().getSession()
						.getAttribute("VirtualAccountCustomerLogQueryModel");
			}
		}

		qm = preparedQMFixValue(qm);

		List<VirtualAccountCustomerLogModel> list = myService.getByCondition(
				qm, wm.getFromNum(), wm.getPageShow());
		wm.setRows(list);
		wm.setTotalNum(myService.getCount(qm));
		this.preparedListData(model, request);
		return "virtualaccount/sysback/virtualaccountcustomerlog/VirtualAccountCustomerLogList";
	}

	/**
	 * 根据会员id获取该会员所有的充值记录
	 * 
	 * @param customerUuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/searchCusstomerLogs/{uuid}", method = RequestMethod.GET)
	public String searchCusstomerLogs(@PathVariable("uuid") String uuid,
			Model model, HttpServletRequest request) {
		String customerUuid = myService.getCustomerUuidByLogsUuid(uuid);
		VirtualAccountCustomerLogModel vac = myService.getByUuid(uuid);
		model.addAttribute("vac", vac);
		if (!StringUtil.isEmpty(customerUuid)) {
			CustomerModel cm = customerService.getByUuid(customerUuid);
			if (cm != null) {
				model.addAttribute("cm", cm);
			}
		}
		return "virtualaccount/sysback/virtualaccountcustomerlog/VirtualAccountCustomerLogListView";
	}

	/**
	 * 跳转到患者消费明细页面
	 */
	@RequestMapping(value = { "/toLogView/{uuid}" }, method = { RequestMethod.GET })
	public String toLogView(Model model, @PathVariable("uuid") String uuid,
			HttpServletRequest request) {

		VirtualAccountCustomerLogModel vm = myService.getByUuid(uuid);
		String customerUuid = vm.getCustomerUuid();
		if (!StringUtil.isEmpty(customerUuid)) {
			CustomerModel cm = customerService.getByUuid(customerUuid);
			CustomerInfoModel cim = customerInfoService
					.getCustomerInfoModelByCustomerUuid(customerUuid);
			if (cm != null && cim != null) {
				model.addAttribute("cm", cm);
				model.addAttribute("cim", cim);
			}
			model.addAttribute("vm", vm);
		}
		return "virtualaccount/sysback/virtualaccountcustomerlog/VirtualAccountCustomerLogListView";
	}

}