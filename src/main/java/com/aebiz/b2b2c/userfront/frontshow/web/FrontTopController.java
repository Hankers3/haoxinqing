package com.aebiz.b2b2c.userfront.frontshow.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Controller
public class FrontTopController {
	
	//打开首页是否已经弹出注册提示框
	public static final String REGISTERTIP = "REGISTERTIP";// 放到cookies中的key
	
	@RequestMapping("getFrontTop")
	public String getFrontTop(HttpServletRequest request,
			HttpServletResponse response) {

		return "userfront/common/platTop";
	}
	
	@RequestMapping("getSimpleTop")
	public String getSimpleTop(HttpServletRequest request,
			HttpServletResponse response) {

		return "userfront/common/platSimpleTop";
	}
	
	@RequestMapping("getSimpleLogoTop")
	public String getSimpleLogoTop(HttpServletRequest request,
			HttpServletResponse response) {

		return "userfront/common/platSimpleLogoTop";
	}

	/**
	 * 获取打开首页是否已经弹出注册提示框
	 * @author zdx
	 * @date 2015-8-27
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/getRegisterTipCookies" }, method = { RequestMethod.GET })
	@ResponseBody
	public String getRegisterTipCookies( HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(REGISTERTIP)) {
					return "true" ;
				}
			}
		}
		return "false";
	}
	
	/**
	 * 如果开发首页已经弹出了提示注册的页面，则在cookie中标记已经读取
	 * @author zdx
	 * @date 2015-8-27
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/addRegisterTipCookies" }, method = { RequestMethod.GET })
	@ResponseBody
	public String addCookies(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie(REGISTERTIP, REGISTERTIP);
		// 设置1天生存期，如果设置为负值的话，则为浏览器进程cookie(内存中保存)，关闭浏览器就失效
		cookie.setMaxAge(30 * 24 * 60 * 60);
		response.addCookie(cookie);
		return "success";
	}
}