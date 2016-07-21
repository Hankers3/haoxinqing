package com.aebiz.b2b2c.customermgr.mobile.web.pub;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 类描述：HTTP请求服务工具类
 * @author: dgh
 * @date： 日期：2013-11-14 时间：下午01:26:56
 * @version 1.0
 */
public class HttpServletUtils {
	private static final String DEFAULT_ENCODING = SysConst.DEFAULT_CHARACTOR_ENCONDING;
	
	private static final String JSON_TYPE        = SysConst.JSON_TYPE;
	
	/**
	 * 把对象转换为json字符串并通过response返回到前台
	 * @param response  
	 * @param obj 要转化的对象
	 */
	public static void outJson(HttpServletResponse response, Object obj) {
		String jsonString = JsonUtils.getJSONString(obj);
		outJsonString(response, jsonString);//json串输出
	}
	
	/**
	 * 把对象转换为json字符串并通过response返回到前台(输出带标签的字符串)
	 * @param response  
	 * @param obj 要转化的对象
	 */
	public static void outJsonCallBack(HttpServletResponse response, Object obj,Object callback) {
		String jsonString = JsonUtils.getJSONString(obj);
		outJsonCallBack(response, jsonString,callback);//json串输出
	
	}
	
	/**
	 * 设置编码并并将字符串写到前台
	 * @param response
	 * @param str
	 */
	public static void outJsonString(HttpServletResponse response, String str) {
		outString(response, str);
	}
	
	/**
	 * 设置编码并并将字符串写到前台(输出带标签的字符串)
	 * @param response
	 * @param str
	 */
	public static void outJsonCallBack(HttpServletResponse response, String str,Object callback) {
		outStringCallBack(response, str,callback);
	}
	
	/**
	 * 将字符串写到前台
	 * @param response
	 * @param str
	 */
	public static void outString(HttpServletResponse response, String str) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 将字符串写到前台（输出带标签的字符串）
	 * @param response
	 * @param str
	 */
	public static void outStringCallBack(HttpServletResponse response, String str,Object callback) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(callback !=null ){
				out.print( callback+"("+str+")");
			}else{
				out.print(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

}
