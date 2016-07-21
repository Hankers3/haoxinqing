package com.aebiz.b2b2c.basebusiness.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.basebusiness.plugin.validateCode.RandomValidateCode;

@Controller
public class ImageCaptchaController {

	//商户登录时验证码
	@RequestMapping("/getStoreLoginCode")
	public String getStoreLoginCode(HttpServletResponse response,
			HttpServletRequest request) {
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			randomValidateCode.getRandcode(request, response,"STORELOGINCODE");// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	
	//商户注册时验证码
	@RequestMapping("/getStoreRegCode")
	public String getStoreRegCode(HttpServletResponse response,
			HttpServletRequest request) {
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			randomValidateCode.getRandcode(request, response,"STOEREGCODEKEY");// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	//会员找回密码时验证码
	@RequestMapping("/getStoreFindPwdCode")
	public String getStoreFindPwdCode(HttpServletResponse response,
			HttpServletRequest request) {
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			randomValidateCode.getRandcode(request, response,"STOEFINDPWDCODEKEY");// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	//会员登录时验证码
	@RequestMapping("/getCustomerLoginCode")
	public String getCustomerLoginCode(HttpServletResponse response,
			HttpServletRequest request) {
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			randomValidateCode.getRandcode(request, response,"CUSTOMELOGINCODEKEY");// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	//会员注册时验证码
	@RequestMapping("/getCustomerRegCode")
	public String getCustomerRegCode(HttpServletResponse response,
			HttpServletRequest request) {
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			randomValidateCode.getRandcode(request, response,"CUSTOMEREGCODEKEY");// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}

}
