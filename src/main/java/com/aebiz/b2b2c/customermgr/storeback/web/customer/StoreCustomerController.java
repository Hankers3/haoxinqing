package com.aebiz.b2b2c.customermgr.storeback.web.customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;
import com.aebiz.b2b2c.customermgr.customercomb.service.CustomerCombService;
import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.EducationDegreeEnum;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.IncomeRangeEnum;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.IndustryEnum;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelModel;
import com.aebiz.b2b2c.customermgr.storeback.service.customer.StoreCustomerService;

/**
 * 商户后台会员管理controller
 * 
 * @author likun
 * 
 */
@Controller
@RequestMapping("/store/customer")
public class StoreCustomerController extends
		BaseController<CustomerModel, CustomerQueryModel> {
	private StoreCustomerService myService;
	@Autowired
	private CustomerCombService customerCombService;

	@Autowired
	public void setMyService(StoreCustomerService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public StoreCustomerController() {
		super("customermgr/storeback/customer", "Customer",
				StoreCustomerController.class);
	}

	@Override
	public void preparedListData(Model model, HttpServletRequest request) {
		// 调用商户接口获取商户编号
		String storeUuid = "store01";
		// 将商户会员等级发送到页面展示
		sendCustomerStoreLevel(model, storeUuid);
	}

	/**
	 * 将商户的会员等级发送到页面
	 */
	private void sendCustomerStoreLevel(Model model, String storeUuid) {
		List<CustomerStoreLevelModel> customerStoreLevelList = this.myService
				.getCustomerStoreLevelsByStoreUuid(storeUuid);
		model.addAttribute("storeLevelList", customerStoreLevelList);
	}

	/**
	 * 跳转到会员查看页面
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toView/{uuid}", method = RequestMethod.GET)
	public String toView(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		CustomerCombModel customerCombModel = customerCombService
				.getCustomerCombModelByCustomerUuid(uuid);
		// 需要调用商户接口获取当前登录的商户编号
		String storeUuid = "store01";
		// 通过会员编号和商户编号获取该会员的商户会员等级
		CustomerStoreLevelModel customerStoreLevelModel = this.myService
				.getCustomerStoreLevel(uuid, storeUuid);
		//customerCombModel.setCustomerStoreLevelModel(customerStoreLevelModel);

		preparedUpdateData(model, request);

		model.addAttribute("m", customerCombModel);

		return "customermgr/storeback/customer/CustomerView";
	}

	@Override
	protected void preparedUpdateData(Model model, HttpServletRequest request) {
		sendIncomeShowName(model);
		sendEducationShowName(model);
		sendIndustryShowName(model);
	}

	// =====================================================================私有方法==========================================================================================
	/**
	 * 将会员收入展示数据发送到页面
	 * 
	 * @param model
	 */
	private void sendIncomeShowName(Model model) {
		List<DataTablesPageParam> incomeList = new ArrayList<DataTablesPageParam>();

		for (IncomeRangeEnum ir : IncomeRangeEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(ir.getKey());
			dpp.setValue(ir.getValue());
			incomeList.add(dpp);
		}
		model.addAttribute("incomeList", incomeList);
	}

	/**
	 * 将会员的教育程度发送到会员编辑页面展示
	 * 
	 * @param model
	 */
	private void sendEducationShowName(Model model) {
		List<DataTablesPageParam> educationList = new ArrayList<DataTablesPageParam>();

		for (EducationDegreeEnum ed : EducationDegreeEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(ed.getKey());
			dpp.setValue(ed.getValue());
			educationList.add(dpp);
		}
		model.addAttribute("educationList", educationList);
	}

	/**
	 * 将会员所在行业展示数据发送到前台页面展示
	 * 
	 * @param model
	 */
	private void sendIndustryShowName(Model model) {
		List<DataTablesPageParam> industryList = new ArrayList<DataTablesPageParam>();

		for (IndustryEnum ct : IndustryEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(ct.getKey());
			dpp.setValue(ct.getValue());
			industryList.add(dpp);
		}
		model.addAttribute("industryList", industryList);
	}
}