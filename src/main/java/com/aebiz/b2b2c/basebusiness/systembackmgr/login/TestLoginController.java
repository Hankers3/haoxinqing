package com.aebiz.b2b2c.basebusiness.systembackmgr.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.SysManagerService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.service.SysMenuService;

@Controller
@RequestMapping("/testlogin")
public class TestLoginController {
	@Autowired
	private org.apache.shiro.mgt.SecurityManager sm = null;
	@Autowired
	private SysManagerService sysmanagers = null;
	@Autowired
	private SysMenuService sms = null;

	@RequestMapping(value = "/testLogin", method = RequestMethod.GET)
	public String testLogin(HttpServletRequest request,
			HttpServletResponse response) {

		String sessionId = request.getParameter("sessionId");
		Cookie newCookie = new Cookie("JSESSIONID", sessionId);
		newCookie.setPath("/");
		newCookie.setMaxAge(30 * 60);
		response.addCookie(newCookie);

		System.out.println("sessionId====" + sessionId);

		return "basebusiness/systembackmgr/login";
	}

}
