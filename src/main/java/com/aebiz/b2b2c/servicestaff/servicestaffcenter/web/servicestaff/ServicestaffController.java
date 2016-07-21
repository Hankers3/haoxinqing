package com.aebiz.b2b2c.servicestaff.servicestaffcenter.web.servicestaff;import java.io.IOException;import java.io.PrintWriter;import java.util.HashMap;import java.util.List;import java.util.Map;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.ModelAttribute;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.ResponseBody;import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;import com.aebiz.b2b2c.baseframework.utils.StringUtil;import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffQueryModel;import com.aebiz.b2b2c.servicestaff.servicestafflevel.service.ServicestafflevelService;import com.aebiz.b2b2c.servicestaff.servicestafflevel.vo.ServicestafflevelModel;import com.alibaba.fastjson.JSON;@Controller("UCServicestaffController")@RequestMapping("/usercenter/servicestaff")public class ServicestaffController extends		BaseController<ServicestaffModel, ServicestaffQueryModel> {	private ServicestaffService myService;	/* 平台家政员等级信息service */	@Autowired	private ServicestafflevelService servicestafflevelService;	@Autowired	public void setMyService(ServicestaffService bs) {		this.myService = bs;		super.setBs(bs);	}		public ServicestaffController() {		super("/servicestaff/sysback/servicestaff", "Servicestaff",				ServicestaffController.class);	}	/**	 * 查询待审核的医生	 * 	 * @param model	 * @param request	 */	@RequestMapping(value = { "/toApplyList" }, method = { RequestMethod.GET })	public String toApplyList(Model model, HttpServletRequest request) {		return "servicestaff/sysback/servicestaff/ServicestaffApplyList";	}			/**	 * 查询医生认证页面的	 * 	 * @param model	 * @param request	 */	@RequestMapping(value = { "/toInterviewList" }, method = { RequestMethod.GET })	public String toInterviewList(Model model, HttpServletRequest request) {		return "servicestaff/sysback/servicestaff/ServicestaffInterviewList";	}	/**	 * 查询冻结医生的	 * 	 * @param model	 * @param request	 */	@RequestMapping(value = { "/toEntryList" }, method = { RequestMethod.GET })	public String toEntryList(Model model, HttpServletRequest request) {		return "servicestaff/sysback/servicestaff/ServicestaffEntryList";	}	/**	 * 查询黑名单里的	 * 	 * @param model	 * @param request	 */	@RequestMapping(value = { "/toBlackList" }, method = { RequestMethod.GET })	public String toBlackList(Model model, HttpServletRequest request) {		return "servicestaff/sysback/servicestaff/ServicestaffBlackList";	}	/**	 * 黑名单解冻老多数据的	 * 	 * @param model	 * @param request	 */	/*	 * @RequestMapping(value = "/unfreeze", method = RequestMethod.POST)	 * 	 * @ResponseBody public String unfreeze(	 * 	 * @RequestParam("selectOne") List<String> needDeleteUuids, BaseWebModel wm,	 * HttpServletRequest request, HttpServletResponse response) throws	 * Exception {	 * 	 * if(needDeleteUuids!=null&&needDeleteUuids.size()>0){ for(int i=0;i <	 * needDeleteUuids.size(); i++) { ServicestaffModel s =	 * myService.getByUuid(needDeleteUuids.get(i)); s.setFrozenSate("0");	 * myService.update(s); } } return "true"; }	 */	/**	 * 会员解冻,需要更新会员登录信息中的冻结状态<br />	 * 1表示冻结，0表示未冻结	 * 	 * @param uuids	 * @param response	 * @return	 * @throws IOException	 */	@RequestMapping("/unfrozen")	@ResponseBody	public String unfrozen(			@RequestParam("selectOne") List<String> needDeleteUuids,			@RequestParam("frozenSate") String frozenSate,			HttpServletResponse response) throws IOException {		this.myService.unfrozen(needDeleteUuids, frozenSate);		return "true";	}	/**	 * 需要更新家政员的状态<br />	 * 	 * @param uuid	 * @param response	 * @return	 */	@RequestMapping("/upStaffState")	@ResponseBody	public String upStaffState(@RequestParam("uuid") String uuid,			@RequestParam("state") String state, HttpServletResponse response) {		// myService.unpdateState(uuid, state);		return "true";	}	/**	 * 冻结 根据会员uuid对会员冻结<br />	 * 1表示冻结，0表示未冻结 hedongfei	 * 	 * @param uuids	 * @param response	 * @return	 * @throws IOException	 */	@RequestMapping("/frozen/{serviceStaffuuid}")	public String frozen(			@PathVariable("serviceStaffuuid") String serviceStaffuuid,			HttpServletResponse response) throws IOException {		// this.myService.unpdateState(serviceStaffuuid, "1");		this.myService.unpdateFreezeState(serviceStaffuuid, "1");		return "servicestaff/sysback/servicestaff/ServicestaffList";	}	/*	 * 密码重置 hedongfei	 */	@RequestMapping(value = { "/pwdreset" })	public String pwdreset(Model model, HttpServletRequest request,			HttpServletResponse response,			@ModelAttribute("m") ServicestaffModel m) throws Exception {		String pwd = request.getParameter("pwd");		String confirmPwd = request.getParameter("confirmPwd");		Map<String, Object> jsonMap = new HashMap<String, Object>();		// 如果密码为空		if (StringUtil.isEmpty(pwd)) {			jsonMap.put("message", "pwd_empty");		}		// 如果密码不匹配		if (StringUtil.isEmpty(confirmPwd) && !pwd.equals(confirmPwd)) {			jsonMap.put("message", "no_equal");		}		// 得到对象		ServicestaffModel sfm = myService.getByUuid(m.getUuid());		sfm.setPassword(pwd);		myService.update(sfm);		jsonMap.put("message", "success");		response.setContentType("charset=UTF-8");		response.setCharacterEncoding("UTF-8");		PrintWriter out = response.getWriter();		out.print(JSON.toJSONString(jsonMap));		return null;	}	/**	 * 得到会员的平台等级编号和等级名称的集合	 * 	 * @param model	 */	private void getServicestafflevelModel(Model model) {		List<ServicestafflevelModel> servicestafflevelModelList = servicestafflevelService				.ServicestafflevelModelList();		// 将等级放到编辑页面上		model.addAttribute("levelList", servicestafflevelModelList);	}	/**	 * 获取会员平台等级	 */	@Override	protected void preparedUpdateData(Model model, HttpServletRequest request) {		// 会员平台等级展示数据		getServicestafflevelModel(model);	}	/**	 * 跳转新增家政员页面	 * 	 * @param model	 * @param request	 */	@RequestMapping(value = { "/toAddserviceStaff" }, method = { RequestMethod.GET })	public String toAddserviceStaff(Model model, HttpServletRequest request) {		// 将家政员等级放到页面上		preparedUpdateData(model, request);		return "servicestaff/sysback/servicestaff/ServicestaffAdd";	}	/**	 * 押金管理页面	 * 	 * @param model	 * @param request	 */	@RequestMapping(value = { "/toDepositAdminister" }, method = { RequestMethod.GET })	public String toDepositAdminister(Model model, HttpServletRequest request) {		return "servicestaff/sysback/servicestaff/DepositAdminister";	}	/**	 * 修改家政员押金金额及状态 hedongfei	 * 	 * @param uuid	 * @param deposit	 * @param depositState	 */	@RequestMapping("/getServicestaffModelByUuid/{uuid}")	public String getServicestaffModelByUuid(@PathVariable("uuid") String uuid,			HttpServletResponse response, Model model) {		ServicestaffModel servicestaffModel = myService				.getServicestaffModelByUuid(uuid);		model.addAttribute("t", servicestaffModel);		return "servicestaff/sysback/servicestaff/ShowDeposit";	}	/**	 * 查询家政员入职的	 * 	 * @param model	 * @param request	 */	/*	 * @RequestMapping(value = { "/ShowDeposit" }, method = { RequestMethod.GET	 * }) public String ShowDeposit(Model model, HttpServletRequest request){	 * 	 * return "servicestaff/sysback/servicestaff/ShowDeposit"; }	 */	/**	 * 进入家政员个人设置页面	 * 	 * @param model	 * @param request	 */	@RequestMapping(value = { "/toPersonalSettings" }, method = { RequestMethod.GET })	public String toPersonalSettings(Model model, HttpServletRequest request) {		// 获取当前登陆的家政员的uuid		String servicestaffuuid = LoginUserHelper.getLoginUserUuid();		return "servicestaff/servicestaffcenter/servicestaff/PersonalSettings";	}}