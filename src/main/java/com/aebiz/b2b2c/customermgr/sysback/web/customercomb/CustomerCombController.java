package com.aebiz.b2b2c.customermgr.sysback.web.customercomb;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.consultrecord.service.ConsultRecordService;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.FrozenTypeEnum;
import com.aebiz.b2b2c.customermgr.customercategory.service.CustomerCategoryService;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryModel;
import com.aebiz.b2b2c.customermgr.customercomb.service.CustomerCombService;
import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombModel;
import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombQueryModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CertTypeEnum;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.EducationDegreeEnum;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.IncomeRangeEnum;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.IndustryEnum;
import com.aebiz.b2b2c.customermgr.customershoplevel.service.CustomerShopLevelService;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;
import com.aebiz.b2b2c.customermgr.customersource.vo.ClientTypeEnum;
import com.aebiz.b2b2c.customermgr.customersource.vo.SiteTypeEnum;
import com.aebiz.b2b2c.customermgr.customersource.vo.ThirdPlatTypeEnum;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordModel;
import com.aebiz.b2b2c.websiteoperation.concern.service.ConcernService;
import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernModel;

/**
 * 会员系统多模块复合控制器
 * 
 * @author likun
 * 
 */
@Controller
@RequestMapping("/sysback/customercomb")
public class CustomerCombController extends
		BaseController<CustomerCombModel, CustomerCombQueryModel> {
	/* 复合service */
	private CustomerCombService myService;

	/* 会员账户信息service */
	@Autowired
	private CustomerService customerService;

	/* 会员平台等级service */
	@Autowired
	private CustomerShopLevelService customerShopLevelService;

	@Autowired
	private ConsultRecordService consultRecordService;
	@Autowired
	private VisitRecordService visitRecordService;

	/* 患者分类service */
	@Autowired
	private CustomerCategoryService customerCategoryService;
	
	@Autowired
	private OrderMainService orderService;
	
	// 患者关注医生service
	@Autowired
	private ConcernService concernService;
	
	public CustomerCombController() {
		super("customermgr/sysback/customer", "Customer",
				CustomerCombController.class);
	}
	
	@Autowired
	public void setMyService(CustomerCombService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	/**
	 * 会员冻结类型展示数据处理，获取会员平台等级
	 */
	@Override
	protected void preparedUpdateData(Model model, HttpServletRequest request) {
		// 会员冻结类型展示数据
		sendFrozenTypeShowName(model);
		// 会员收入展示数据
		sendIncomeShowName(model);
		// 患者分类展示数据
		sendCustomerCategoryList(model);
	}

	private void preparedView(Model model, HttpServletRequest request) {
		// 会员冻结类型展示数据
		sendFrozenTypeShowName(model);
		// 第三方来源展示数据
		sendThirdPlatShowName(model);

	}

	/**
	 * 会员冻结类型展示数据处理
	 */
	@Override
	protected void preparedAddData(Model model, HttpServletRequest request) {
		// 会员收入展示数据
		sendIncomeShowName(model);
		// 会员学历展示数据
		sendEducationShowName(model);
		// 会员所在行业展示数据
		sendIndustryShowName(model);
		// 会员平台等级展示数据
		getCustomerShopLevel(model);
		// 会员冻结类型展示数据
		sendFrozenTypeShowName(model);
	}

	/**
	 * 平台添加会员时，校验校验用户名<br/>
	 * 
	 * @param customerNameValue
	 * @param minLength
	 * @param maxLength
	 */
	public void checkCustomerName(String customerNameValue, int minLength,
			int maxLength) {
		// 校验用户名是否为空
		if (StringUtil.isEmpty(customerNameValue)) {
			this.putErrorMsg("customerModel.customerName", MessageHelper
					.getMessage("customerModel.customerName.empty"));
		} else {
			// 如果不校验最大最小长度则返回
			if (maxLength == 0 || minLength == 0) {
				return;
			}
			// 校验最大长度
			if (customerNameValue.length() > maxLength) {
				this.putErrorMsg(
						"customerModel.customerName",
						MessageHelper
								.getMessage("customerModel.customerName.maxlength")
								+ maxLength);
			}
			// 校验最小长度
			if (customerNameValue.length() < minLength) {
				this.putErrorMsg(
						"customerModel.customerName",
						MessageHelper
								.getMessage("customerModel.customerName.minlength")
								+ minLength);
			}
		}
	}

	/**
	 * 平台添加会员时，校验手机号<br/>
	 * 
	 * @param mobileValue
	 * @param minLength
	 * @param maxLength
	 */
	public void checkMobile(String mobileValue, int minLength, int maxLength) {
		// 校验手机号是否为空
		if (StringUtil.isEmpty(mobileValue)) {
			this.putErrorMsg("customerModel.mobile",
					MessageHelper.getMessage("customerModel.mobile.empty"));
		} else {
			// 不校验最大最小长度则返回
			if (maxLength == 0 || minLength == 0) {
				return;
			}
			// 校验最大长度
			if (mobileValue.length() > maxLength) {
				this.putErrorMsg(
						"customerModel.mobile",
						MessageHelper
								.getMessage("customerModel.mobile.maxlength")
								+ maxLength);
			}
			// 校验最小长度
			if (mobileValue.length() < minLength) {
				this.putErrorMsg(
						"customerModel.mobile",
						MessageHelper
								.getMessage("customerModel.mobile.minlength")
								+ minLength);
			}
		}
	}

	/**
	 * 平台添加会员时，邮箱不是必需项<br/>
	 * 即邮箱可以为空，若邮箱不为空，则需要校验邮箱是否已经存在<br/>
	 * 
	 * @param mobileValue
	 * @param minLength
	 * @param maxLength
	 */
	public void checkEmail(String emailValue, int minLength, int maxLength) {
		// 校验手机号是否存在
		if (!StringUtil.isEmpty(emailValue)) {
			if (customerService.checkEmailExist(emailValue)) {
				this.putErrorMsg("customerModel.email", MessageHelper
						.getMessage("customerModel.mobile.existed"));
			}
		}

	}

	/**
	 * 添加会员时后台校验用户名，手机号是否满足条件
	 */
	@Override
	protected boolean checkAdd(Model model, CustomerCombModel m,
			HttpServletRequest request) {
		String customerName = m.getCustomerModel().getCustomerName();
		String mobile = m.getCustomerModel().getMobile();
		String email = m.getCustomerModel().getEmail();
		// 校验用户名是否存在
		if (customerService.checkCustomerNameExist(customerName)) {
			this.putErrorMsg("customerModel.customerName", MessageHelper
					.getMessage("customerModel.customerName.existed"));
		}

		// 校验手机号是否存在
		if (customerService.checkMobileExist(mobile)) {
			this.putErrorMsg("customerModel.mobile",
					MessageHelper.getMessage("customerModel.mobile.existed"));
		}

		// 校验邮箱是否存在
		if (customerService.checkEmailExist(email)) {
			this.putErrorMsg("customerModel.email",
					MessageHelper.getMessage("customerModel.email.existed"));
		}
		// 校验用户名是否为空和长度
		checkCustomerName(customerName, 3, 20);
		// 校验手机号是否为空
		checkMobile(mobile, 0, 30);
		// 校验邮箱是否为空和长度
		checkEmail(email, 0, 20);
		// 跳转到页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"customermgr/sysback/customer/CustomerAdd");
			return false;
		}
		return true;
	}

	/**
	 * 编辑会员时校验用户名，手机号，邮箱是否满足条件
	 * 
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@Override
	protected boolean checkUpdate(Model model, CustomerCombModel m,
			HttpServletRequest request) {
		// 验证用户名是否为空和长度
		checkCustomerName(m.getCustomerModel().getCustomerName(), 3, 20);
		// 验证手机号是否为空和长度
		checkMobile(m.getCustomerModel().getMobile(), 0, 0);

		// 跳转到页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"customermgr/sysback/customer/CustomerUpdate");
			return false;
		}
		return true;
	}

	/**
	 * 刷新列表的时候需要查询出所有的会员平台等级
	 */
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		// 会员冻结类型展示数据
		sendFrozenTypeShowName(model);
	}

	/**
	 * 查询审核管理
	 */
	@RequestMapping(value = "/toActiveList", method = RequestMethod.GET)
	public String toActiveState(Model model, HttpServletRequest request) {
		preparedListData(model, request);
		return "customermgr/sysback/customer/CustomerActiveList";
	}

	/**
	 * 查询黑名单里的
	 * 
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = { "/toBlackList" }, method = { RequestMethod.GET })
	public String toBlackList(Model model, HttpServletRequest request) {
		preparedListData(model, request);
		return "customermgr/sysback/customer/CustomerToBlackList";
	}

	/**
	 * 添加会员
	 */
	@RequestMapping(value = { "/addCustomer" }, method = { RequestMethod.POST })
	public String addCustomer(Model model,
			@ModelAttribute("m") CustomerCombModel m, HttpServletRequest request) {
		this.getMapErrorMsg().clear();
		if (!checkAdd(model, m, request)) {
			request.setAttribute("ShowMsgs", this.getMapErrorMsg());
			return (String) request.getAttribute(ERROR_BACK_URL);
		}
		cleanQuerySession(request);

		this.myService.createCustomer(m);
		// 将关联的会员的uuid传到下一个修改页面
		model.addAttribute("uuid", m.getCustomerModel().getUuid());

		return "redirect:toAddNext";
	}

	/**
	 * 
	 * @param uuid
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddNext")
	public String toAddNext(@RequestParam("uuid") String uuid, Model model,
			@ModelAttribute("m") CustomerCombModel m, HttpServletRequest request) {
		CustomerCombModel customerCombModel = myService
				.getCustomerCombModelByCustomerUuid(uuid);
		preparedUpdateData(model, request);

		model.addAttribute("m", customerCombModel);

		return "customermgr/sysback/customer/CustomerAddNext";
	}

	/**
	 * 编辑会员的基本信息
	 * 
	 * @param uuid
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping("/toUpdateCustomer/{uuid}")
	public String toUpdateCustomer(@PathVariable("uuid") String uuid,
			Model model, HttpServletRequest request) {
		CustomerCombModel customerCombModel = myService
				.getCustomerCombModelByCustomerUuid(uuid);

		preparedUpdateData(model, request);

		model.addAttribute("m", customerCombModel);

		// 向页面发送数据
		sendIntegralTypeToPage(model);

		return "customermgr/sysback/customer/CustomerUpdate";
	}

	/**
	 * 更新会员账户信息
	 * 
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/updateAccountInfo" }, method = { RequestMethod.POST })
	public String updateAccountInfo(Model model,
			@ModelAttribute("m") CustomerCombModel m, HttpServletRequest request) {
		// 将mapErrorMsg清空
		this.getMapErrorMsg().clear();
		if (!checkUpdate(model, m, request)) {
			request.setAttribute("ShowMsgs", this.getMapErrorMsg());
			return (String) request.getAttribute(ERROR_BACK_URL);
		}
		cleanQuerySession(request);

		myService.updateCustomerAccountInfo(m.getCustomerModel());
		myService.updateCustomerFrozenLog(m);

		return "customermgr/sysback/customer/CustomerSuccess";
	}

	/**
	 * 更新会员基础信息
	 * 
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/updateBaseInfo" }, method = { RequestMethod.POST })
	public String updateBaseInfo(
			Model model,
			@ModelAttribute("m") CustomerCombModel m,
			HttpServletRequest request,
			@RequestParam(value = "imgFile", required = false) MultipartFile[] imgFiles) {

		myService.updateCustomerAccountInfo(m.getCustomerModel());
		
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String region = request.getParameter("region");

		CustomerInfoModel inm = m.getCustomerInfoModel();
		inm.setProvince(province);
		inm.setCity(city);
		inm.setRegion(region);

		myService.updateCustomerBaseInfo(m, imgFiles);

		return "customermgr/sysback/customer/CustomerSuccess";
	}

	/**
	 * 查看会员信息
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toView/{uuid}")
	public String toView(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		CustomerCombModel customerCombModel = myService.getAllCustomerCombModelByCustomerUuid(uuid);
		preparedView(model, request);
		
		/* ConsultRecordModel
		 *  咨询类型
		 *  0在线咨询即图文咨询 
		 *  2预约加号 
		 */
		// 图文咨询记录
		List<ConsultRecordModel> picWordRecordList = consultRecordService.getByCustomerUuid(uuid,"0");
		// 电话咨询记录
		List<OrderMainModel> telRecordList = orderService.getOrderList("1", uuid, 0, 0);
		// 加号记录
		List<ConsultRecordModel> plusRecordList = consultRecordService.getByCustomerUuid(uuid,"2");
		// 私人医生记录
		List<OrderMainModel> perList = orderService.getOrderList("2", uuid, 0, 0);
		// 关注的医生
		List<ConcernModel> concernList = concernService.getByCustomerUuid(uuid);
		// 随访
		List<VisitRecordModel> VisitRecordList = visitRecordService.getByCustomerUuid(uuid);
		
		model.addAttribute("picWordRecordList", picWordRecordList);
		model.addAttribute("telRecordList", telRecordList);
		model.addAttribute("perList", perList);
		model.addAttribute("plusRecordList", plusRecordList);
		model.addAttribute("visitRecordList", VisitRecordList);
		model.addAttribute("concernList", concernList);

		model.addAttribute("m", customerCombModel);
		return "customermgr/sysback/customer/CustomerView";
	}

	// ==========================================================================私有方法===========================================================================================
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
	 * 将会员冻结类型数据发送到页面去展示
	 * 
	 * @param model
	 */
	private void sendFrozenTypeShowName(Model model) {
		List<DataTablesPageParam> frozenTypeList = new ArrayList<DataTablesPageParam>();

		for (FrozenTypeEnum ft : FrozenTypeEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(ft.getKey());
			dpp.setValue(ft.getValue());
			frozenTypeList.add(dpp);
		}
		model.addAttribute("frozenTypeList", frozenTypeList);
	}

	/**
	 * 将证件类型发送到前台页面展示
	 * 
	 * @param model
	 */
	private void sendCertTypeShowName(Model model) {
		List<DataTablesPageParam> certTypeList = new ArrayList<DataTablesPageParam>();

		for (CertTypeEnum ct : CertTypeEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(ct.getKey());
			dpp.setValue(ct.getValue());
			certTypeList.add(dpp);
		}
		model.addAttribute("certTypeList", certTypeList);
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

	/**
	 * 会员来源第三方类型：<br/>
	 * 
	 * 1.qq账号登录<br/>
	 * 2.新浪账号登录<br/>
	 * 3.微信账号登录<br/>
	 * 4.百度账号登录<br/>
	 * 
	 * @param model
	 */
	private void sendThirdPlatShowName(Model model) {
		List<DataTablesPageParam> thirdPlatList = new ArrayList<DataTablesPageParam>();

		for (ThirdPlatTypeEnum tp : ThirdPlatTypeEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(tp.getKey());
			dpp.setValue(tp.getValue());
			thirdPlatList.add(dpp);
		}
		model.addAttribute("thirdPlatList", thirdPlatList);
	}

	/**
	 * 将会员客户端来源数据发送到页面去展示<br/>
	 * 会员客户端来源:移动端、PC端<br/>
	 * 
	 * @param model
	 */
	private void sendClientTypeShowName(Model model) {
		List<DataTablesPageParam> clientTypeList = new ArrayList<DataTablesPageParam>();

		for (ClientTypeEnum ct : ClientTypeEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(ct.getKey());
			dpp.setValue(ct.getValue());
			clientTypeList.add(dpp);
		}
		model.addAttribute("clientTypeList", clientTypeList);
	}

	/**
	 * 将会员来源站点数据发送到页面去展示<br/>
	 * 来源站点 :来源分为本站注册、第三方外站注册、会员导入 <br/>
	 * 
	 * @param model
	 */
	private void sendSiteTypeShowName(Model model) {
		List<DataTablesPageParam> siteTypeList = new ArrayList<DataTablesPageParam>();
		for (SiteTypeEnum st : SiteTypeEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(st.getKey());
			dpp.setValue(st.getValue());
			siteTypeList.add(dpp);
		}
		model.addAttribute("siteTypeList", siteTypeList);
	}

	/**
	 * 
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
	 * 
	 * 将患者分类展示数据发送到页面
	 * 
	 * @param model
	 */
	private void sendCustomerCategoryList(Model model) {
		List<CustomerCategoryModel> categoryList = customerCategoryService
				.getAll();
		model.addAttribute("categoryList", categoryList);
	}

	/**
	 * 得到会员的平台等级编号和等级名称的集合
	 * 
	 * @param model
	 */
	private void getCustomerShopLevel(Model model) {
		List<CustomerShopLevelModel> shopLevelModelList = customerShopLevelService
				.getShopLevelModelList();

		model.addAttribute("levelList", shopLevelModelList);
	}

	/**
	 * 向页面发送积分操作类型供页面筛选使用
	 * 
	 * @param model
	 */
	private void sendIntegralTypeToPage(Model model) {

		List<DataTablesPageParam> types = new ArrayList<DataTablesPageParam>();

		for (IntegralType q : IntegralType.values()) {
			DataTablesPageParam data = new DataTablesPageParam();
			data.setValue(q.getValue());
			data.setName(q.getName());
			types.add(data);
		}

		model.addAttribute("integralType", types);
	}
}
