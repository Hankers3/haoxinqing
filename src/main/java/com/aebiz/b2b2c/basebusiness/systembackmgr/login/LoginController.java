package com.aebiz.b2b2c.basebusiness.systembackmgr.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.SysManagerService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.service.SysMenuService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.service.SysRoleService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.service.SysRoleMenuRelService;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Controller
@RequestMapping("/admin")
public class LoginController {
	@Autowired
	private org.apache.shiro.mgt.SecurityManager sm = null;
	@Autowired
	private SysManagerService sysmanagers = null;
	@Autowired
	private SysMenuService sms = null;
	@Autowired
	private SysRoleMenuRelService menuRelService = null;
	/**
	 * SysRole模块的service，由Spring注入
	 */
	@Autowired
	private SysRoleService srs = null;
	

	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request) {
		
		String refresh = request.getParameter("refresh");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("mgrManager")) {
					String loginNameAndPwd = cookies[i].getValue();
					if (!StringUtil.isEmpty(loginNameAndPwd)) {
						String[] v = loginNameAndPwd.split(",");
						request.setAttribute("loginName", v[0]);
						request.setAttribute("pwd", v[1]);
						request.setAttribute("remember", "1");
					}
				}
			}
		}
		if(!StringUtil.isEmpty(refresh)){
			request.setAttribute("refresh", refresh);
		}
		return "basebusiness/systembackmgr/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("userId") String userId,
			@RequestParam("pwd") String pwd, HttpServletRequest request,
			HttpServletResponse response) {

		SecurityUtils.setSecurityManager(sm);

		// 后台登陆
		String type = request.getParameter("type");
		if (StringUtil.isEmpty(type)) {
			type = "system";
		}
		UsernamePasswordToken token = new UsernamePasswordToken(type + ","
				+ userId, pwd);
		token.setRememberMe(true);

		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);

		} catch (Exception err) {
			// err.printStackTrace();
			request.setAttribute("Error_Msg",
					MessageHelper.getMessage("login.failed"));
			return "basebusiness/systembackmgr/login";
		}

		SysManagerModel sum = sysmanagers.getById(userId);

		currentUser.getSession().setAttribute(LoginUserHelper.LOGIN_USER, sum);

		// 如果记住我，则加cookies
		String remember = request.getParameter("remember");
		if ("1".equals(remember)) {
			Cookie manCookies = new Cookie("mgrManager", userId + "," + pwd);

			// 有效时间为30天
			manCookies.setMaxAge(30 * 24 * 60 * 60);
			response.addCookie(manCookies);
		} else {
			Cookie manCookies = new Cookie("mgrManager", "");

			manCookies.setMaxAge(0);
			response.addCookie(manCookies);
		}

		return "redirect:/admin/toIndex";
		// return "basebusiness/systembackmgr/dashboard";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:toLogin";
	}

	@RequestMapping(value = "/toIndex", method = RequestMethod.GET)
	public String toIndex(Model model) {

		return "basebusiness/systembackmgr/index";
	}

	@RequestMapping(value = "/toHeader", method = RequestMethod.GET)
	public String toHeader(HttpServletRequest request) {
		String loginUserUuid = request.getParameter("loginUserUuid");
		if(StringUtil.isEmpty(loginUserUuid)){
			loginUserUuid = LoginUserHelper.getLoginUserUuid();
		}

		List<SysMenuModel> list = null;
//		List<SysRoleModel> roleList = srs.getSysRoleModelByManagerUuid(loginUserUuid);
//		List<String> roleIds = new ArrayList<String>();
//		boolean flag = false; 
//		if(roleList != null && roleList.size() >0){
//			for (SysRoleModel role : roleList) {
//				roleIds.add(role.getUuid());
//				if(!StringUtil.isEmpty(role.getUuid()) && SysRoleModel.SYSROEL.equals(role.getUuid())){
//					flag = true;
//					continue;
//				}
//			}
//		}
//
		List<SysMenuModel> listOne = new ArrayList<SysMenuModel>();
//		
//		if(flag){
			list = sms.getUserSysMenus(loginUserUuid);
//		}else{
//			list = menuRelService.getAllRelMenuByRoleUuid(roleIds);
//		}

		for (SysMenuModel smm : list) {
			if(smm != null){
				if(StringUtil.isEmpty(smm.getParentMenuUuid()) || smm.getParentMenuUuid().equals("-1")){
					listOne.add(smm);
				}
			}
			
		}
		String selMenu = request.getParameter("selMenu");
		request.setAttribute("Menu_One", listOne);

		request.setAttribute("LoginUserName", LoginUserHelper
				.<SysManagerModel> getLoginUserModel().getName());

		request.setAttribute("Now_Time", DateFormatHelper.getNowTimeStr());
		request.setAttribute("selMenu", selMenu);
		return "basebusiness/systembackmgr/common/header";
	}
	
	private void getRoot(List<SysMenuModel> listOne,SysMenuModel smm){
		if(!StringUtil.isEmpty(smm.getParentMenuUuid())){
			if(!smm.getParentMenuUuid().equals("-1")){
				SysMenuModel menuModel = sms.getByUuid(smm.getParentMenuUuid());
				if(menuModel != null){
					this.getRoot(listOne, menuModel);
				}
			}else{
				listOne.add(smm);
			}
		}
	}

	@RequestMapping(value = "/toFooter", method = RequestMethod.GET)
	public String toFooter() {
		return "basebusiness/systembackmgr/common/footer";
	}

	@RequestMapping(value = "/toIndex/{parentId}", method = RequestMethod.GET)
	public String toIndex(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("parentId") String parentId, Model model) {
		String toUrl = request.getParameter("toUrl");
		String selMenu = request.getParameter("selMenu");
		model.addAttribute("toUrl", toUrl);
		model.addAttribute("parentId", parentId);
		model.addAttribute("selMenu", selMenu);
		return "basebusiness/systembackmgr/index";
	}

	@RequestMapping(value = "/toMenu/{parentId}", method = RequestMethod.GET)
	public String toMenu(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("parentId") String parentId) {
		String loginUserUuid = request.getParameter("loginUserUuid");
		if(StringUtil.isEmpty(loginUserUuid)){
			loginUserUuid = LoginUserHelper.getLoginUserUuid();
		}
		//List<SysMenuModel> list = sms.getUserSysMenus(loginUserUuid);
		List<SysMenuModel> list = null ;
//		List<SysRoleModel> roleList = srs.getSysRoleModelByManagerUuid(loginUserUuid);
//		List<String> roleIds = new ArrayList<String>();
//		boolean flag = false; 
//		if(roleList != null && roleList.size() >0){
//			for (SysRoleModel role : roleList) {
//				roleIds.add(role.getUuid());
//				if(!StringUtil.isEmpty(role.getUuid()) && SysRoleModel.SYSROEL.equals(role.getUuid())){
//					flag = true;
//					continue;
//				}
//			}
//		}
//		//判断登陆的用户是否属于系统管理员角色,如果属于,则查全部,不属于则查该角色下的权限 
//		if(flag){
			list = sms.getUserSysMenus(loginUserUuid);
//		}else{
//			list = menuRelService.getAllRelMenuByRoleUuid(roleIds);
//		}
		//list.addAll(SysMenuModelList);
		
			
		List<SysMenuModel> listOne = new ArrayList<SysMenuModel>();
		Map<SysMenuModel, List<SysMenuModel>> mapOneTwo = new HashMap<SysMenuModel, List<SysMenuModel>>();
		Map<SysMenuModel, List<SysMenuModel>> mapTwoThree = new HashMap<SysMenuModel, List<SysMenuModel>>();

		for (SysMenuModel smm : list) {

			// 当前parentId下的所有二级分类 smm ,并将smm的子，放入到tempList中
			if (smm.getParentMenuUuid().equals(parentId)) {
				List<SysMenuModel> tempList = new ArrayList<SysMenuModel>();

				for (SysMenuModel smm2 : list) {
					if (smm2.getParentMenuUuid().endsWith(smm.getUuid())) {
						tempList.add(smm2);
					}
				}

				mapOneTwo.put(smm, tempList);
				listOne.add(smm);
			}
		}

		for (List<SysMenuModel> listTwo : mapOneTwo.values()) {
			for (SysMenuModel smm2 : listTwo) {
				List<SysMenuModel> tempList = new ArrayList<SysMenuModel>();
				for (SysMenuModel smm3 : list) {
					if (smm3.getParentMenuUuid().endsWith(smm2.getUuid())) {
						tempList.add(smm3);
					}
				}

				if (tempList != null && tempList.size() <= 0) {
					tempList = null;
				}

				mapTwoThree.put(smm2, tempList);
			}
		}

		request.setAttribute("Menu_One", listOne);
		request.setAttribute("Menu_OneTwo", mapOneTwo);
		request.setAttribute("Menu_TwoThree", mapTwoThree);

		return "basebusiness/systembackmgr/common/menu";
	}

	/**
	 * 欢迎页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toWelcome", method = RequestMethod.GET)
	public String toWelcome(HttpServletRequest request) {

		return "basebusiness/systembackmgr/welcome";
	}
}
