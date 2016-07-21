package com.aebiz.b2b2c.customermgr.mobile.web.pub;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;

/**
 * APP接口基础控制类
 * @author: dgh
 * @date： 日期：2014-11-14 时间：下午01:26:56
 * @version 1.0
 *
 */
public class AppBaseController extends BaseController<BaseModel, BaseModel>{
	
	
	public AppBaseController(String moduleJspPath, String moduleName,
			Class controllerClass) {
		super(moduleJspPath, moduleName, controllerClass);
		
	}


	/**
	 * 验证用户是否登录
	 * @param request
	 * @param response
	 * @param userId 用户id
	 * @param breakOut 锚点标志
	 */
	public void checkSessionUser(HttpServletRequest request,HttpServletResponse response,String userId,boolean breakOut){
		CustomerModel session_user = (CustomerModel)request.getSession().getAttribute("User");
		if(session_user == null){
			HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "session_user is null"));
			breakOut = true;
		}
		if(!breakOut){
			if(!userId.equals(session_user.getUuid())){
				HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, "userId is invalid"));
				breakOut = true;
			}
		}
	}
	
	
	/**
	 * 返回错误信息调用的组装jsonMap
	 * @param code
	 * @param message
	 * @return
	 */
	public Map<Object,Object> getFailJsonMap(String code,String message){
		Map<Object,Object> jsonMap = new HashMap<Object,Object>();
		Map<Object,Object> runMap = new HashMap<Object,Object>();
		runMap.put("success", code);
		runMap.put("message", message);
		jsonMap.put("query", runMap);
		return jsonMap;
	}
	
	/**
	 * 返回成功信息调用的组装jsonMap
	 * @param code
	 * @param message
	 * @return
	 */
	public Map<Object,Object> getSucJsonMap(String code,String message){
		Map<Object,Object> jsonMap = new HashMap<Object,Object>();
		Map<Object,Object> runMap = new HashMap<Object,Object>();
		runMap.put("success", code);
		runMap.put("message", message);
		jsonMap.put("query", runMap);
		return jsonMap;
	}
	
	/**
	 * 获取参数值并且进行非空判断
	 * @param request
	 * @param response
	 * @param breakOut 标志位
	 * @param params 传入参数，为数组格式
	 */
	public Map<String,String> getParam(HttpServletRequest request,HttpServletResponse response,boolean breakOut,String[] params){
		Map<String,String> paramMap = new HashMap<String,String>();

		for(int i=0;i<params.length;i++){
			String paramName = params[i].split(",")[0];
			String temp = paramName;//暂时存储参数名
			String isCheck = params[i].split(",")[1];
			paramName = request.getParameter(paramName);
			paramMap.put(temp, paramName);
			if("true".equals(isCheck) && StringUtil.isEmpty(paramName)){
				HttpServletUtils.outJson(response, this.getFailJsonMap(SysConst.FAIL, temp+" is null"));
				breakOut = true;
			}
			paramMap.put("breakOut", String.valueOf(breakOut));
		}
		return paramMap;
	}
	
	/**
	 * 返回正确信息调用的组装jsonMap
	 * @param code
	 * @param reson
	 * @return
	 */
	public Map<Object,Object> getSuccessJsonMap(String code){
		Map<Object,Object> jsonMap = new HashMap<Object,Object>();
		Map<Object,Object> runMap = new HashMap<Object,Object>();
		runMap.put("runNumber", code);
		jsonMap.put("query", runMap);
		return jsonMap;
	}
	
	
}
